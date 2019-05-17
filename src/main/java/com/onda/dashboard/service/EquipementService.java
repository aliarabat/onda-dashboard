/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service;

import com.onda.dashboard.bean.Equipement;

/**
 *
 * @author hp
 */
public interface EquipementService {

    public Equipement findByReference(String reference);

    public int create(Equipement equipement);

}
