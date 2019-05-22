package com.onda.dashboard.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class InterventionDay implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String anomaly;
    private LocalDateTime interventionStart;
    private LocalDateTime interventionEnd;
    private LocalDateTime callIntervention;
    @OneToOne(cascade = CascadeType.ALL)
    private Timing breakDuration;
    @OneToOne(cascade = CascadeType.ALL)
    private Timing reparationDuration;
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

   

    public Timing getBreakDuration() {
        return breakDuration;
    }

    public void setBreakDuration(Timing breakDuration) {
        this.breakDuration = breakDuration;
    }

    public Timing getReparationDuration() {
        return reparationDuration;
    }

    public void setReparationDuration(Timing reparationDuration) {
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
