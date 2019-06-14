/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onda.dashboard.model.Type;

/**
 *
 * @author AMINE
 */
@Repository
public interface TypeDao extends JpaRepository<Type, Long> {

	Type findByReferenceAndName(String reference, String name);

	Type findByName(String name);
}
