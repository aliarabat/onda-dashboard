/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.rest;

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

import com.onda.dashboard.model.Equipement;
import com.onda.dashboard.rest.converter.EquipementConverter;
import com.onda.dashboard.rest.vo.EquipementVo;
import com.onda.dashboard.service.EquipementService;

/**
 *
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = { "https://onda-marrakech.firebaseapp.com", "https://onda-menara.tk", "http://localhost:4200" })
@RequestMapping("/dashboard-api/dashboards/equipement")
public class EquipementRest {

	@Autowired
	private EquipementService equipementService;

	@Autowired
	private EquipementConverter equipementConverter;

	@PostMapping("/")
	public int createEquipement(@RequestBody List<EquipementVo> equipementsVo) {
		List<Equipement> equipements = equipementConverter.toItem(equipementsVo);
		return equipementService.createEquipement(equipements);
	}

	@GetMapping("/")
	public List<EquipementVo> findAll() {
		return new EquipementConverter().toVo(equipementService.findAll());
	}

	@GetMapping("/id/{id}")
	public EquipementVo findById(@PathVariable Long id) {
		return new EquipementConverter().toVo(equipementService.findById(id));
	}

	@PutMapping("/")
	public int editEquipement(@RequestBody EquipementVo newEquipementVo) {
		Equipement newEquipement = equipementConverter.toItem(newEquipementVo);
		return equipementService.editEquipement(newEquipement);
	}

	@DeleteMapping("/id/{id}")
	public int deleteEquipement(@PathVariable Long id) {
		return equipementService.deleteEquipement(id);
	}

	@GetMapping("/nameEquipement/{name}")
	public List<EquipementVo> findByTypeName(@PathVariable String name) {
		return equipementConverter.toVo(equipementService.findByTypeName(name));
	}

	public EquipementService getEquipementService() {
		return equipementService;
	}

	public void setEquipementService(EquipementService equipementService) {
		this.equipementService = equipementService;
	}

	public EquipementConverter getEquipementConverter() {
		return equipementConverter;
	}

	public void setEquipementConverter(EquipementConverter equipementConverter) {
		this.equipementConverter = equipementConverter;
	}

}
