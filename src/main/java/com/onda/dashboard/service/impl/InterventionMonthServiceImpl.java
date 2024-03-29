/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onda.dashboard.dao.InterventionMonthDao;
import com.onda.dashboard.model.Equipement;
import com.onda.dashboard.model.InterventionDay;
import com.onda.dashboard.model.InterventionMonth;
import com.onda.dashboard.model.Timing;
import com.onda.dashboard.rest.converter.InterventionMonthConverter;
import com.onda.dashboard.rest.vo.InterventionMonthVo;
import com.onda.dashboard.service.InterventionDayService;
import com.onda.dashboard.service.InterventionMonthService;
import com.onda.dashboard.util.DateUtil;
import com.onda.dashboard.util.InterventionMonthComparator;
import com.onda.dashboard.util.JasperUtil;
import com.onda.dashboard.util.MonthUtil;
import com.onda.dashboard.util.NumberUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 * @author hp
 */
@Service
public class InterventionMonthServiceImpl implements InterventionMonthService {

	private static final Logger log = LoggerFactory.getLogger(InterventionMonthServiceImpl.class);

	@Autowired
	private InterventionMonthDao interventionMonthDao;

	@Autowired
	private InterventionDayService interventionDayService;

	@Override
	public void createInterventionMonth(Equipement equipement, List<InterventionDay> interventionDays) {
		for (InterventionDay interventionDay : interventionDays) {
			Date dateIntervention = DateUtil.getFirstDayOfMonthByYearAndMonth(
					interventionDay.getInterventionStart().getYear(),
					interventionDay.getInterventionStart().getMonthValue());
			InterventionMonth interventionMonth = findByEquipementNameAndInterventionDate(equipement.getName(),
					dateIntervention);
			if (interventionMonth == null) {
				interventionMonth = new InterventionMonth(dateIntervention);
				interventionMonth.setEquipement(equipement);
			}
			InterventionDay intDayCheck = interventionMonth.getInterventionDays().stream()
					.filter(intDay -> intDay.getAnomaly().equals(interventionDay.getAnomaly())).findAny().orElse(null);
			if (intDayCheck == null) {
				interventionMonth.getInterventionDays()
						.add(interventionDayService.setInterventionDayInfos(interventionDay));
				interventionMonthDao.save(interventionMonth);
			}
		}
	}

	@Override
	public InterventionMonth findByEquipementNameAndInterventionDate(String name, Date dateIntervention) {
		return interventionMonthDao.findByEquipementNameAndInterventionDate(name, dateIntervention);
	}

	@Override
	public InterventionMonth findByEquipementName(String name) {
		return interventionMonthDao.findByEquipementName(name);
	}

	@Override
	public List<InterventionMonth> findByInterventionDateOrderByEquipementTypeNameAscIdAsc(Date dateIntervention) {
		return interventionMonthDao.findByInterventionDateOrderByEquipementTypeNameAscIdAsc(dateIntervention);
	}

	@Override
	public InterventionMonth findTopByInterventionDate(Date date) {
		return interventionMonthDao.findTopByInterventionDate(date);
	}

