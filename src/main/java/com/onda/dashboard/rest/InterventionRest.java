package com.onda.dashboard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.onda.dashboard.service.EquipementService;

@RestController
@RequestMapping("dashboard-api")
public class InterventionRest {

	@Autowired
	private EquipementService equipementService;

	public EquipementService getEquipementService() {
		return equipementService;
	}

	public void setEquipementService(EquipementService equipementService) {
		this.equipementService = equipementService;
	}

}
