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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onda.dashboard.dao.EquipementDao;
import com.onda.dashboard.model.Equipement;
import com.onda.dashboard.model.InterventionMonth;
import com.onda.dashboard.model.Type;
import com.onda.dashboard.service.EquipementService;
import com.onda.dashboard.util.JasperUtil;
import java.util.Arrays;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
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
        return equipementDao.findByName(name);
    }

    public EquipementDao getEquipementDao() {
        return equipementDao;
    }

    public void setEquipementDao(EquipementDao equipementDao) {
        this.equipementDao = equipementDao;
    }

    @Override
    public void printDoc(HttpServletResponse response) {
        JasperPrint jasperPrint = null;
        Map<String, Object> params = new HashMap<>();

        List<InterventionMonth> list = new ArrayList();
        list.add(new InterventionMonth(new Equipement("equipe 1", new Type("type1"))));
        list.add(new InterventionMonth(new Equipement("equipe 2", new Type("type1"))));
        list.add(new InterventionMonth(new Equipement("equipe 3", new Type("type1"))));

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachement; filename=\"dashboard.pdf" + "\"");
        OutputStream out = null;

        try {
            out = response.getOutputStream();
            jasperPrint = new JasperUtil().generateDoc(list, params, "Dashboard_Detail.jasper", "pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        } catch (FileNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (JRException | IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }

    @Override
    public void printGraph(HttpServletResponse response) {
        JasperPrint jasperPrint = null;
        Map<String, Object> params = new HashMap<>();

        /*List<Intervention> list = new ArrayList<>();
        List<InterventionMonth> lst=new ArrayList();
        lst.add(new InterventionMonth(new Equipement("equipe 1")));
        lst.add(new InterventionMonth(new Equipement("equipe 2")));
        lst.add(new InterventionMonth(new Equipement("equipe 3")));
        list.add(new Intervention("type1", lst));*/
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachement; filename=\"tbf.pdf" + "\"");
        OutputStream out = null;

        try {
            out = response.getOutputStream();
            //jasperPrint = new JasperUtil().generateDoc(list, params, "TBF_Detail.jasper", "pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        } catch (FileNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (JRException | IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

}
