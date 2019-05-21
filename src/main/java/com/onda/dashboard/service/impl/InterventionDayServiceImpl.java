
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
import com.onda.dashboard.util.DateUtil;
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
        /*for (InterventionDay interventionDay : InterventionDays) {
            interventionDay.setAnomaly(interventionDay.getAnomaly());
            interventionDay.setCallIntervention(interventionDay.getCallIntervention());
            interventionDay.setInterventionStart(interventionDay.getInterventionStart());
            interventionDay.setInterventionEnd(interventionDay.getInterventionEnd());
            interventionDay.setBreakNumber(interventionDay.getBreakNumber());
            LocalDateTime call = DateUtil.convertToLocalDateTime(interventionDay.getCallIntervention());
            LocalDateTime start = DateUtil.convertToLocalDateTime(interventionDay.getInterventionStart());
            LocalDateTime end = DateUtil.convertToLocalDateTime(interventionDay.getInterventionEnd());
            Duration dRep = Duration.between(call, start);
            Duration dBreak = Duration.between(call, end);
            long dRepHours = dRep.toHours();
            long dRepMinute = Long.parseLong((dRep.toMinutes()) % 60 + "");
            Timing tRep = new Timing((int) dRepHours, (int) dRepMinute);
            interventionDay.setReparationDuration(tRep);
            long dBreakHours = dBreak.toHours();
            long dBreakMinute = Long.parseLong((dBreak.toMinutes()) % 60 + "");
            Timing tBreak = new Timing((int) dBreakHours, (int) dBreakMinute);
            interventionDay.setBreakDuration(tBreak);
            //interventionDayDao.save(interventionDay);

        }interventionMonthService.createInterventionMonth(name, InterventionDays);*/
    }

    @Override
    public InterventionDay save(InterventionDay interventionDay) {
        return interventionDayDao.save(interventionDay);
    }

    @Override
    public InterventionDay setInterventionDayInfos(InterventionDay interventionDay) {
        //retrieve date attrs
        LocalDateTime callIntervention = DateUtil.convertToLocalDateTime(interventionDay.getCallIntervention());
        LocalDateTime startIntervention = DateUtil.convertToLocalDateTime(interventionDay.getInterventionStart());
        LocalDateTime endIntervention = DateUtil.convertToLocalDateTime(interventionDay.getInterventionEnd());
        Duration reparationPeriod = Duration.between(callIntervention, startIntervention);
        Duration breakPeriod = Duration.between(callIntervention, endIntervention);
        //set durations info
        interventionDay.setReparationDuration(ReparationAndBreakManipulations(reparationPeriod));
        interventionDay.setBreakDuration(ReparationAndBreakManipulations(breakPeriod));

        return save(interventionDay);
    }

    private Timing ReparationAndBreakManipulations(Duration period) {
        long hoursReparationDuration = period.toHours();
        long minutesReparationDuration = Long.parseLong((period.toMinutes()) % 60 + "");
        return new Timing((int) hoursReparationDuration, (int) minutesReparationDuration);
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
