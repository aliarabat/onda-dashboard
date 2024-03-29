/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onda.dashboard.model.Equipement;

/**
 *
 * @author hp
 */
@Repository
public interface EquipementDao extends JpaRepository<Equipement, Long> {

	Equipement findByName(String name);

	List<Equipement> findByTypeName(String name);
}
