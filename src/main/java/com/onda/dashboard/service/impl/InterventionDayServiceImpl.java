
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
import java.util.List;

/**
 *
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
    public int createInterventionDay( List<InterventionDay> InterventionDays) {

        //Equipement equipement = equipementService.findByName(name);
        for (InterventionDay interventionDay : InterventionDays) {
            interventionDay.setAnomaly(interventionDay.getAnomaly());
            interventionDay.setCallIntervention(interventionDay.getCallIntervention());
            interventionDay.setInterventionStart(interventionDay.getInterventionStart());
            interventionDay.setInterventionEnd(interventionDay.getInterventionEnd());
            interventionDay.setBreakNumber(interventionDay.getBreakNumber());
            Duration dRep = Duration.between(interventionDay.getCallIntervention(), interventionDay.getInterventionStart());
            Duration dBreak = Duration.between(interventionDay.getCallIntervention(), interventionDay.getInterventionEnd());
            long dRepHours = dRep.toHours();
            long dRepMinute = Long.parseLong((dRep.toMinutes()) % 60 + "");
            Timing tRep = new Timing((int) dRepHours, (int) dRepMinute);
            interventionDay.setReparationDuration(tRep);
            long dBreakHours = dBreak.toHours();
            long dBreakMinute = Long.parseLong((dBreak.toMinutes()) % 60 + "");
            Timing tBreak = new Timing((int) dBreakHours, (int) dBreakMinute);
            interventionDay.setBreakDuration(tBreak);
            interventionDayDao.save(interventionDay);

        }//interventionMonthService.createInterventionMonth(name, InterventionDays);

        return 1;
    }

    @Override
    public List<InterventionDay> findAll() {
        return interventionDayDao.findAll();
    }

    @Override
    public InterventionDay save(InterventionDay interventionDay) {
        return interventionDayDao.save(interventionDay);
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
