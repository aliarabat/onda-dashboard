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
public class EquipementVo {

	private Long id;
	private String name;
	private TimingVo expectedBreakPeriodMaintenance;
	private TypeVo typeVo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TimingVo getExpectedBreakPeriodMaintenance() {
		return expectedBreakPeriodMaintenance;
	}

	public TypeVo getTypeVo() {
		return typeVo;
	}

	public void setTypeVo(TypeVo typeVo) {
		this.typeVo = typeVo;
	}

	public void setExpectedBreakPeriodMaintenance(TimingVo expectedBreakPeriodMaintenance) {
		this.expectedBreakPeriodMaintenance = expectedBreakPeriodMaintenance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
