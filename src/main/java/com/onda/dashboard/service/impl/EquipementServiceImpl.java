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
import java.util.List;

/**
 *
 * @author hp
 */
@Service
public class EquipementServiceImpl implements EquipementService {

    @Autowired
    private EquipementDao equipementDao;

    @Override
    public int createEquipement(List<Equipement> equipements) {
        for (Equipement equi : equipements) {
            Equipement equipement = equipementDao.findByName(equi.getName());
            if (equipement == null) {
                equipementDao.save(equi);
            }
        }
        return 1;

    }

    @Override
    public int editEquipement(Equipement newEquipement) {
        Equipement checkEquipement = equipementDao.getOne(newEquipement.getId());
        if (checkEquipement == null) {
            return -1;
        } else {
            equipementDao.save(checkEquipement);
            return 1;
        }
    }

    @Override
    public int deleteEquipement(Long id) {
        Equipement checkEquipement = equipementDao.getOne(id);
        if (checkEquipement == null) {
            return -1;

        } else {
            equipementDao.delete(checkEquipement);
            return 1;
        }
    }

       @Override
    public Equipement findByName(String name) {
        return  equipementDao.findByName(name);
    }
    public EquipementDao getEquipementDao() {
        return equipementDao;
    }

    public void setEquipementDao(EquipementDao equipementDao) {
        this.equipementDao = equipementDao;
    }

 

}
