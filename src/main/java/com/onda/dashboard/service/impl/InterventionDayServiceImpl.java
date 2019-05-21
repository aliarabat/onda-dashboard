
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service.impl;

import com.onda.dashboard.bean.InterventionDay;
import com.onda.dashboard.bean.InterventionMonth;
import com.onda.dashboard.bean.Timing;
import com.onda.dashboard.common.util.DateUtil;
import com.onda.dashboard.dao.InterventionDayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onda.dashboard.service.InterventionDayService;
import com.onda.dashboard.service.InterventionMonthService;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author hp
 */
@Service
public class InterventionDayServiceImpl implements InterventionDayService {

    @Autowired
    private InterventionDayDao interventionDayDao;
    @Autowired
    private InterventionMonthService interventionMonthService;
    
    
     @Override
    public int createInterventionDay(String name , List<InterventionDay> InterventionDays) {

        for (InterventionDay interventionDay : InterventionDays) {
            interventionDay.setAnomaly(interventionDay.getAnomaly());
            interventionDay.setCallIntervention(interventionDay.getCallIntervention());
            interventionDay.setInterventionStart(interventionDay.getInterventionStart());
            interventionDay.setInterventionEnd(interventionDay.getInterventionEnd());
            interventionDay.setBreakNumber(interventionDay.getBreakNumber());
            LocalDateTime call = DateUtil.convertToLocalDateTime(interventionDay.getCallIntervention());
            LocalDateTime start = DateUtil.convertToLocalDateTime(interventionDay.getInterventionStart());
            LocalDateTime end = DateUtil.convertToLocalDateTime(interventionDay.getInterventionEnd());
            Duration dRep = Duration.between(call, start);
            Duration dBreak = Duration.between(call, end);
            long dRepHours = dRep.toHours();
            long dRepMinute = Long.parseLong((dRep.toMinutes())%60+"");
            Timing tRep = new Timing((int) dRepHours, (int) dRepMinute);
            interventionDay.setReparationDuration(tRep);
            long dBreakHours = dBreak.toHours();
            long dBreakMinute = Long.parseLong((dBreak.toMinutes())%60+"");
            Timing tBreak = new Timing((int) dBreakHours, (int) dBreakMinute);
            interventionDay.setBreakDuration(tBreak);
            //interventionDayDao.save(interventionDay);

        }
                    interventionMonthService.createInterventionMonth(name, InterventionDays);

        return 1;
    }
 @Override
    public InterventionDay save(InterventionDay interventionDay) {
       return interventionDayDao.save(interventionDay);
    }
    public InterventionMonthService getInterventionMonthService() {
        return interventionMonthService;
    }

    public void setInterventionMonthService(InterventionMonthService interventionMonthService) {
        this.interventionMonthService = interventionMonthService;
    }

    public InterventionDayDao getInterventionDayDao() {
        return interventionDayDao;
    }

    public void setInterventionDayDao(InterventionDayDao interventionDayDao) {
        this.interventionDayDao = interventionDayDao;
    }

   

   
}
