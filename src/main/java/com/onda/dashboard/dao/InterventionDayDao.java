/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onda.dashboard.model.InterventionDay;

/**
 *
 * @author hp
 */
@Repository
public interface InterventionDayDao extends JpaRepository<InterventionDay, Long> {

}
