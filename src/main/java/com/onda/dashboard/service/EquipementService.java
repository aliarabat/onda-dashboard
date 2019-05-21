/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.service;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public interface EquipementService {

    void printDoc(HttpServletResponse response);

    void printGraph(HttpServletResponse response);
}
