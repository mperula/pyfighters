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

public class ActualizarScriptAction extends ActionSupport {
    private int script_id;
    private String new_title;

    @Override
    public String execute() {
        if (FighterDAO.updateScriptTitle(script_id, new_title)) {
            return SUCCESS;
        }
        return ERROR;
    }

    // Getters y setters
    public int getScript_id() { return script_id; }
    public void setScript_id(int script_id) { this.script_id = script_id; }
    public String getNew_title() { return new_title; }
    public void setNew_title(String new_title) { this.new_title = new_title; }
}
