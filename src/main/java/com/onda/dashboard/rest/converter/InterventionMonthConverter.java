/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest.converter;

import com.onda.dashboard.bean.Intervention;
import com.onda.dashboard.bean.InterventionMonth;
import com.onda.dashboard.common.util.DateUtil;
import com.onda.dashboard.rest.vo.InterventionMonthVo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class InterventionMonthConverter extends AbstractConverter<InterventionMonth, InterventionMonthVo> {

    @Override
    public InterventionMonth toItem(InterventionMonthVo vo) {
        if (vo == null) {
            return null;
        } else {
            InterventionMonth interventionMonth = new InterventionMonth();
            interventionMonth.setId(vo.getId());
            interventionMonth.setExpectedBreakPeriodMaintenance(new TimingConverter().toItem(vo.getExpectedBreakPeriodMaintenance()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");

            interventionMonth.setDateIntervention(LocalDate.parse(vo.getDateIntervention(), formatter));
            interventionMonth.setInterventionPartDays(new InterventionDayConverter().toItem(vo.getInterventionPartDaysVo())); 
            interventionMonth.setExpectedBreakPeriodMaintenance(new TimingConverter().toItem(vo.getExpectedBreakPeriodMaintenance()));
            return interventionMonth;
        }
    }

    @Override
    public InterventionMonthVo toVo(InterventionMonth item) {
           if (item == null) {
            return null;
        } else {
            InterventionMonthVo interventionMonthVo = new InterventionMonthVo();
            interventionMonthVo.setId(item.getId());
            interventionMonthVo.setExpectedBreakPeriodMaintenance(new TimingConverter().toVo(item.getExpectedBreakPeriodMaintenance()));
            interventionMonthVo.setDateIntervention(DateUtil.toString(item.getDateIntervention()));
            interventionMonthVo.setInterventionPartDaysVo(new InterventionDayConverter().toVo(item.getInterventionPartDays())); 
            interventionMonthVo.setExpectedBreakPeriodMaintenance(new TimingConverter().toVo(item.getExpectedBreakPeriodMaintenance()));
            return interventionMonthVo;
        }
    }

}
