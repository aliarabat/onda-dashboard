
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service.impl;

import com.onda.dashboard.model.InterventionDay;
import com.onda.dashboard.dao.InterventionDayDao;
import com.onda.dashboard.model.Equipement;
import com.onda.dashboard.model.Timing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onda.dashboard.service.InterventionDayService;
import com.onda.dashboard.service.InterventionMonthService;
import com.onda.dashboard.service.EquipementService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hp
 */
@Service
public class InterventionDayServiceImpl implements InterventionDayService {

    @Autowired
    private InterventionDayDao interventionDayDao;
    @Autowired
    private InterventionMonthService interventionMonthService;
    @Autowired
    private EquipementService equipementService;

    @Override
    public int createInterventionDay(String name, List<InterventionDay> interventionDays) {
        Equipement equipement = equipementService.findByName(name);
        if (equipement == null) {
            return -1;
        } else if (interventionDays == null || interventionDays.isEmpty()) {
            return -2;
        } else {
            interventionMonthService.createInterventionMonth(equipement, interventionDays);
            return 1;
        }
    }

    @Override
    public int modifyInterventionDay(String name, InterventionDay interventionDay) {
        return 0;
    }

    @Override
    public InterventionDay save(InterventionDay interventionDay) {
        return interventionDayDao.save(interventionDay);
    }

    @Override
    public InterventionDay setInterventionDayInfos(InterventionDay interventionDay) {
        //retrieve date attrs
        LocalDateTime callIntervention = interventionDay.getCallIntervention();
        LocalDateTime startIntervention = interventionDay.getInterventionStart();
        LocalDateTime endIntervention = interventionDay.getInterventionEnd();
        Duration reparationPeriod = Duration.between(callIntervention, startIntervention);
        Duration breakPeriod = Duration.between(callIntervention, endIntervention);
        //set durations info
        interventionDay.setReparationDuration(ReparationAndBreakManipulations(reparationPeriod));
        interventionDay.setBreakDuration(ReparationAndBreakManipulations(breakPeriod));

        return save(interventionDay);
    }

    @Override
    public List<InterventionDay> findAll() {
        return interventionDayDao.findAll();
    }

    private Timing ReparationAndBreakManipulations(Duration period) {
        long hoursReparationDuration = period.toHours();
        long minutesReparationDuration = Long.parseLong((period.toMinutes() % 60) + "");
        return new Timing((int) hoursReparationDuration, (int) minutesReparationDuration);
    }

    @Override
    public Timing getDuration(String ldt1, String ldt2) {
        LocalDateTime ldt1Time = LocalDateTime.parse(ldt1);
        LocalDateTime ldt2Time = LocalDateTime.parse(ldt2);
        Duration duration = Duration.between(ldt1Time, ldt2Time);
        long durationHours = duration.toHours();
        long durationMinute = Long.parseLong((duration.toMinutes()) % 60 + "");
        return new Timing((int) durationHours, (int) durationMinute);

    }

    public InterventionMonthService getInterventionMonthService() {
        return interventionMonthService;
    }

    public void setInterventionMonthService(InterventionMonthService interventionMonthService) {
        this.interventionMonthService = interventionMonthService;
    }

    public InterventionDayDao getInterventionDayDao() {
        return interventionDayDao;
    }

    public void setInterventionDayDao(InterventionDayDao interventionDayDao) {
        this.interventionDayDao = interventionDayDao;
    }

    public EquipementService getEquipementService() {
        return equipementService;
    }

    public void setEquipementService(EquipementService equipementService) {
        this.equipementService = equipementService;
    }

}
