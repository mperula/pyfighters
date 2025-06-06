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

public class DescargarScriptAction extends ActionSupport {

    private int script_id;
    private InputStream inputStream;
    private String fileName;

    @Override
    public String execute() {
        Script script = FighterDAO.getScriptById(script_id);
        if (script != null) {
            String content = script.getCode() != null ? script.getCode() : "";
            inputStream = new ByteArrayInputStream(content.getBytes());
            fileName = script.getTitle().replaceAll("\\s+", "_") + ".txt";
            return SUCCESS;
        }
        return ERROR;
    }

    // Getters y setters
    public int getScript_id() {
        return script_id;
    }

    public void setScript_id(int script_id) {
        this.script_id = script_id;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String getFileName() {
        return fileName;
    }
}
