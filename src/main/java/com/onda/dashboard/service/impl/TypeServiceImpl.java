/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service.impl;

import com.onda.dashboard.dao.TypeDao;
import com.onda.dashboard.model.Type;
import com.onda.dashboard.service.TypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AMINE
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeDao typeDao;

    @Override
    public Type findByReferenceAndName(String reference, String name) {
        return typeDao.findByReferenceAndName(reference, name);
    }

    @Override
    public int createType(Type type) {
        Type checkType = findByReferenceAndName(type.getReference(), type.getName());
        if(checkType!=null){
            return -1;
        }
        else{
            typeDao.save(type);
            return 1;
        }
    }

    @Override
    public int editType(Type newType) {
        Type checkType = findById(newType.getId());
        if(checkType==null){
            return -1;
        }
        else{
            checkType.setName(newType.getName());
            checkType.setReference(newType.getReference());
            typeDao.save(checkType);
            return 1;
        }
    }

    @Override
    public int deleteType(Long id) {
        Type checkType = findById(id);
        if (checkType == null) {
            return -1;
        } else {
            typeDao.delete(checkType);
            return 1;
        }

    }

    @Override
    public Type findById(Long id) {
        return typeDao.getOne(id);
    }

    @Override
    public Type findByName(String name) {
        return typeDao.findByName(name);
    }

    @Override
    public List<Type> findAllTypes() {
        return typeDao.findAll();
    }

    public TypeDao getTypeDao() {
        return typeDao;
    }

    public void setTypeDao(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

}
