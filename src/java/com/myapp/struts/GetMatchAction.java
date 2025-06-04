package com.myapp.struts;

/**
 *
 * @author pablo
 */
import com.opensymphony.xwork2.ActionSupport;

public class GetMatchAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // Lógica para obtener detalles de un Match
        return SUCCESS;
    }
}