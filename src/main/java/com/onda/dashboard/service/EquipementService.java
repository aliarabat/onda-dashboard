/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service;

import java.util.List;

import com.onda.dashboard.model.Equipement;

/**
 * @author hp
 */
public interface EquipementService {

	int createEquipement(List<Equipement> equipements);

	int editEquipement(Equipement newEquipement);

	int deleteEquipement(Long id);

	Equipement findByName(String name);

	List<Equipement> findAll();

	Equipement findById(Long id);

	List<Equipement> findByTypeName(String name);

}
