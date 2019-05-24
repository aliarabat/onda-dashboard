/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest.vo;

import com.onda.dashboard.model.Timing;

import java.util.List;

/**
 * @author AMINE
 */
public class InterventionMonthVo {

    private Long id;
    private String dateIntervention;
    private EquipementVo equipementVo;
    private List<InterventionDayVo> interventionPartDaysVo;
    private TimingVo functionalityTimeWanted = new TimingVo();
    private TimingVo functionalityTimeAchieved = new TimingVo();
    private TimingVo currentBreakPeriodMaintenance = new TimingVo();
    private Double tbf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EquipementVo getEquipementVo() {
        return equipementVo;
    }

    public void setEquipementVo(EquipementVo equipementVo) {
        this.equipementVo = equipementVo;
    }

    public String getDateIntervention() {
        return dateIntervention;
    }

    public void setDateIntervention(String dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

    public List<InterventionDayVo> getInterventionPartDaysVo() {
        return interventionPartDaysVo;
    }

    public void setInterventionPartDaysVo(List<InterventionDayVo> interventionPartDaysVo) {
        this.interventionPartDaysVo = interventionPartDaysVo;
    }

    public TimingVo getFunctionalityTimeWanted() {
        return functionalityTimeWanted;
    }

    public void setFunctionalityTimeWanted(TimingVo functionalityTimeWanted) {
        this.functionalityTimeWanted = functionalityTimeWanted;
    }

    public TimingVo getFunctionalityTimeAchieved() {
        return functionalityTimeAchieved;
    }

    public void setFunctionalityTimeAchieved(TimingVo functionalityTimeAchieved) {
        this.functionalityTimeAchieved = functionalityTimeAchieved;
    }

    public TimingVo getCurrentBreakPeriodMaintenance() {
        return currentBreakPeriodMaintenance;
    }

    public void setCurrentBreakPeriodMaintenance(TimingVo currentBreakPeriodMaintenance) {
        this.currentBreakPeriodMaintenance = currentBreakPeriodMaintenance;
    }

    public Double getTbf() {
        return tbf;
    }

    public void setTbf(Double tbf) {
        this.tbf = tbf;
    }
}
