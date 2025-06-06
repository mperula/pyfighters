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
import java.util.List;

public class ComunidadAction extends ActionSupport {

    private List<Fighter> fighters;

    @Override
    public String execute() {
        fighters = FighterDAO.getAll();
        return SUCCESS;
    }

    public List<Fighter> getFighters() {
        return fighters;
    }

    public void setFighters(List<Fighter> fighters) {
        this.fighters = fighters;
    }
}
