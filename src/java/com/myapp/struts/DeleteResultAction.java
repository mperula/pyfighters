package com.myapp.struts;

/**
 *
 * @author pablo
 */
import com.opensymphony.xwork2.ActionSupport;

public class DeleteResultAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // Lógica para eliminar un Resultado
        return SUCCESS;
    }
}
