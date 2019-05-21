package com.onda.dashboard.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.exolab.castor.types.DateTime;

@Entity
public class InterventionDay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String anomaly;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date interventionStart;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date interventionEnd;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date callIntervention;
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

    public Date getInterventionStart() {
        return interventionStart;
    }

    public void setInterventionStart(Date interventionStart) {
        this.interventionStart = interventionStart;
    }

    public Date getInterventionEnd() {
        return interventionEnd;
    }

    public void setInterventionEnd(Date interventionEnd) {
        this.interventionEnd = interventionEnd;
    }

    public Date getCallIntervention() {
        return callIntervention;
    }

    public void setCallIntervention(Date callIntervention) {
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
