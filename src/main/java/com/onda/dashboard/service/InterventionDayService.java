/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service;

import com.onda.dashboard.bean.Equipement;
import com.onda.dashboard.bean.InterventionDay;
import java.util.List;

/**
 *
 * @author hp
 */
public interface InterventionDayService {
    
 public int createInterventionDay(String  name , List<InterventionDay> InterventionDays);
 
 public InterventionDay save (InterventionDay interventionDay);
}
