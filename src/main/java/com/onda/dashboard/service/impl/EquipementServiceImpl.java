/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service.impl;

import com.onda.dashboard.bean.Equipement;
import com.onda.dashboard.dao.EquipementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onda.dashboard.service.EquipementService;

/**
 *
 * @author hp
 */
@Service
public class EquipementServiceImpl implements EquipementService {

    @Autowired
    private EquipementDao equipementDao;

    public int create(Equipement equipement) {
        Equipement e = findByReference(equipement.getReference());
        if (e != null) {
            return -1;
        } else {
            equipementDao.save(e);
            return 1;
        }
    }

    @Override
    public Equipement findByReference(String reference) {
        return equipementDao.findByReference(reference);
    }

    public EquipementDao getEquipementDao() {
        return equipementDao;
    }

    public void setEquipementDao(EquipementDao equipementDao) {
        this.equipementDao = equipementDao;
    }

}
