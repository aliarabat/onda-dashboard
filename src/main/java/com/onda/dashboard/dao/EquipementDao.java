/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onda.dashboard.model.Equipement;
import java.util.List;

/**
 *
 * @author hp
 */
@Repository
public interface EquipementDao extends JpaRepository<Equipement, Long> {

    public Equipement findByName(String name);
    
    public List<Equipement> findByTypeName(String name);
}
