/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service;

import com.onda.dashboard.model.Type;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface TypeService {

    public Type findByReferenceAndName(String reference, String name);

    public int createType(Type type);

    public int editType(Type newType);

    public int deleteType(Long id);

    public Type findById(Long id);

    public Type findByName(String name);

    public List<Type> findAllTypes();
}
