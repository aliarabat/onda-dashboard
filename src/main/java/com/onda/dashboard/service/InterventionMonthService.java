/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service;

import com.onda.dashboard.model.Equipement;
import com.onda.dashboard.model.InterventionDay;
import com.onda.dashboard.model.InterventionMonth;
import com.onda.dashboard.rest.vo.InterventionMonthVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * @author hp
 */
public interface InterventionMonthService {

    void createInterventionMonth(Equipement equipement, List<InterventionDay> InterventionDays);

    List<InterventionMonth> findByInterventionDateOrderByEquipementTypeNameAscIdAsc(Date dateIntervention);

    List<InterventionMonthVo> interventionMonthsToPrint(List<InterventionMonth> interventionMonths);

    InterventionMonth findByEquipementName(String name);

    InterventionMonth findByEquipementNameAndInterventionDate(String name, Date dateIntervention);

    List<InterventionMonthVo> findAll();

    void printDoc(HttpServletResponse response, int year, int month);

    //void printXlsx(HttpServletResponse response, int year, int month);
    void printGraph(HttpServletResponse response, int year, int month, double object);

    List<InterventionMonthVo> findByYear(int year);

    List<InterventionMonthVo> findByYearAndMonth(int year, int month);

    List<InterventionMonthVo> findByYearAndMonthAndEquipement(int year, int month, String name);

    InterventionMonth findById(Long id);

}
