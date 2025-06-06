/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

/**
 *
 * @author User
 */
import com.myapp.struts.dao.FighterDAO;
import com.myapp.struts.model.Fighter;
import com.myapp.struts.model.Script;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;

public class MisScriptsAction extends ActionSupport {

    private List<Script> scripts;

    @Override
    public String execute() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        Fighter user = (Fighter) session.get("usuario");
        if (user != null) {
            scripts = FighterDAO.getScriptsByFighterId(user.getFighter_id());
            return SUCCESS;
        }
        return ERROR;
    }

    public List<Script> getScripts() {
        return scripts;
    }

    public void setScripts(List<Script> scripts) {
        this.scripts = scripts;
    }
}
