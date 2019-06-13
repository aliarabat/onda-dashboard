/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest;

import com.onda.dashboard.model.Type;
import com.onda.dashboard.rest.converter.TypeConverter;
import com.onda.dashboard.rest.vo.EquipementVo;
import com.onda.dashboard.rest.vo.TypeVo;
import com.onda.dashboard.service.TypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = {"https://onda-marrakech.firebaseapp.com", "https://onda-menara.tk"})
@RequestMapping("/dashboard-api/dashboards/type")
public class TypeRest {

    @Autowired
    TypeService typeService;

    @GetMapping("/reference/{reference}/name/{name}")
    public TypeVo findByReferenceAndName(@PathVariable String reference, @PathVariable String name) {
        return new TypeConverter().toVo(typeService.findByReferenceAndName(reference, name));
    }

    @PostMapping("/")
    public int createType(@RequestBody TypeVo type) {
        return typeService.createType(new TypeConverter().toItem(type));
    }

    @PutMapping("/")
    public int editType(@RequestBody TypeVo newType) {
        return typeService.editType(new TypeConverter().toItem(newType));
    }

    @DeleteMapping("/id/{id}")
    public int deleteType(@PathVariable Long id) {
        return typeService.deleteType(id);
    }

    @GetMapping("/id/{id}")
    public TypeVo findById(@PathVariable Long id) {
        return new TypeConverter().toVo(typeService.findById(id));
    }

    @GetMapping("/name/{name}")
    public TypeVo findByName(@PathVariable String name) {
        return new TypeConverter().toVo(typeService.findByName(name));
    }

    @GetMapping("/")
    public List<TypeVo> findAllTypes() {
        return new TypeConverter().toVo(typeService.findAllTypes());
    }

  

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

}
