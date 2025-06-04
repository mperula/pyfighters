package com.myapp.struts;

/**
 *
 * @author pablo
 */
import com.opensymphony.xwork2.ActionSupport;

public class GetResultAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // Lógica para obtener detalles de un Resultado
        return SUCCESS;
    }
}