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
public class InterventionVo {

    private Long id;
    private EquipementVo equipementVo;
    private InterventionMonthVo interventionMonthVo;

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

    public InterventionMonthVo getInterventionMonthVo() {
        return interventionMonthVo;
    }

    public void setInterventionMonthVo(InterventionMonthVo interventionMonthVo) {
        this.interventionMonthVo = interventionMonthVo;
    }
    
    
}
