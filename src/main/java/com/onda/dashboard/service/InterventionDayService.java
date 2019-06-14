/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service;

import com.onda.dashboard.model.InterventionDay;
import com.onda.dashboard.model.Timing;

import java.util.List;

/**
 * @author hp
 */
public interface InterventionDayService {

    int createInterventionDay(String name, List<InterventionDay> interventionDays);

    int modifyInterventionDay(String name, InterventionDay interventionDay);

    InterventionDay save(InterventionDay interventionDay);

    InterventionDay setInterventionDayInfos(InterventionDay interventionDay);

    List<InterventionDay> findAll();

    Timing getDuration(String ldt1, String ldt2);

}
