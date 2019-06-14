package com.onda.dashboard.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Ali
 */
@Entity
public class Equipement implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToOne(cascade = CascadeType.ALL)
	private Timing expectedBreakPeriodMaintenance;
	@OneToOne
	private Type type;

	public Equipement(String name, Type type, Timing expectedBreakPeriodMaintenance) {
		this.name = name;
		this.type = type;
		this.expectedBreakPeriodMaintenance = expectedBreakPeriodMaintenance;
	}

	public Equipement() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timing getExpectedBreakPeriodMaintenance() {
		return expectedBreakPeriodMaintenance;
	}

	public void setExpectedBreakPeriodMaintenance(Timing expectedBreakPeriodMaintenance) {
		this.expectedBreakPeriodMaintenance = expectedBreakPeriodMaintenance;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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
		if (!(object instanceof Equipement)) {
			return false;
		}
		Equipement other = (Equipement) object;
		return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
	}

}
