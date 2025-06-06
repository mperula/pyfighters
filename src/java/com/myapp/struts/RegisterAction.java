/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

/**
 *
 * @author empichi
 */

import com.myapp.struts.dao.FighterDAO;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
    private String username;
    private String email;
    private String password;

    @Override
    public String execute() {
        if (username == null || username.isEmpty() ||
            email == null || email.isEmpty() ||
            password == null || password.isEmpty()) {
            addActionError("Todos los campos son obligatorios.");
            return ERROR;
        }

        if (FighterDAO.existsUser(email, username)) {
            addActionError("El usuario o correo ya existen.");
            return ERROR;
        }

        boolean registrado = FighterDAO.register(username, email, password);
        if (registrado) {
            return "login";
        } else {
            addActionError("Error al registrar el usuario.");
            return ERROR;
        }
    }

    // Getters y Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
