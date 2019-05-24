/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.dao;

import com.onda.dashboard.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AMINE
 */
@Repository
public interface TypeDao extends JpaRepository<Type, Long> {

    public Type findByReferenceAndName(String reference, String name);

    public Type findByName(String name);
}
