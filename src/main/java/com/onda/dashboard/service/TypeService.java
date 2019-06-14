/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service;

import java.util.List;

import com.onda.dashboard.model.Type;

/**
 *
 * @author AMINE
 */
public interface TypeService {

	Type findByReferenceAndName(String reference, String name);

	int createType(Type type);

	int editType(Type newType);

	int deleteType(Long id);

	Type findById(Long id);

	Type findByName(String name);

	List<Type> findAllTypes();
}
