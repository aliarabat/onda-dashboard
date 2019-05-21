/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest.converter;

import com.onda.dashboard.bean.InterventionDay;
import com.onda.dashboard.bean.Timing;
import com.onda.dashboard.common.util.DateUtil;
import com.onda.dashboard.common.util.NumberUtil;
import com.onda.dashboard.rest.vo.InterventionDayVo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.exolab.castor.types.DateTime;
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
           SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  

          
            try {
                interventionDay.setCallIntervention(formatter.parse(vo.getCallIntervention()));
            } catch (ParseException ex) {
                Logger.getLogger(InterventionDayConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            try {
                interventionDay.setInterventionEnd(formatter.parse(vo.getInterventionEnd()));
            } catch (ParseException ex) {
                Logger.getLogger(InterventionDayConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        ;
            try {
                interventionDay.setInterventionStart(formatter.parse(vo.getInterventionStart()));
            } catch (ParseException ex) {
                Logger.getLogger(InterventionDayConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        ;
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
            interventionDayVo.setBreakNumber(NumberUtil.fromIntToString(item.getBreakNumber()));
            interventionDayVo.setCallIntervention(DateUtil.toString(DateUtil.fromDate(item.getCallIntervention())));
            interventionDayVo.setInterventionEnd(DateUtil.toString(DateUtil.fromDate(item.getInterventionEnd())));
            interventionDayVo.setInterventionStart(DateUtil.toString(DateUtil.fromDate(item.getInterventionStart())));
            interventionDayVo.setBreakDuration(new TimingConverter().toVo(item.getBreakDuration()));
            interventionDayVo.setReparationDuration(new TimingConverter().toVo(item.getReparationDuration()));
            return interventionDayVo;
        }
    }

}
