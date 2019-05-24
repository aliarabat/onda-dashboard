/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest.converter;

import com.onda.dashboard.model.InterventionDay;
import com.onda.dashboard.util.DateUtil;
import com.onda.dashboard.util.NumberUtil;
import com.onda.dashboard.rest.vo.InterventionDayVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

/**
 * @author AMINE
 */
@Component
public class InterventionDayConverter extends AbstractConverter<InterventionDay, InterventionDayVo> {

    @Override
    public InterventionDay toItem(InterventionDayVo vo) {
        if (vo == null) {
            return null;
        } else {
            InterventionDay interventionDay = new InterventionDay();
            interventionDay.setId(vo.getId());
            interventionDay.setAnomaly(vo.getAnomaly());
            interventionDay.setActions(vo.getActions());
            interventionDay.setBreakNumber(NumberUtil.toInt(vo.getBreakNumber()));
            interventionDay.setCallIntervention(LocalDateTime.parse(vo.getCallIntervention()));
            interventionDay.setInterventionEnd(LocalDateTime.parse(vo.getInterventionEnd()));
            interventionDay.setInterventionStart(LocalDateTime.parse(vo.getInterventionStart()));
            interventionDay.setBreakDuration(new TimingConverter().toItem(vo.getBreakDuration()));
            interventionDay.setReparationDuration(new TimingConverter().toItem(vo.getReparationDuration()));
            return interventionDay;
        }
    }

    @Override
    public InterventionDayVo toVo(InterventionDay item) {
        if (item == null) {
            return null;
        } else {
            InterventionDayVo interventionDayVo = new InterventionDayVo();
            interventionDayVo.setId(item.getId());
            interventionDayVo.setAnomaly(item.getAnomaly());
            interventionDayVo.setActions(item.getActions());
            interventionDayVo.setBreakNumber((item.getBreakNumber() + ""));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            interventionDayVo.setCallIntervention(formatter.format(item.getCallIntervention()));
            interventionDayVo.setInterventionEnd(formatter.format(item.getInterventionEnd()));
            interventionDayVo.setInterventionStart(formatter.format(item.getInterventionStart()));
            interventionDayVo.setBreakDuration(new TimingConverter().toVo(item.getBreakDuration()));
            interventionDayVo.setReparationDuration(new TimingConverter().toVo(item.getReparationDuration()));
            return interventionDayVo;
        }
    }

}