package com.onda.dashboard.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.persistence.OneToOne;

@Entity
public class InterventionDay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Equipement equipement;
    private String anomaly;
    private LocalDateTime interventionStart;
    private LocalDateTime interventionEnd;
    private LocalDateTime callIntervention;
    private Duration breakDuration;
    private Duration reparationDuration;
    private Integer breakNumber;
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

    public LocalDateTime getInterventionStart() {
        return interventionStart;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public void setInterventionStart(LocalDateTime interventionStart) {
        this.interventionStart = interventionStart;
    }

    public LocalDateTime getInterventionEnd() {
        return interventionEnd;
    }

    public void setInterventionEnd(LocalDateTime interventionEnd) {
        this.interventionEnd = interventionEnd;
    }

    public LocalDateTime getCallIntervention() {
        return callIntervention;
    }

    public void setCallIntervention(LocalDateTime callIntervention) {
        this.callIntervention = callIntervention;
    }

    public Duration getBreakDuration() {
        return breakDuration;
    }

    public void setBreakDuration(Duration breakDuration) {
        this.breakDuration = breakDuration;
    }

    public Duration getReparationDuration() {
        return reparationDuration;
    }

    public void setReparationDuration(Duration reparationDuration) {
        this.reparationDuration = reparationDuration;
    }

    public Integer getBreakNumber() {
        return breakNumber;
    }

    public void setBreakNumber(Integer breakNumber) {
        this.breakNumber = breakNumber;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InterventionDay)) {
            return false;
        }
        InterventionDay other = (InterventionDay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
