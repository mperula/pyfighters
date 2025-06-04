package com.myapp.struts;

/**
 *
 * @author pablo
 */
import com.opensymphony.xwork2.ActionSupport;

public class CreateResultAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // Lógica para crear un Resultado
        return SUCCESS;
    }
}
