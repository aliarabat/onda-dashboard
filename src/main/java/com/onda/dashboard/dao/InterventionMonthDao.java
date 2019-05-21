/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.dao;

import com.onda.dashboard.model.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onda.dashboard.model.InterventionMonth;

import java.util.Date;

/**
 * @author hp
 */
@Repository
public interface InterventionMonthDao extends JpaRepository<InterventionMonth, Long> {

    Equipement findByEquipementName(String name);

    InterventionMonth findByInterventionDate(Date dateIntervention);

    InterventionMonth findTopByEquipementNameOrderByInterventionDateDesc(String name);
}