	@Override
	public List<InterventionMonthVo> interventionMonthsToPrint(List<InterventionMonth> interventionMonths) {
		if (interventionMonths != null && !interventionMonths.isEmpty()) {
			List<InterventionMonthVo> interventionMonthVos = new InterventionMonthConverter().toVo(interventionMonths);
			interventionMonthVos.forEach(interventionMonthVo -> {
				interventionMonthVo.getInterventionPartDaysVo().forEach(interventionDayVo -> {
					int housMaintenance = NumberUtil
							.toInt(interventionMonthVo.getCurrentBreakPeriodMaintenance().getHour())
							+ NumberUtil.toInt(interventionDayVo.getBreakDuration().getHour());
					int minutesMaintenance = NumberUtil
							.toInt(interventionMonthVo.getCurrentBreakPeriodMaintenance().getMinute())
							+ NumberUtil.toInt(interventionDayVo.getBreakDuration().getMinute());
					if (minutesMaintenance >= 60) {
						++housMaintenance;
						minutesMaintenance -= 60;
					}
					interventionMonthVo.getCurrentBreakPeriodMaintenance()
							.setHour(NumberUtil.toString(housMaintenance));
					interventionMonthVo.getCurrentBreakPeriodMaintenance()
							.setMinute(NumberUtil.toString(minutesMaintenance));
				});
				// some calculs for monthly data
				int functionalityTimeWantedHours = DateUtil
						.lenghtOfMonth(DateUtil.fromStringToLocalDate(interventionMonthVo.getDateIntervention())) * 24;
				int functionalityTimeWantedMinutes = 0;
				int functionalityTimeAchievedHours = functionalityTimeWantedHours - (NumberUtil
						.toInt(interventionMonthVo.getEquipementVo().getExpectedBreakPeriodMaintenance().getHour())
						+ NumberUtil.toInt(interventionMonthVo.getCurrentBreakPeriodMaintenance().getHour()));
				int functionalityTimeAchievedMinutes = functionalityTimeWantedMinutes - (NumberUtil
						.toInt(interventionMonthVo.getEquipementVo().getExpectedBreakPeriodMaintenance().getMinute())
						+ NumberUtil.toInt(interventionMonthVo.getCurrentBreakPeriodMaintenance().getMinute()));
				if (functionalityTimeAchievedMinutes < 0 && functionalityTimeAchievedMinutes >= -60) {
					--functionalityTimeAchievedHours;
					functionalityTimeAchievedMinutes = functionalityTimeAchievedMinutes + 60;
				} else if (functionalityTimeAchievedMinutes < -60) {
					int restMinutes = Math.abs(functionalityTimeAchievedMinutes)
							- ((Math.abs(functionalityTimeAchievedMinutes) / 60) * 60);
					functionalityTimeAchievedHours = functionalityTimeAchievedHours
							- ((Math.abs(functionalityTimeAchievedMinutes) / 60) + (restMinutes > 0 ? 1 : 0));
					functionalityTimeAchievedMinutes = 60 - restMinutes;
				}
				Double periodFunctionAchieved = functionalityTimeAchievedHours
						+ NumberUtil.toDouble(functionalityTimeAchievedMinutes / 60 + "");
				// set infos to the main object
				interventionMonthVo.getFunctionalityTimeWanted()
						.setHour(NumberUtil.toString(functionalityTimeWantedHours));
				interventionMonthVo.getFunctionalityTimeWanted()
						.setMinute(NumberUtil.toString(functionalityTimeWantedMinutes));
				interventionMonthVo.getFunctionalityTimeAchieved()
						.setHour(NumberUtil.toString(functionalityTimeAchievedHours));
				interventionMonthVo.getFunctionalityTimeAchieved()
						.setMinute(NumberUtil.toString(functionalityTimeAchievedMinutes));
				// calculate the tbf
				Double tbf = periodFunctionAchieved * 100 / functionalityTimeWantedHours;

				/*
				 * DecimalFormat df = new DecimalFormat(".##");
				 * df.setRoundingMode(RoundingMode.DOWN); df.format(tbf)
				 */
				interventionMonthVo.setTbf(new BigDecimal(tbf).setScale(2, RoundingMode.DOWN).doubleValue());
			});
			return interventionMonthVos;
		}
		return null;
	}

	@Override
	public List<InterventionMonthVo> findAll() {
		return interventionMonthsToPrint(interventionMonthDao.findAll());
	}

