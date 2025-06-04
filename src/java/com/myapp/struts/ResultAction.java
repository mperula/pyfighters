/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

/**
 *
 * @author pablo
 */
import com.myapp.struts.model.Result;
import com.myapp.struts.dao.ResultDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

public class ResultAction extends ActionSupport {

    private Result result;
    private List<Result> results;
    private int resultId;
    private int fighterId;
    private int arenaId;

    public void setResult(Result result) {
        this.result = result;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getFighterId() {
        return fighterId;
    }

    public void setFighterId(int fighterId) {
        this.fighterId = fighterId;
    }

    public int getArenaId() {
        return arenaId;
    }

    public void setArenaId(int arenaId) {
        this.arenaId = arenaId;
    }

    public String createResult() {
        // Aquí iría la lógica para crear un Result
        return SUCCESS;
    }

    public String updateResult() {
        // Aquí iría la lógica para actualizar un Result
        return SUCCESS;
    }

    public String deleteResult() {
        // Aquí iría la lógica para eliminar un Result
        return SUCCESS;
    }

    public String getResult() {
        // Aquí iría la lógica para obtener un Result por ID
        return SUCCESS;
    }

    public String listResults() {
        // Aquí iría la lógica para listar todos los Results
        return SUCCESS;
    }

    public String searchResults() {
        // Aquí iría la lógica para buscar Results por luchador o arena
        return SUCCESS;
    }
}
