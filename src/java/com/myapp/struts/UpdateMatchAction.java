package com.myapp.struts;

/**
 *
 * @author pablo
 */
import com.opensymphony.xwork2.ActionSupport;

public class UpdateMatchAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // Lógica para actualizar un Match
        return SUCCESS;
    }
}