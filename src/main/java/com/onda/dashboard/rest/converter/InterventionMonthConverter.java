/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest.converter;

import com.onda.dashboard.model.InterventionMonth;
import com.onda.dashboard.util.DateUtil;
import com.onda.dashboard.rest.vo.InterventionMonthVo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            interventionMonth.setEquipement(new EquipementConverter().toItem(vo.getEquipementVo()));
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            try {
                interventionMonth.setInterventionDate(formatter.parse(vo.getDateIntervention()));
            } catch (ParseException ex) {
                Logger.getLogger(InterventionMonthConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
            interventionMonth.setInterventionDays(new InterventionDayConverter().toItem(vo.getInterventionPartDaysVo()));
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
            interventionMonthVo.setEquipementVo(new EquipementConverter().toVo(item.getEquipement()));
            interventionMonthVo.setDateIntervention(DateUtil.toString(DateUtil.fromDate(item.getInterventionDate())));
            interventionMonthVo.setInterventionPartDaysVo(new InterventionDayConverter().toVo(item.getInterventionDays()));
            return interventionMonthVo;
        }
    }

}
