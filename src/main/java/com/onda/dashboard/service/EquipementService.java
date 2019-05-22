/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service;

import com.onda.dashboard.model.Equipement;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hp
 */
public interface EquipementService {

    void printDoc(HttpServletResponse response);

    public int createEquipement(List<Equipement> equipements);

    void printGraph(HttpServletResponse response);

    public int editEquipement(Equipement newEquipement);

    public int deleteEquipement(Long id);

    public Equipement findByName(String name);

}
