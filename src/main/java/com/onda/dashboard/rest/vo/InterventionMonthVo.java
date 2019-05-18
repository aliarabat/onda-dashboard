/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest.vo;

import java.time.LocalDate;
import java.util.List;



/**
 *
 * @author AMINE
 */
public class InterventionMonthVo {

    private Long id;
    private String dateIntervention;
    private TimingVo expectedBreakPeriodMaintenance;
    private List<InterventionDayVo> interventionPartDaysVo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateIntervention() {
        return dateIntervention;
    }

    public void setDateIntervention(String dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

   

    public TimingVo getExpectedBreakPeriodMaintenance() {
        return expectedBreakPeriodMaintenance;
    }

    public void setExpectedBreakPeriodMaintenance(TimingVo expectedBreakPeriodMaintenance) {
        this.expectedBreakPeriodMaintenance = expectedBreakPeriodMaintenance;
    }

    public List<InterventionDayVo> getInterventionPartDaysVo() {
        return interventionPartDaysVo;
    }

    public void setInterventionPartDaysVo(List<InterventionDayVo> interventionPartDaysVo) {
        this.interventionPartDaysVo = interventionPartDaysVo;
    }
    
    
}
