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
import com.myapp.struts.model.Fighter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import java.util.Map;

public class LoginAction extends ActionSupport {

    private String email;
    private String password;
    private Fighter user;

    @Override
    public String execute() {
        //System.out.println("Email recibido: " + email);
        //System.out.println("Password recibido: " + password);
        user = FighterDAO.login(email, password);
        if (user != null) {
            //System.out.println("Login exitoso");
            Map<String, Object> session = ActionContext.getContext().getSession();
            session.put("usuario", user);
            return SUCCESS;
        } else {
            //System.out.println("Login fallido");
            addActionError("Correo o contraseña incorrectos.");
            return ERROR;
        }
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Fighter getUser() {
        return user;
    }

    public void setUser(Fighter user) {
        this.user = user;
    }
}
