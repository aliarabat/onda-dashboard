/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onda.dashboard.model.InterventionMonth;
import com.onda.dashboard.rest.converter.InterventionMonthConverter;
import com.onda.dashboard.rest.vo.InterventionMonthVo;
import com.onda.dashboard.service.InterventionMonthService;
import com.onda.dashboard.util.DateUtil;

/**
 *
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = { "https://onda-marrakech.firebaseapp.com", "https://onda-menara.tk", "http://localhost:4200" })
@RequestMapping("/dashboard-api/dashboards/interventionMonth")
public class InterventionMonthRest {

	@Autowired
	private InterventionMonthService interventionMonthService;

	@GetMapping("/")
	public List<InterventionMonthVo> findAll() {
		return interventionMonthService.findAll();
	}

	@GetMapping("/id/{id}")
	public InterventionMonthVo findById(@PathVariable Long id) {
		return new InterventionMonthConverter().toVo(interventionMonthService.findById(id));
	}

	@GetMapping("/year/{year}")
	public List<InterventionMonthVo> findByYear(@PathVariable int year) {
		return interventionMonthService.findByYear(year);
	}

	@GetMapping("/search/year/{year}/month/{month}")
	public List<InterventionMonthVo> findByYearAndMonth(@PathVariable int year, @PathVariable int month) {
		return interventionMonthService.findByYearAndMonth(year, month);
	}

	@GetMapping("/year/{year}/month/{month}/name/{name}")
	public List<InterventionMonthVo> findByYearAndMonthAndEquipement(@PathVariable int year, @PathVariable int month,
			@PathVariable String name) {
		return interventionMonthService.findByYearAndMonthAndEquipement(year, month, name);
	}

	@GetMapping("/year/{year}/month/{month}")
	public List<InterventionMonthVo> findByInterventionDateOrderByEquipementTypeNameAscIdAsc(@PathVariable int year,
			@PathVariable int month) {
		return interventionMonthsToPrint(
				interventionMonthService.findByInterventionDateOrderByEquipementTypeNameAscIdAsc(
						DateUtil.toDate(LocalDate.of(year, month, 1))));
	}

	private List<InterventionMonthVo> interventionMonthsToPrint(List<InterventionMonth> interventionMonths) {
		return interventionMonthService.interventionMonthsToPrint(interventionMonths);
	}

	@GetMapping("/interventiontoprint/year/{year}/month/{month}")
	public InterventionMonthVo findTopByInterventionDate(@PathVariable int year, @PathVariable int month) {
		return new InterventionMonthConverter().toVo(interventionMonthService
				.findTopByInterventionDate(DateUtil.getFirstDayOfMonthByYearAndMonth(year, month)));
	}

	@GetMapping(value = "/printdoc/year/{year}/month/{month}", produces = MediaType.APPLICATION_PDF_VALUE)
	public void printDoc(HttpServletResponse response, @PathVariable int year, @PathVariable int month) {
		interventionMonthService.printDoc(response, year, month);
	}

	@GetMapping(value = "/printgraph/year/{year}/month/{month}/object/{object}", produces = MediaType.APPLICATION_PDF_VALUE)
	public void printGraph(HttpServletResponse response, @PathVariable int year, @PathVariable int month,
			@PathVariable double object) {
		interventionMonthService.printGraph(response, year, month, object);
	}

	@DeleteMapping("/idMonth/{idMonth}/idAnomalie/{idAnomalie}")
	public void deleteAnomaly(@PathVariable long idMonth, @PathVariable long idAnomalie) {
		interventionMonthService.deleteAnomaly(idMonth, idAnomalie);
	}

	public InterventionMonthService getInterventionMonthService() {
		return interventionMonthService;
	}

	public void setInterventionMonthService(InterventionMonthService interventionMonthService) {
		this.interventionMonthService = interventionMonthService;
	}
}
