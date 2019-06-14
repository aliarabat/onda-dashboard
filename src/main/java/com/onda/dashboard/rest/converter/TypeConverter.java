/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest.converter;

import org.springframework.stereotype.Component;

import com.onda.dashboard.model.Type;
import com.onda.dashboard.rest.vo.TypeVo;

/**
 *
 * @author AMINE
 */
@Component
public class TypeConverter extends AbstractConverter<Type, TypeVo> {

	@Override
	public Type toItem(TypeVo vo) {
		if (vo == null) {
			return null;
		} else {
			Type type = new Type();
			type.setId(vo.getId());
			type.setName(vo.getName());
			type.setReference(vo.getReference());
			return type;
		}
	}

	@Override
	public TypeVo toVo(Type item) {
		if (item == null) {
			return null;
		} else {
			TypeVo typeVo = new TypeVo();
			typeVo.setId(item.getId());
			typeVo.setName(item.getName());
			typeVo.setReference(item.getReference());
			return typeVo;
		}
	}

}
