/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest.converter;

import com.onda.dashboard.bean.Intervention;
import com.onda.dashboard.bean.Timing;
import com.onda.dashboard.common.util.NumberUtil;
import com.onda.dashboard.rest.vo.EquipementVo;
import com.onda.dashboard.rest.vo.InterventionVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class InterventionConverter  extends AbstractConverter<Intervention, InterventionVo> {

    @Override
    public Intervention toItem(InterventionVo vo) {
           if (vo == null) {
            return null;
        } else {
            Intervention intervention = new Intervention();
            intervention.setId(vo.getId());
            intervention.setEquipement(new EquipementConverter().toItem(vo.getEquipementVo()));
            intervention.setInterventionMonth(new InterventionMonthConverter().toItem(vo.getInterventionMonthVo()));
           
            return intervention;
        }
    }

    @Override
    public InterventionVo toVo(Intervention item) {
              if (item == null) {
            return null;
        } else {
            InterventionVo interventionVo = new InterventionVo();
            interventionVo.setId(item.getId());
            interventionVo.setEquipementVo(new EquipementConverter().toVo(item.getEquipement()));
            interventionVo.setInterventionMonthVo(new InterventionMonthConverter().toVo(item.getInterventionMonth()));
           
            return interventionVo;
        }
    }
    
}
