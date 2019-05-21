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
import com.onda.dashboard.model.Timing;
import com.onda.dashboard.service.InterventionDayService;
import com.onda.dashboard.service.InterventionMonthService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.onda.dashboard.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hp
 */
@Service
public class InterventionMonthServiceImpl implements InterventionMonthService {

    @Autowired
    private InterventionMonthDao interventionMonthDao;

    @Autowired
    private InterventionDayService interventionDayService;

    @Override
    public void createInterventionMonth(Equipement equipement, List<InterventionDay> interventionDays) {

        for (InterventionDay interventionDay : interventionDays) {
            InterventionMonth interventionMonth = findTopByEquipementNameOrderByInterventionDateDesc(equipement.getName());
            Date dateIntervention = new Date(interventionDay.getCallIntervention().getYear(), interventionDay.getCallIntervention().getMonth(), 1);
            InterventionMonth newInterventionMonth = new InterventionMonth(DateUtil.toDate(DateUtil.fromDate(dateIntervention).plusMonths(1)));
            if (interventionMonth == null) {
                interventionMonth = new InterventionMonth(dateIntervention);
                interventionMonth.setEquipement(equipement);
            }

            if ((interventionDay.getCallIntervention().after(interventionMonth.getInterventionDate()) || interventionDay.getCallIntervention().compareTo(interventionMonth.getInterventionDate()) == 0) && interventionDay.getCallIntervention().before(newInterventionMonth.getInterventionDate())) {
                interventionMonth.getInterventionDays().add(interventionDayService.setInterventionDayInfos(interventionDay));
            } else {
                newInterventionMonth.getInterventionDays().add(interventionDayService.setInterventionDayInfos(interventionDay));
            }
            if (newInterventionMonth.getInterventionDays().size() == 0 || newInterventionMonth.getInterventionDays().isEmpty()) {
                interventionMonthDao.save(interventionMonth);
            } else {
                interventionMonthDao.save(interventionMonth);
                newInterventionMonth.setEquipement(equipement);
                interventionMonthDao.save(newInterventionMonth);
            }
        }
    }

    private InterventionMonth findTopByEquipementNameOrderByInterventionDateDesc(String name) {
        return interventionMonthDao.findTopByEquipementNameOrderByInterventionDateDesc(name);
    }

    @Override
    public Equipement findByEquipementName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InterventionMonth findByDateIntervention(Date dateIntervention) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
