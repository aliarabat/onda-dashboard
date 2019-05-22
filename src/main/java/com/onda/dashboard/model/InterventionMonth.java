/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author hp
 */
@Entity
public class InterventionMonth implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Equipement equipement;
    private LocalTime expectedBreakPeriodMaintenance;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateIntervention;
    @OneToMany
    private List<InterventionDay> interventionDays;

    public InterventionMonth(Equipement equipement) {
        this.equipement = equipement;
    }

    public InterventionMonth() {
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public Date getDateIntervention() {
        return dateIntervention;
    }

    public void setDateIntervention(Date dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

    public List<InterventionDay> getInterventionDays() {
        return interventionDays;
    }

    public void setInterventionDays(List<InterventionDay> interventionDays) {
        this.interventionDays = interventionDays;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getExpectedBreakPeriodMaintenance() {
        return expectedBreakPeriodMaintenance;
    }

    public void setExpectedBreakPeriodMaintenance(LocalTime expectedBreakPeriodMaintenance) {
        this.expectedBreakPeriodMaintenance = expectedBreakPeriodMaintenance;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InterventionMonth)) {
            return false;
        }
        InterventionMonth other = (InterventionMonth) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}