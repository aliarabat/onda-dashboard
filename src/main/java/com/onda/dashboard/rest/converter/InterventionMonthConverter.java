/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.onda.dashboard.model.InterventionMonth;
import com.onda.dashboard.rest.vo.InterventionMonthVo;

/**
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
			// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			interventionMonth.setInterventionDate(new Date(vo.getDateIntervention()));
			interventionMonth
					.setInterventionDays(new InterventionDayConverter().toItem(vo.getInterventionPartDaysVo()));
			return interventionMonth;
		}
	}

	@Override
	public InterventionMonthVo toVo(InterventionMonth item) {
		if (item == null) {
			return null;
		} else {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			InterventionMonthVo interventionMonthVo = new InterventionMonthVo();
			interventionMonthVo.setId(item.getId());
			interventionMonthVo.setEquipementVo(new EquipementConverter().toVo(item.getEquipement()));
			interventionMonthVo.setDateIntervention(formatter.format(item.getInterventionDate()));
			interventionMonthVo
					.setInterventionPartDaysVo(new InterventionDayConverter().toVo(item.getInterventionDays()));
			return interventionMonthVo;
		}
	}
}
