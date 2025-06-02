/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.model;

/**
 *
 * @author empichi
 */

public class Fighter {
    private int fighter_id;
    private String username;
    private String email;
    private String password;

    // Getters y Setters
    public int getFighter_id() { return fighter_id; }
    public void setFighter_id(int fighter_id) { this.fighter_id = fighter_id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

