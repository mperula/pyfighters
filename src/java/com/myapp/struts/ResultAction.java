package com.myapp.struts;

import com.myapp.struts.dao.BaseDAO;
import com.myapp.struts.dao.ResultDAO;
import com.myapp.struts.model.Result;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ResultAction extends ActionSupport {

    private int resultId;
    private int matchId;
    private int winnerId;
    private int loserId;
    private int draw;

    private List<Result> results = new ArrayList<>();
    private Result result;

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    public int getLoserId() {
        return loserId;
    }

    public void setLoserId(int loserId) {
        this.loserId = loserId;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public List<Result> getResults() {
        return results;
    }

    public Result getResult() {
        return result;
    }

    public String listResults() {
        try (Connection conn = BaseDAO.getConnection()) {
            ResultDAO dao = new ResultDAO(conn);
            results = dao.listResults();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al listar resultados.");
            return ERROR;
        }
    }

    public String searchResults() {
        try (Connection conn = BaseDAO.getConnection()) {
            ResultDAO dao = new ResultDAO(conn);
            Result res = dao.getResultByMatchId(matchId);
            if (res != null) {
                results.clear();
                results.add(res);
            }
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al buscar resultados.");
            return ERROR;
        }
    }

    public String deleteResult() {
        try (Connection conn = BaseDAO.getConnection()) {
            ResultDAO dao = new ResultDAO(conn);
            dao.deleteResult(resultId);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al eliminar resultado.");
            return ERROR;
        }
    }

    public String getResultDetails() {
        try (Connection conn = BaseDAO.getConnection()) {
            ResultDAO dao = new ResultDAO(conn);
            result = dao.getResult(resultId);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al obtener detalles del resultado.");
            return ERROR;
        }
    }

    public String deleteResultConfirm() {
        return getResultDetails();
    }
}
