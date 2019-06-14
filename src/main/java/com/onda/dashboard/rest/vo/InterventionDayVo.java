/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest.vo;

/**
 *
 * @author AMINE
 */
public class InterventionDayVo {

	private Long id;
	private String anomaly;
	private String interventionStart;
	private String interventionEnd;
	private String callIntervention;
	private TimingVo breakDuration;
	private TimingVo reparationDuration;
	private String breakNumber;
	private String actions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TimingVo getBreakDuration() {
		return breakDuration;
	}

	public void setBreakDuration(TimingVo breakDuration) {
		this.breakDuration = breakDuration;
	}

	public TimingVo getReparationDuration() {
		return reparationDuration;
	}

	public void setReparationDuration(TimingVo reparationDuration) {
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
