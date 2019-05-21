/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service;

import com.onda.dashboard.bean.Equipement;
import java.util.List;

/**
 *
 * @author hp
 */
public interface EquipementService {

    public int createEquipement(List<Equipement> equipements);

    public int editEquipement(Equipement newEquipement);
    
    public int deleteEquipement(Long id);
    
    public Equipement findByName(String name);
}
