package com.myapp.struts;

import com.myapp.struts.dao.BaseDAO;
import com.myapp.struts.dao.ResultDAO;
import com.myapp.struts.model.Result;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultAction extends ActionSupport {

    private int resultId;
    private int matchId;
    private int winnerId;
    private int loserId;
    private int draw;

    private List<Result> results = new ArrayList<>();
    private Result result;
    private List<Map<String, String>> fighterOptions = new ArrayList<>();

    public List<Map<String, String>> getFighterOptions() {
        return fighterOptions;
    }

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

            if (result != null) {
                matchId = result.getMatchId();
                winnerId = result.getWinnerId();
                loserId = result.getLoserId();
                draw = result.getIsDraw();
            }

            // Cargar opciones de luchadores SIN crear clases nuevas
            fighterOptions.clear();
            String sql = "SELECT fighter_id, username FROM Fighters ORDER BY username ASC";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, String> option = new HashMap<>();
                    option.put("key", String.valueOf(rs.getInt("fighter_id")));
                    option.put("value", rs.getString("username"));
                    fighterOptions.add(option);
                }
            }

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

    public String updateResult() {
        try (Connection conn = BaseDAO.getConnection()) {
            ResultDAO dao = new ResultDAO(conn);
            Result updated = new Result();
            updated.setResultId(resultId);
            updated.setMatchId(matchId);
            updated.setWinnerId(winnerId);
            updated.setLoserId(loserId);
            updated.setDraw(draw);
            dao.updateResult(updated);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al actualizar resultado.");
            return ERROR;
        }
    }

}
