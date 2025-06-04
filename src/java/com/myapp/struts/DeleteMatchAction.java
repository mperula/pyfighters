package com.myapp.struts;

/**
 *
 * @author pablo
 */
import com.opensymphony.xwork2.ActionSupport;

public class DeleteMatchAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // Lógica para eliminar un Match
        return SUCCESS;
    }
}
