/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest;

import com.onda.dashboard.model.Equipement;
import com.onda.dashboard.model.InterventionDay;
import com.onda.dashboard.model.InterventionMonth;
import com.onda.dashboard.rest.converter.InterventionMonthConverter;
import com.onda.dashboard.rest.vo.InterventionMonthVo;
import com.onda.dashboard.service.InterventionMonthService;
import com.onda.dashboard.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/dashboard-api/dashboards/interventionMonth")
public class InterventionMonthRest {

	@Autowired
	private InterventionMonthService interventionMonthService;

	@GetMapping("/")
	public List<InterventionMonthVo> findAll() {
		return interventionMonthService.findAll();
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

	@GetMapping("/year/{year}/month/{month}/printdoc/pdf")
	public void printDoc(HttpServletResponse response, @PathVariable int year, @PathVariable int month) {
		interventionMonthService.printDoc(response, year, month);
	}

	@GetMapping("/year/{year}/month/{month}/object/{object}/printgraph/pdf")
	public void printGraph(HttpServletResponse response, @PathVariable int year, @PathVariable int month,
			@PathVariable double object) {
		interventionMonthService.printGraph(response, year, month, object);
	}

	public InterventionMonthService getInterventionMonthService() {
		return interventionMonthService;
	}

	public void setInterventionMonthService(InterventionMonthService interventionMonthService) {
		this.interventionMonthService = interventionMonthService;
	}
}
