package com.onda.dashboard.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class InterventionMonth {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dateIntervention;
    @OneToMany
    private List<InterventionDay> interventionPartDays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateIntervention() {
        return dateIntervention;
    }

    public void setDateIntervention(LocalDate dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

    public List<InterventionDay> getInterventionPartDays() {
        return interventionPartDays;
    }

    public void setInterventionPartDays(List<InterventionDay> interventionPartDays) {
        this.interventionPartDays = interventionPartDays;
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
