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
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import java.util.HashMap;

public class LikeSessionAction extends ActionSupport {

    private int script_id;

    @Override
    public String execute() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        Map<Integer, Integer> likes = (Map<Integer, Integer>) session.get("likes");

        if (likes == null) {
            likes = new HashMap<>();
        }

        likes.put(script_id, likes.getOrDefault(script_id, 0) + 1);
        session.put("likes", likes);

        return SUCCESS;
    }

    // Setter requerido por Struts para inyectar el valor desde el formulario
    public void setScript_id(int script_id) {
        this.script_id = script_id;
    }

    // Getter añadido por claridad y robustez
    public int getScript_id() {
        return script_id;
    }
}
