/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.bean.Work;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.rest.converter.WorkConverter;
import com.onda.personnel.rest.vo.WorkVo;
import com.onda.personnel.service.WorkService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/personnel-api/personnels/work")
public class WorkRest {

    @Autowired
    private WorkService workService;

    @Autowired
    private WorkConverter workConverter;

    @GetMapping("/matricule/{matricule}/workDetailDate/{workDetailDate}")
    public WorkVo findByEmployeeMatriculeAndWorkDetailWorkDetailDate(@PathVariable Integer matricule, @PathVariable String workDetailDate) {
        return new WorkConverter().toVo(workService.findByEmployeeMatriculeAndWorkDetailTestDate(matricule,DateUtil.toDate(DateUtil.fromStringToLocalDate(workDetailDate)) ))  ;
    }

    @GetMapping("/matricule/{matricule}")
    public Work findTopByEmployeeMatriculeOrderByWorkDetailTestDateDesc(@PathVariable Integer matricule) {
        return workService.findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(matricule);
    }

    @GetMapping("matricule/{matricule}/annee/{annee}/")
    public List<WorkVo> findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(@PathVariable Integer matricule, @PathVariable Integer annee) {
        return new WorkConverter().toVo(workService.findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(matricule, annee));
    }

    @GetMapping("/annee/{annee}")
    public List<WorkVo> findAllByWorkDetailWorkDetailDateBetween(@PathVariable Integer annee) {
        return new WorkConverter().toVo(workService.findAllByWorkDetailWorkDetailDateBetween(annee));
    }

    @GetMapping("/workDate/{workDate}")
    public List<WorkVo> findWorksByDate(@PathVariable String workDate) {
        return new WorkConverter().toVo(workService.findWorksByDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(workDate))));
    }

    @GetMapping("/matricule/{matricule}/year/{year}/month/{month}")
    public WorkVo findByEmployeeMatriculeAndMonthAndYear(@PathVariable Integer matricule, @PathVariable int year, @PathVariable int month) {
        return new WorkConverter().toVo(workService.findByEmployeeMatriculeAndMonthAndYear(matricule, year, month));
    }

    @GetMapping("/year/{year}/month/{month}")
    public List<WorkVo> findByMonthAndYear(@PathVariable int year, @PathVariable int month) {
        return new WorkConverter().toVo(workService.findByMonthAndYear(year, month));
    }

    @GetMapping("/workDetailDate/{workDetailDate}")
    public List<WorkVo> findByWorkDetailWorkDetailDate(@PathVariable String workDetailDate) {
        return new WorkConverter().toVo(workService.findByWorkDetailWorkDetailDate(DateUtil.toDate(DateUtil.fromStringToLocalDate(workDetailDate))));
    }

    @GetMapping("/ckeckdates/matricule/{matricule}")
    public List<String> findFromDateToDate(@PathVariable Integer matricule) {
        return workService.findFromDateToDate(matricule);
    }

    public WorkService getWorkService() {
        return workService;
    }

    public void setWorkService(WorkService workService) {
        this.workService = workService;
    }

    public WorkConverter getWorkConverter() {
        return workConverter;
    }

    public void setWorkConverter(WorkConverter workConverter) {
        this.workConverter = workConverter;
    }
}
