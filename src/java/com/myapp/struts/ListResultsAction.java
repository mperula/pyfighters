package com.myapp.struts;

/**
 *
 * @author pablo
 */
import com.opensymphony.xwork2.ActionSupport;

public class ListResultsAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // Lógica para listar todos los Resultados
        return SUCCESS;
    }
}