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
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import java.util.HashMap;
import java.util.Map;

public class ScriptsGlobalesAction extends ActionSupport {

    private List<Script> scripts;
    private String query;

    private Map<Integer, Integer> likesMap = new HashMap<>();

    @Override
    public String execute() {
        if (query != null && !query.trim().isEmpty()) {
            scripts = FighterDAO.searchScripts(query);
        } else {
            scripts = FighterDAO.getAllScripts();
        }

        Map<String, Object> session = ActionContext.getContext().getSession();
        Object obj = session.get("likes");
        if (obj != null) {
            likesMap = (Map<Integer, Integer>) obj;
        }

        return SUCCESS;
    }

    public List<Script> getScripts() {
        return scripts;
    }

    public void setScripts(List<Script> scripts) {
        this.scripts = scripts;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getLikesForScript(int scriptId) {
        return likesMap.getOrDefault(scriptId, 0);
    }
}
