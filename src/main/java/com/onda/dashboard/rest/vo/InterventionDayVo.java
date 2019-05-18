/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest.vo;

import com.onda.dashboard.bean.Equipement;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author AMINE
 */
public class InterventionDayVo {

    private Long id;
    private EquipementVo equipementVo;
    private String anomaly;
    private String interventionStart;
    private String interventionEnd;
    private String callIntervention;
    private String breakDuration;
    private String reparationDuration;
    private String breakNumber;
    private String actions;

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

    public String getAnomaly() {
        return anomaly;
    }

    public void setAnomaly(String anomaly) {
        this.anomaly = anomaly;
    }

    public String getInterventionStart() {
        return interventionStart;
    }

    public void setInterventionStart(String interventionStart) {
        this.interventionStart = interventionStart;
    }

    public String getInterventionEnd() {
        return interventionEnd;
    }

    public void setInterventionEnd(String interventionEnd) {
        this.interventionEnd = interventionEnd;
    }

    public String getCallIntervention() {
        return callIntervention;
    }

    public void setCallIntervention(String callIntervention) {
        this.callIntervention = callIntervention;
    }

    public String getBreakDuration() {
        return breakDuration;
    }

    public void setBreakDuration(String breakDuration) {
        this.breakDuration = breakDuration;
    }

    public String getReparationDuration() {
        return reparationDuration;
    }

    public void setReparationDuration(String reparationDuration) {
        this.reparationDuration = reparationDuration;
    }

    public String getBreakNumber() {
        return breakNumber;
    }

    public void setBreakNumber(String breakNumber) {
        this.breakNumber = breakNumber;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

}
