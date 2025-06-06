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

public class ActualizarPerfilAction extends ActionSupport {

    private String username;
    private String email;
    private String password;

    @Override
    public String execute() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        Fighter usuario = (Fighter) session.get("usuario");

        if (usuario == null) {
            addActionError("Sesión expirada.");
            return ERROR;
        }

        // Actualizamos datos
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setPassword(password);

        boolean actualizado = FighterDAO.update(usuario);

        if (actualizado) {
            session.put("usuario", usuario);
            return SUCCESS;
        } else {
            addActionError("No se pudo actualizar.");
            return ERROR;
        }
    }

    // Getters y setters (usados por el formulario)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
}
