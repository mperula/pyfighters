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
import com.myapp.struts.model.Script;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class VerScriptAjaxAction extends ActionSupport {

    private int script_id;
    private InputStream inputStream;

    @Override
    public String execute() {
        Script s = FighterDAO.getScriptById(script_id);
        if (s != null) {
            inputStream = new ByteArrayInputStream(s.getCode().getBytes());
            return SUCCESS;
        }
        return ERROR;
    }

    // Getters y setters
    public void setScript_id(int script_id) {
        this.script_id = script_id;
    }

    public int getScript_id() {
        return script_id;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
