package com.myapp.struts;

/**
 *
 * @author pablo
 */
import com.opensymphony.xwork2.ActionSupport;

public class CreateMatchAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // Lógica para crear un Match
        return SUCCESS;
    }
}