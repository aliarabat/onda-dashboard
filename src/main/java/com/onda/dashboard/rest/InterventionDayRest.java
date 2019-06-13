/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest;

import com.onda.dashboard.model.InterventionDay;
import com.onda.dashboard.model.InterventionMonth;
import com.onda.dashboard.rest.converter.EquipementConverter;
import com.onda.dashboard.rest.converter.InterventionDayConverter;
import com.onda.dashboard.rest.converter.TimingConverter;
import com.onda.dashboard.rest.vo.EquipementVo;
import com.onda.dashboard.rest.vo.InterventionDayVo;
import com.onda.dashboard.rest.vo.InterventionMonthVo;
import com.onda.dashboard.rest.vo.TimingVo;
import com.onda.dashboard.service.InterventionDayService;
import com.onda.dashboard.service.InterventionMonthService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = {"https://onda-marrakech.firebaseapp.com", "https://onda-menara.tk"})
@RequestMapping("/dashboard-api/dashboards/interventionDay")
public class InterventionDayRest {

    @Autowired
    private InterventionDayService interventionDayService;
    @Autowired
    private InterventionMonthService interventionMonthService;

    @Autowired
    private InterventionDayConverter interventionDayConverter;

    @Autowired
    private TimingConverter timingConverter;

    @PostMapping("/equipement/{name}")
    public int createInterventionDay(@PathVariable String name, @RequestBody List<InterventionDayVo> InterventionDaysVo) {
        List<InterventionDay> InterventionDays = interventionDayConverter.toItem(InterventionDaysVo);
        return interventionDayService.createInterventionDay(name, InterventionDays);
    }

    @GetMapping("/")
    public List<InterventionDayVo> findAll() {
        return interventionDayConverter.toVo(interventionDayService.findAll());
    }

    @GetMapping("/call/{ldt1}/startOrAnd/{ldt2}")
    public TimingVo getDuration(@PathVariable String ldt1, @PathVariable String ldt2) {
        return timingConverter.toVo(interventionDayService.getDuration(ldt1, ldt2));
    }

    public InterventionDayConverter getInterventionDayConverter() {
        return interventionDayConverter;
    }

    public void setInterventionDayConverter(InterventionDayConverter interventionDayConverter) {
        this.interventionDayConverter = interventionDayConverter;
    }

    public InterventionDayService getInterventionDayService() {
        return interventionDayService;
    }

    public InterventionMonthService getInterventionMonthService() {
        return interventionMonthService;
    }

    public void setInterventionMonthService(InterventionMonthService interventionMonthService) {
        this.interventionMonthService = interventionMonthService;
    }

    public void setInterventionDayService(InterventionDayService interventionDayService) {
        this.interventionDayService = interventionDayService;
    }

    public TimingConverter getTimingConverter() {
        return timingConverter;
    }

    public void setTimingConverter(TimingConverter timingConverter) {
        this.timingConverter = timingConverter;
    }

}
