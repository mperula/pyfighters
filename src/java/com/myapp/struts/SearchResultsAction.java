package com.myapp.struts;

/**
 *
 * @author pablo
 */
import com.opensymphony.xwork2.ActionSupport;

public class SearchResultsAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // Lógica para buscar Resultados por luchador o arena
        return SUCCESS;
    }
}
