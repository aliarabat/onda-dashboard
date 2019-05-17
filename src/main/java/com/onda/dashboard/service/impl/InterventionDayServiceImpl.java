/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service.impl;

import com.onda.dashboard.dao.InterventionDayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onda.dashboard.service.InterventionDayService;

/**
 *
 * @author hp
 */
@Service
public class InterventionDayServiceImpl implements InterventionDayService {

    @Autowired
    private InterventionDayDao interventionDayDao;

    public InterventionDayDao getInterventionDayDao() {
        return interventionDayDao;
    }

    public void setInterventionDayDao(InterventionDayDao interventionDayDao) {
        this.interventionDayDao = interventionDayDao;
    }

}
