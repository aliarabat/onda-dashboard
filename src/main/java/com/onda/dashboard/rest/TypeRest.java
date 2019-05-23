/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest;

import com.onda.dashboard.model.Type;
import com.onda.dashboard.rest.converter.TypeConverter;
import com.onda.dashboard.rest.vo.TypeVo;
import com.onda.dashboard.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/dashboard-api/dashboards/type")
public class TypeRest {
    @Autowired
    private TypeService typeService;

    @PostMapping("/")
    public int createType(@RequestBody TypeVo typeVo) {
        return typeService.createType(new TypeConverter().toItem(typeVo));
    }

    @GetMapping("/name/{name}")
    public Type findByName(@PathVariable String name) {
        return typeService.findByName(name);
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }
}
