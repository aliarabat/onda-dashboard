/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service.impl;

import com.onda.dashboard.dao.InterventionMonthDao;
import org.springframework.beans.factory.annotation.Autowired;
import com.onda.dashboard.service.InterventionService;

/**
 *
 * @author hp
 */
public class InterventionServiceImpl implements InterventionService {

    @Autowired
    private InterventionMonthDao interventionMonthDao;

    public InterventionMonthDao getInterventionMonthDao() {
        return interventionMonthDao;
    }

    public void setInterventionMonthDao(InterventionMonthDao interventionMonthDao) {
        this.interventionMonthDao = interventionMonthDao;
    }

}
