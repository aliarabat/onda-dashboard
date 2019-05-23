/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service;

import com.onda.dashboard.model.Type;
import com.onda.dashboard.rest.vo.TypeVo;

/**
 *
 * @author AMINE
 */
public interface TypeService {

    Type findByName(String name);

    int createType(Type typeVo);
}
