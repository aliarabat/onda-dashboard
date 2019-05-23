/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service.impl;

import com.onda.dashboard.dao.TypeDao;
import com.onda.dashboard.model.Type;
import com.onda.dashboard.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AMINE
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public Type findByName(String name) {
        return typeDao.findByName(name);
    }

    @Override
    public int createType(Type type) {
        Type typeCheck = findByName(type.getName());
        if (typeCheck == null) {
            typeDao.save(type);
            return 1;
        }
        return -1;
    }

    public TypeDao getTypeDao() {
        return typeDao;
    }

    public void setTypeDao(TypeDao typeDao) {
        this.typeDao = typeDao;
    }
}
