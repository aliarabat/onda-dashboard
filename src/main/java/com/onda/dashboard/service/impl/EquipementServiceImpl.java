/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onda.dashboard.dao.EquipementDao;
import com.onda.dashboard.model.Equipement;
import com.onda.dashboard.model.Type;
import com.onda.dashboard.service.EquipementService;
import com.onda.dashboard.service.TypeService;

import java.util.List;

/**
 * @author hp
 */
@Service
public class EquipementServiceImpl implements EquipementService {

    @Autowired
    private EquipementDao equipementDao;
    @Autowired
    private TypeService typeService;

    @Override
    public int createEquipement(List<Equipement> equipements) {

        for (Equipement equi : equipements) {
            Type checkType = typeService.findByName(equi.getType().getName());
            if (checkType == null) {
                return -1;
            } else {
                Equipement equipement = equipementDao.findByName(equi.getName());
                if (equipement == null) {
                    equi.setType(checkType);
                    equipementDao.save(equi);
                } else {
                    return -2;
                }
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
            Type checkType = typeService.findByName(newEquipement.getType().getName());
            if (checkType == null) {
                return -2;
            } else {
                Equipement chEquipement = findByName(newEquipement.getName());

                if (chEquipement == null) {
                    checkEquipement.setExpectedBreakPeriodMaintenance(newEquipement.getExpectedBreakPeriodMaintenance());
                    checkEquipement.setType(checkType);
                    checkEquipement.setName(newEquipement.getName());
                    equipementDao.save(checkEquipement);
                    return 1;
                } else {
                    return -3;
                }
            }
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
        return equipementDao.findByName(name);
    }

    @Override
    public List<Equipement> findAll() {
        return equipementDao.findAll();
    }

    @Override
    public Equipement findById(Long id) {
        return equipementDao.getOne(id);
    }

    public EquipementDao getEquipementDao() {
        return equipementDao;
    }

    public void setEquipementDao(EquipementDao equipementDao) {
        this.equipementDao = equipementDao;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

}
