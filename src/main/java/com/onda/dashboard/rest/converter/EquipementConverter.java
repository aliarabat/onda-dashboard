/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest.converter;

import com.onda.dashboard.bean.Equipement;
import com.onda.dashboard.bean.Timing;
import com.onda.dashboard.common.util.NumberUtil;
import com.onda.dashboard.rest.vo.EquipementVo;
import com.onda.dashboard.rest.vo.TimingVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class EquipementConverter extends AbstractConverter<Equipement, EquipementVo>{

    @Override
    public Equipement toItem(EquipementVo vo) {
             if (vo == null) {
            return null;
        } else {
            Equipement equipement = new Equipement();
            equipement.setId(vo.getId());
            equipement.setName(vo.getName());
            equipement.setType(vo.getType());
            return equipement;
        }
    }

    @Override
    public EquipementVo toVo(Equipement item) {
            if (item == null) {
            return null;
        } else {
            EquipementVo equipementVo = new EquipementVo();
            equipementVo.setId(item.getId());
            equipementVo.setName(item.getName());
            equipementVo.setType(item.getType());
            return equipementVo;
        }
    }
    
}
