/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.Day;
import com.onda.personnel.bean.Employee;
import com.onda.personnel.bean.Work;
import com.onda.personnel.bean.WorkDetail;
import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.common.util.DayComparator;
import com.onda.personnel.dao.WorkDetailDao;
import com.onda.personnel.rest.vo.WorkDetailVo;
import com.onda.personnel.service.DayService;
import com.onda.personnel.service.EmployeeService;
import com.onda.personnel.service.WorkService;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onda.personnel.service.WorkDetailService;

/**
 *
 * @author AMINE
 */
@Service
public class WorkDetailSeviceImpl implements WorkDetailService {

    @Autowired
    private WorkService workService;
    @Autowired
    private WorkDetailDao workDetailDao;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DayService dayService;

    private static final Logger log = LoggerFactory.getLogger(WorkDetailSeviceImpl.class);

    /*@Override
    public List<WorkDetail> findByWorkDetailDate(LocalDate workDetailDate) {
        if (workDetailDao.findByWorkDetailDate(workDetailDate).isEmpty()) {
            return null;
        } else {
            return workDetailDao.findByWorkDetailDate(workDetailDate);
        }
    }*/
    @Override
    public WorkDetail findByWorkDetailDate(LocalDate localDate) {
        return workDetailDao.findByWorkDetailDate(localDate);
    }

    @Override
    public void saveWorkDetail(WorkDetail workDetail) {
        workDetailDao.save(workDetail);
    }

    @Override
    public void createWorkDetail(Employee emp, List<Day> days) {
        Work work = workService.findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(emp.getMatricule());
        //WorkDetail workDetail = findByWorkDetailDate(workDetailDate);
        WorkDetail workDetail;
        LocalDate dayDate;
        int workDetailListLength = DateUtil.lenghtOfMonth(new Date());
        if (work == null || work.getWorkDetail() == null) {
            work = new Work(emp);
            //Date firstMondayOfMonth=DateUtil.toDate(DateUtil.getFirstMonday(DayOfWeek.MONDAY));
            Date firstDayOfMonth=DateUtil.getFirstDayOfMonth();
            workDetail = new WorkDetail(firstDayOfMonth);
            workDetailListLength = workDetailListLength - DateUtil.getFirstMonday(DayOfWeek.MONDAY).getDayOfMonth() + 1;
            dayDate = DateUtil.getFirstDayOfWeek();
        } else {
            workDetail = workDetailDao.getOne(work.getWorkDetail().getId());
            int size=workDetail.getDays().size();
            Day dayMin=Collections.min(workDetail.getDays(), new DayComparator());
            log.info("hahowa day minnnn =====> "+dayMin.getDayDate());
            log.info("hahowa size nta3 days tl workDertail ==> "+workDetail.getDays().size());
            workDetailListLength= DateUtil.lenghtOfMonth(workDetail.getWorkDetailDate())-dayMin.getDayDate().getDate()+1;
            System.out.println("hhha workDetailListLength ==> "+workDetailListLength);
            dayDate= DateUtil.fromDate(workDetail.getDays().get(size-1).getDayDate()).plusDays(1);
        }

        LocalDate ld = DateUtil.fromDate(workDetail.getWorkDetailDate()).plusMonths(1);
        WorkDetail newWorkDetail = new WorkDetail(DateUtil.toDate(ld));
        try {
            for (Day day : days) {
                if (workDetailListLength > workDetail.getDays().size()) {
                    setOtherInfos(workDetail, dayService.setDayInfos(day.getDayDetails(), DateUtil.toDate(dayDate)));
                } else {
                    setOtherInfos(newWorkDetail, dayService.setDayInfos(day.getDayDetails(), DateUtil.toDate(dayDate)));
                }
                dayDate=dayDate.plusDays(1);
            }
        } catch (NullPointerException e) {
            log.error("null in for-loop block " + e.getMessage());
        }

        if (newWorkDetail.getDays() == null || newWorkDetail.getDays().isEmpty()) {
            saveWorkDetail(workDetail);
            work.setWorkDetail(workDetail);
            workService.saveWork(work);
        } else {
            saveWorkDetail(workDetail);
            saveWorkDetail(newWorkDetail);
            Work newWork = new Work(emp, newWorkDetail);
            workService.saveWork(work);
            workService.saveWork(newWork);
        }
    }

    @Override
    public WorkDetail updateWorkDetail(WorkDetail workDetail) {
        WorkDetail wd=workDetailDao.getOne(workDetail.getId());
        if (wd==null){
            return null;
        }else{
            wd.getHjf().setHour(workDetail.getHjf().getHour());
            wd.getHjf().setMinute(workDetail.getHjf().getMinute());
            wd.getHn().setHour(workDetail.getHn().getHour());
            wd.getHn().setMinute(workDetail.getHn().getMinute());
            wd.setPan(workDetail.getPan());
            saveWorkDetail(wd);
            return wd;
        }
    }

    private void setOtherInfos(WorkDetail workDetail, Day day) {
        workDetail.setPan(workDetail.getPan() + day.getPan());
        workDetail.getHn().setHour(workDetail.getHn().getHour()+day.getHn().getHour());
        workDetail.getHn().setMinute(workDetail.getHn().getMinute()+day.getHn().getMinute());
        workDetail.getHjf().setHour(workDetail.getHjf().getHour()+day.getHe().getHour());
        workDetail.getHjf().setMinute(workDetail.getHjf().getMinute()+day.getHe().getMinute());
        workDetail.getDays().add(day);
    }

    public WorkDetailDao getWorkDetailDao() {
        return workDetailDao;
    }

    public void setWorkDetailDao(WorkDetailDao workDetailDao) {
        this.workDetailDao = workDetailDao;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public WorkService getWorkService() {
        return workService;
    }

    public void setWorkService(WorkService workService) {
        this.workService = workService;
    }

    public DayService getDayService() { return dayService; }

    public void setDayService(DayService dayService) { this.dayService = dayService; }
}
