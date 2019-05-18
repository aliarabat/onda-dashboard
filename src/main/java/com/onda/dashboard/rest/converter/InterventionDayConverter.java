/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest.converter;

import com.onda.dashboard.bean.Intervention;
import com.onda.dashboard.bean.InterventionDay;
import com.onda.dashboard.common.util.DateUtil;
import com.onda.dashboard.common.util.NumberUtil;
import com.onda.dashboard.rest.vo.InterventionDayVo;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

/**
 *
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            interventionDay.setCallIntervention(LocalDateTime.parse(vo.getCallIntervention() , formatter));
            interventionDay.setInterventionEnd(LocalDateTime.parse(vo.getInterventionEnd() , formatter));
            interventionDay.setInterventionStart(LocalDateTime.parse(vo.getInterventionStart() , formatter));
            interventionDay.setBreakDuration(Duration.parse(vo.getBreakDuration()));
            interventionDay.setReparationDuration(Duration.parse(vo.getReparationDuration()));
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
            interventionDayVo.setBreakNumber(NumberUtil.fromIntToString(item.getBreakNumber()));
            interventionDayVo.setCallIntervention(DateUtil.toString(item.getCallIntervention()));
            interventionDayVo.setInterventionEnd(DateUtil.toString(item.getInterventionEnd()));
            interventionDayVo.setInterventionStart(DateUtil.toString(item.getInterventionStart()));
            interventionDayVo.setBreakDuration(DateUtil.toString(item.getBreakDuration()));
            interventionDayVo.setReparationDuration(DateUtil.toString(item.getReparationDuration()));
            return interventionDayVo;
        }
    }

}
