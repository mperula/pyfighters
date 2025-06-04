/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

/**
 *
 * @author pablo
 */
import com.opensymphony.xwork2.ActionSupport;

public class ListMatchesAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // Lógica para listar todos los Matches
        return SUCCESS;
    }
}