/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.onda.dashboard.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onda.dashboard.dao.EquipementDao;
import com.onda.dashboard.model.Equipement;
import com.onda.dashboard.model.InterventionMonth;
import com.onda.dashboard.model.Type;
import com.onda.dashboard.service.EquipementService;

import java.util.Arrays;

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
            Type type = typeService.findByName(equi.getType().getName());
            Equipement equipement = equipementDao.findByName(equi.getName());
            if (type != null && equipement == null) {
                equipement = new Equipement(equi.getName(), type, equi.getExpectedBreakPeriodMaintenance());
                equipementDao.save(equipement);
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
    public List<Equipement> findByTypeName(String name) {
        return equipementDao.findByTypeName(name);
    }

    @Override
    public Equipement findByName(String name) {
        return equipementDao.findByName(name);
    }

    @Override
    public List<Equipement> findAll() {
        return equipementDao.findAll();
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
