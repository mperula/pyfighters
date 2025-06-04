package com.myapp.struts;

/**
 *
 * @author pablo
 */
import com.opensymphony.xwork2.ActionSupport;

public class SearchMatchesAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // Lógica para buscar Matches por fecha o participantes
        return SUCCESS;
    }
}
