/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service.impl;

import com.onda.dashboard.bean.Equipement;
import com.onda.dashboard.bean.InterventionDay;
import com.onda.dashboard.bean.InterventionMonth;
import com.onda.dashboard.dao.InterventionMonthDao;
import com.onda.dashboard.service.EquipementService;
import com.onda.dashboard.service.InterventionDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onda.dashboard.service.InterventionMonthService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hp
 */
@Service
public class InterventionMonthServiceImpl implements InterventionMonthService {

    @Autowired
    private InterventionMonthDao interventionMonthDao;
    @Autowired
    private InterventionDayService interventionDayService;
    @Autowired
    private EquipementService equipementService;

    @Override
    public int createInterventionMonth(String name, List<InterventionDay> InterventionDays) {
        Equipement eq = equipementService.findByName(name);
        System.out.println("haaaaaaaa neme " + name);

        if (eq == null) {
            return -1;
        } else {
            for (InterventionDay InterventionDay : InterventionDays) {
                InterventionMonth interventionMonth = findByDateIntervention(InterventionDay.getInterventionStart());
                if (interventionMonth == null) {
                    InterventionMonth inter = new InterventionMonth();
                    inter.setEquipement(eq);
                    inter.setDateIntervention(new Date(InterventionDay.getInterventionStart().getYear(),InterventionDay.getInterventionStart().getMonth(),1));
                    inter.getInterventionPartDays().add(interventionDayService.save(InterventionDay));
                    interventionMonthDao.save(inter);

                }
            }
        }
        return 2;
    }

    @Override
    public Equipement findByEquipementName(String name) {
        System.out.println("naaaaame" + name);
        return interventionMonthDao.findByEquipementName(name);
    }

    @Override
    public InterventionMonth findByDateIntervention(Date dateIntervention) {
        return interventionMonthDao.findByDateIntervention(dateIntervention);
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

    public EquipementService getEquipementService() {
        return equipementService;
    }

    public void setEquipementService(EquipementService equipementService) {
        this.equipementService = equipementService;
    }

}
