/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onda.dashboard.model.InterventionMonth;

/**
 * @author hp
 */
@Repository
public interface InterventionMonthDao extends JpaRepository<InterventionMonth, Long> {

	InterventionMonth findByEquipementName(String name);

	InterventionMonth findByInterventionDate(Date dateIntervention);

	InterventionMonth findByEquipementNameAndInterventionDate(String name, Date dateIntrevention);

	List<InterventionMonth> findByInterventionDateOrderByEquipementTypeNameAscIdAsc(Date dateIntervention);

	List<InterventionMonth> findByInterventionDateBetweenOrderById(Date date1, Date date2);

	InterventionMonth findTopByInterventionDate(Date date);
}
