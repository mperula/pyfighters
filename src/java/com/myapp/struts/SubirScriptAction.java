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

import java.io.*;
import java.util.Map;
import java.nio.file.Files;

public class SubirScriptAction extends ActionSupport {

    private String title;
    private File scriptFile;
    private String scriptFileContentType;
    private String scriptFileFileName;

    @Override
    public String execute() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        Fighter user = (Fighter) session.get("usuario");
        if (user == null || scriptFile == null || title == null || title.trim().isEmpty()) {
            return ERROR;
        }

        try {
            String code = new String(Files.readAllBytes(scriptFile.toPath()));
            boolean ok = FighterDAO.insertScript(user.getFighter_id(), title, code);
            return ok ? SUCCESS : ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    // Getters & Setters
    public void setTitle(String title) { this.title = title; }
    public void setScriptFile(File scriptFile) { this.scriptFile = scriptFile; }
    public void setScriptFileContentType(String type) { this.scriptFileContentType = type; }
    public void setScriptFileFileName(String name) { this.scriptFileFileName = name; }
}