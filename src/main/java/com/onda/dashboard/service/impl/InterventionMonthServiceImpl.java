/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service.impl;

import com.onda.dashboard.dao.InterventionMonthDao;
import com.onda.dashboard.model.Equipement;
import com.onda.dashboard.model.InterventionDay;
import com.onda.dashboard.model.InterventionMonth;
import com.onda.dashboard.rest.converter.InterventionMonthConverter;
import com.onda.dashboard.rest.vo.InterventionMonthVo;
import com.onda.dashboard.service.InterventionDayService;
import com.onda.dashboard.service.InterventionMonthService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.onda.dashboard.util.DateUtil;
import com.onda.dashboard.util.InterventionMonthComparator;
import com.onda.dashboard.util.JasperUtil;
import com.onda.dashboard.util.MonthUtil;
import com.onda.dashboard.util.NumberUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<InterventionMonthVo> interventionMonthsToPrint(List<InterventionMonth> interventionMonths) {
        if (interventionMonths != null && !interventionMonths.isEmpty()) {
            List<InterventionMonthVo> interventionMonthVos = new InterventionMonthConverter().toVo(interventionMonths);
            interventionMonthVos.forEach(interventionMonthVo -> {
                interventionMonthVo.getInterventionPartDaysVo().forEach(interventionDayVo -> {
                    int housMaintenance = NumberUtil.toInt(interventionMonthVo.getCurrentBreakPeriodMaintenance().getHour())
                            + NumberUtil.toInt(interventionDayVo.getReparationDuration().getHour());
                    int minutesMaintenance = NumberUtil.toInt(interventionMonthVo.getCurrentBreakPeriodMaintenance().getMinute())
                            + NumberUtil.toInt(interventionDayVo.getReparationDuration().getMinute());
                    if (minutesMaintenance >= 60) {
                        ++housMaintenance;
                        minutesMaintenance = minutesMaintenance - 60;
                    }
                    interventionMonthVo.getCurrentBreakPeriodMaintenance().setHour(NumberUtil.fromIntToString(housMaintenance));
                    interventionMonthVo.getCurrentBreakPeriodMaintenance().setMinute(NumberUtil.fromIntToString(minutesMaintenance));
                });
                // some calculs for monthly data
                int functionalityTimeWantedHours = DateUtil
                        .lenghtOfMonth(DateUtil.fromStringToLocalDate(interventionMonthVo.getDateIntervention())) * 24;
                int functionalityTimeWantedMinutes = 0;
                int functionalityTimeAchievedHours = functionalityTimeWantedHours - NumberUtil
                        .toInt(interventionMonthVo.getEquipementVo().getExpectedBreakPeriodMaintenance().getHour());
                int functionalityTimeAchievedMinutes = functionalityTimeWantedMinutes - NumberUtil
                        .toInt(interventionMonthVo.getEquipementVo().getExpectedBreakPeriodMaintenance().getMinute());
                if (functionalityTimeAchievedMinutes < 0) {
                    --functionalityTimeAchievedHours;
                    functionalityTimeAchievedMinutes = functionalityTimeAchievedMinutes + 60;
                }
                Double periodFunctionAchieved = functionalityTimeAchievedHours
                        + NumberUtil.toDouble("0." + (functionalityTimeAchievedMinutes * 10 / 6));
                // set infos to the main object
                interventionMonthVo.getFunctionalityTimeWanted().setHour(NumberUtil.fromIntToString(functionalityTimeWantedHours));
                interventionMonthVo.getFunctionalityTimeWanted().setMinute(NumberUtil.fromIntToString(functionalityTimeWantedMinutes));
                interventionMonthVo.getFunctionalityTimeAchieved().setHour(NumberUtil.fromIntToString(functionalityTimeAchievedHours));
                interventionMonthVo.getFunctionalityTimeAchieved().setMinute(NumberUtil.fromIntToString(functionalityTimeAchievedMinutes));
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
        JasperPrint jasperPrint = null;
        Map<String, Object> params = new HashMap<>();

        List<InterventionMonthVo> list = interventionMonthsToPrint(findByInterventionDateOrderByEquipementTypeNameAscIdAsc(
                DateUtil.toDate(LocalDate.of(year, month, 1))));
        String mois = MonthUtil.getMonth(month - 1);
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition",
                "attachement; filename=\"TableauDeBord" + mois + year + ".pdf" + "\"");
        OutputStream out = null;

        try {
            out = response.getOutputStream();
            jasperPrint = new JasperUtil().generateDoc(list, params,
                    "Dashboard_Detail.jasper", "pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        } catch (FileNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (JRException | IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }

    @Override
    public void printXlsx(HttpServletResponse response, int year, int month) {
        JasperPrint jasperPrint = null;
        Map<String, Object> params = new HashMap<>();

        List<InterventionMonthVo> list = interventionMonthsToPrint(findByInterventionDateOrderByEquipementTypeNameAscIdAsc(
                DateUtil.toDate(LocalDate.of(year, month, 1))));
        String mois = MonthUtil.getMonth(month - 1);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.addHeader("Content-Disposition", "attachement; filename=\"TableauDeBord" + mois + year + ".xlsx" + "\"");
        OutputStream out = null;

        try {
            out = response.getOutputStream();
            jasperPrint = new JasperUtil().generateDoc(list, params, "Dashboard_Detail.jasper", "xlsx");
            JRXlsxExporter exporter = new JRXlsxExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));

            SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
            reportConfig.setUseTimeZone(true);
            exporter.setConfiguration(reportConfig);
            exporter.exportReport();
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void printGraph(HttpServletResponse response, int year, int month, double object) {
        JasperPrint jasperPrint = null;
        Map<String, Object> params = new HashMap<>();

        List<InterventionMonthVo> list = interventionMonthsToPrint(
                findByInterventionDateOrderByEquipementTypeNameAscIdAsc(DateUtil.toDate(LocalDate.of(year, month, 1))));
        InterventionMonthVo interventionMonthMin = Collections.min(list, new InterventionMonthComparator());

        Double average = list.stream().mapToDouble(item -> item.getTbf()).average().getAsDouble();

        String mois = MonthUtil.getMonth(month - 1);

        params.put("tbfMin", interventionMonthMin.getTbf().intValue());
        params.put("object", NumberUtil.scaleDoubletoTwo(object));
        params.put("average", NumberUtil.scaleDoubletoTwo(average));
        params.put("month", mois);
        params.put("year", year);

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachement; filename=\"tbf" + mois + year + ".pdf" + "\"");
        OutputStream out = null;

        try {
            out = response.getOutputStream();
            jasperPrint = new JasperUtil().generateDoc(list, params, "TBF_Detail.jasper", "pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        } catch (FileNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (JRException | IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
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
    public List<InterventionMonthVo> findByYear(int year) {
        LocalDate ldStart = LocalDate.of(year, 1, 1);
        LocalDate ldEnd = LocalDate.of(year, 12, 31);
        List<InterventionMonth> interventions = interventionMonthDao.findByInterventionDateBetweenOrderById(DateUtil.toDate(ldStart), DateUtil.toDate(ldEnd));

        return interventionMonthsToPrint(interventions);
    }

    @Override
    public List<InterventionMonthVo> findByYearAndMonth(int year, int month) {
        LocalDate localDate = LocalDate.of(year, month, 1);
        List<InterventionMonth> listOfInterventionsMonthly = findByInterventionDateOrderByEquipementTypeNameAscIdAsc(DateUtil.toDate(localDate));
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
}
