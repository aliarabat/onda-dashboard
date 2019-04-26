/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.bean.Employee;
import com.onda.personnel.rest.converter.AbstractConverter;
import com.onda.personnel.rest.vo.EmployeeVo;
import com.onda.personnel.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/personnel-api/personnels/employee")
public class EmployeeRest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    @Qualifier("employeeConverter")
    private AbstractConverter<Employee, EmployeeVo> employeeConverter;

    @GetMapping("/matricule/{matricule}")
    public EmployeeVo findByMatricule(@PathVariable Integer matricule) {
        Employee checkEmployee = employeeService.findByMatricule(matricule);
        return employeeConverter.toVo(checkEmployee);
    }

    @PostMapping("/")
    public int createEmployee(@RequestBody EmployeeVo employeeVo) {
        Employee employee = employeeConverter.toItem(employeeVo);
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/matricule/{matricule}")
    public int editEmployee(@PathVariable Integer matricule, @RequestBody EmployeeVo newEmployeeVo) {
        Employee newEmployee = employeeConverter.toItem(newEmployeeVo);
        return employeeService.editEmployee(matricule, newEmployee);
    }

    @GetMapping("/")
    public List<EmployeeVo> findAll() {
        return employeeConverter.toVo(employeeService.findAll());
    }

    @DeleteMapping("/matricule/{matricule}")
    public int deleteEmployee(@PathVariable Integer matricule) {
        return employeeService.deleteEmployee(matricule);
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public AbstractConverter<Employee, EmployeeVo> getEmployeeConverter() {
        return employeeConverter;
    }

    public void setEmployeeConverter(AbstractConverter<Employee, EmployeeVo> employeeConverter) {
        this.employeeConverter = employeeConverter;
    }

}