	@Override
	public void printDoc(HttpServletResponse response, int year, int month) {
		List<InterventionMonth> interventionMonths = findByInterventionDateOrderByEquipementTypeNameAscIdAsc(
				DateUtil.toDate(LocalDate.of(year, month, 1)));
		if (interventionMonths != null && !interventionMonths.isEmpty()) {
			JasperPrint jasperPrint = null;
			Map<String, Object> params = new HashMap<>();

			List<InterventionMonthVo> list = interventionMonthsToPrint(interventionMonths);

			Double average = list.stream().mapToDouble(item -> item.getTbf()).average().getAsDouble();

			String mois = MonthUtil.getMonth(month - 1);
			calculateTotalTbf(list, params);
			params.put("average", NumberUtil.scaleDoubletoTwo(average));
			params.put("year", year);
			params.put("month", mois);

			OutputStream out = null;

			try {
				out = response.getOutputStream();
				jasperPrint = new JasperUtil().generateDoc(list, params, "Dashboard_Detail.jasper", "pdf");
				// JasperExportManager.exportReportToPdfStream(jasperPrint, out);

				JRPdfExporter pdfExporter = new JRPdfExporter();
				pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
				pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
				pdfExporter.exportReport();

				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition",
						"attachement; filename=\"TableauDeBord" + mois + year + ".pdf" + "\"");

				out.write(pdfReportStream.toByteArray());
				out.close();
				pdfReportStream.close();
			} catch (FileNotFoundException e) {
				System.out.println(Arrays.toString(e.getStackTrace()));
			} catch (JRException | IOException e) {
				System.out.println(Arrays.toString(e.getStackTrace()));
			}
		}
	}

	private void calculateTotalTbf(List<InterventionMonthVo> list, Map<String, Object> params) {
		int functionalityTimeWantedHours = 0;
		int functionalityTimeWantedMinutes = 0;
		int functionalityTimeAchievedHours = 0;
		int functionalityTimeAchievedMinutes = 0;
		int currentBreakPeriodMaintenanceHours = 0;
		int currentBreakPeriodMaintenanceMinutes = 0;
		int expectedBreakPeriodMaintenanceHours = 0;
		int expectedBreakPeriodMaintenanceMinutes = 0;

		for (InterventionMonthVo interventionMonthVo : list) {
			functionalityTimeWantedHours += NumberUtil
					.toInt(interventionMonthVo.getFunctionalityTimeWanted().getHour());
			functionalityTimeAchievedHours += NumberUtil
					.toInt(interventionMonthVo.getFunctionalityTimeAchieved().getHour());
			functionalityTimeAchievedMinutes += NumberUtil
					.toInt(interventionMonthVo.getFunctionalityTimeAchieved().getMinute());
			currentBreakPeriodMaintenanceHours += NumberUtil
					.toInt(interventionMonthVo.getCurrentBreakPeriodMaintenance().getHour());
			currentBreakPeriodMaintenanceMinutes += NumberUtil
					.toInt(interventionMonthVo.getCurrentBreakPeriodMaintenance().getMinute());
			expectedBreakPeriodMaintenanceHours += NumberUtil
					.toInt(interventionMonthVo.getEquipementVo().getExpectedBreakPeriodMaintenance().getHour());
			expectedBreakPeriodMaintenanceMinutes += NumberUtil
					.toInt(interventionMonthVo.getEquipementVo().getExpectedBreakPeriodMaintenance().getMinute());

			if (functionalityTimeAchievedMinutes >= 60) {
				++functionalityTimeAchievedHours;
				functionalityTimeAchievedMinutes -= 60;
			}
			if (currentBreakPeriodMaintenanceMinutes >= 60) {
				++currentBreakPeriodMaintenanceHours;
				currentBreakPeriodMaintenanceMinutes -= 60;
			}
			if (expectedBreakPeriodMaintenanceMinutes >= 60) {
				++expectedBreakPeriodMaintenanceHours;
				expectedBreakPeriodMaintenanceMinutes -= 60;
			}
		}
		params.put("functionalityTimeWanted", new Timing(functionalityTimeWantedHours, functionalityTimeWantedMinutes));
		params.put("functionalityTimeAchieved",
				new Timing(functionalityTimeAchievedHours, functionalityTimeAchievedMinutes));
		params.put("currentBreakPeriodMaintenance",
				new Timing(currentBreakPeriodMaintenanceHours, currentBreakPeriodMaintenanceMinutes));
		params.put("expectedBreakPeriodMaintenance",
				new Timing(expectedBreakPeriodMaintenanceHours, expectedBreakPeriodMaintenanceMinutes));
	}

	@Override
	public void printGraph(HttpServletResponse response, int year, int month, double object) {
		List<InterventionMonth> interventionMonths = findByInterventionDateOrderByEquipementTypeNameAscIdAsc(
				DateUtil.toDate(LocalDate.of(year, month, 1)));
		if (interventionMonths != null && !interventionMonths.isEmpty()) {
			JasperPrint jasperPrint = null;
			Map<String, Object> params = new HashMap<>();

			List<InterventionMonthVo> list = interventionMonthsToPrint(interventionMonths);
			InterventionMonthVo interventionMonthMin = Collections.min(list, new InterventionMonthComparator());

			Double average = list.stream().mapToDouble(item -> item.getTbf()).average().getAsDouble();

			String mois = MonthUtil.getMonth(month - 1);

			params.put("tbfMin", interventionMonthMin.getTbf().intValue());
			params.put("object", NumberUtil.scaleDoubletoTwo(object));
			params.put("average", NumberUtil.scaleDoubletoTwo(average));
			params.put("month", mois);
			params.put("year", year);
			params.put("logoImage", getClass().getClassLoader().getResourceAsStream("reports/logo.png"));

			OutputStream out = null;

			try {
				out = response.getOutputStream();
				jasperPrint = new JasperUtil().generateDoc(list, params, "TBF_Detail.jasper", "pdf");
				// JasperExportManager.exportReportToPdfStream(jasperPrint, out);

				JRPdfExporter pdfExporter = new JRPdfExporter();
				pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
				pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
				pdfExporter.exportReport();

				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachement; filename=\"tbf" + mois + year + ".pdf" + "\"");

				out.write(pdfReportStream.toByteArray());
				out.close();
				pdfReportStream.close();
			} catch (FileNotFoundException e) {
				System.out.println(Arrays.toString(e.getStackTrace()));
			} catch (JRException | IOException e) {
				System.out.println(Arrays.toString(e.getStackTrace()));
			}
		}
	}

	@Override
	public List<InterventionMonthVo> findByYear(int year) {
		LocalDate ldStart = LocalDate.of(year, 1, 1);
		LocalDate ldEnd = LocalDate.of(year, 12, 31);
		List<InterventionMonth> interventions = interventionMonthDao
				.findByInterventionDateBetweenOrderById(DateUtil.toDate(ldStart), DateUtil.toDate(ldEnd));

		return interventionMonthsToPrint(interventions);
	}

	@Override
	public List<InterventionMonthVo> findByYearAndMonth(int year, int month) {
		LocalDate localDate = LocalDate.of(year, month, 1);
		List<InterventionMonth> listOfInterventionsMonthly = findByInterventionDateOrderByEquipementTypeNameAscIdAsc(
				DateUtil.toDate(localDate));
		if (listOfInterventionsMonthly.isEmpty() || listOfInterventionsMonthly == null) {
			return null;
		} else {
			return interventionMonthsToPrint(listOfInterventionsMonthly);

		}
	}

	@Override
	public List<InterventionMonthVo> findByYearAndMonthAndEquipement(int year, int month, String name) {
		LocalDate localDate = LocalDate.of(year, month, 1);
		Date theDate = DateUtil.toDate(localDate);
		InterventionMonth theIntervention = findByEquipementNameAndInterventionDate(name, theDate);
		if (theIntervention == null) {
			return null;
		} else {
			List<InterventionMonth> interventionMonths = new ArrayList<>();
			interventionMonths.add(theIntervention);
			return interventionMonthsToPrint(interventionMonths);
		}
	}

	public InterventionMonthDao getInterventionMonthDao() {
		return interventionMonthDao;
	}

	public void setInterventionMonthDao(InterventionMonthDao interventionMonthDao) {
		this.interventionMonthDao = interventionMonthDao;
	}

	public InterventionDayService getInterventionDayService() {
		return interventionDayService;
	}

	public void setInterventionDayService(InterventionDayService interventionDayService) {
		this.interventionDayService = interventionDayService;
	}

	@Override
	public InterventionMonth findById(Long id) {
		return interventionMonthDao.getOne(id);
	}

	@Override
	public void deleteAnomaly(long idMonth, long idAnomalie) {
		InterventionMonth interventionMonth = findById(idMonth);
		if (interventionMonth != null) {
			InterventionDay iDay = interventionMonth.getInterventionDays().stream()
					.filter(item -> item.getId() == idAnomalie).findFirst().orElse(null);
			if (iDay != null) {
				interventionMonth.getInterventionDays().remove(iDay);
				interventionMonthDao.saveAndFlush(interventionMonth);
			}
		}
	}
}
