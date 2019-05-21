/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest.converter;

import com.onda.dashboard.model.Equipement;
import com.onda.dashboard.rest.vo.EquipementVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class EquipementConverter extends AbstractConverter<Equipement, EquipementVo> {

    @Override
    public Equipement toItem(EquipementVo vo) {
        if (vo == null) {
            return null;
        } else {
            Equipement equipement = new Equipement();
            equipement.setId(vo.getId());
            equipement.setExpectedBreakPeriodMaintenance(new TimingConverter().toItem(vo.getExpectedBreakPeriodMaintenance()));
            equipement.setName(vo.getName());
            equipement.setType(new TypeConverter().toItem(vo.getTypeVo()));
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
            equipementVo.setExpectedBreakPeriodMaintenance(new TimingConverter().toVo(item.getExpectedBreakPeriodMaintenance()));
            equipementVo.setName(item.getName());
            equipementVo.setTypeVo(new TypeConverter().toVo(item.getType()));
            return equipementVo;
        }
    }

}
