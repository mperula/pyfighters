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

public class ProfileAction extends ActionSupport {
    private Fighter fighter;

    @Override
    public String execute() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        Fighter usuario = (Fighter) session.get("usuario");

        if (usuario == null) {
            addActionError("Sesión expirada. Vuelve a iniciar sesión.");
            return ERROR;
        }

        this.fighter = FighterDAO.getById(usuario.getFighter_id());
        return SUCCESS;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }
}
