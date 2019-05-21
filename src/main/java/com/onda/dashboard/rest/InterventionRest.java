package com.onda.dashboard.rest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.onda.dashboard.service.EquipementService;

@RestController
@RequestMapping("dashboard-api")
public class InterventionRest {

    @Autowired
    private EquipementService equipementService;

    @GetMapping("/")
    public void printDoc(HttpServletResponse response) {
        equipementService.printDoc(response);
    }

    @GetMapping("/graph/pdf")
    public void printGraph(HttpServletResponse response) {
        equipementService.printGraph(response);
    }

    public EquipementService getEquipementService() {
        return equipementService;
    }

    public void setEquipementService(EquipementService equipementService) {
        this.equipementService = equipementService;
    }

}
