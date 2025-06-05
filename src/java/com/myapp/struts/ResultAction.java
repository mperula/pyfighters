/**
 *
 * @author pablo
 */
package com.myapp.struts;

import com.myapp.struts.dao.BaseDAO;
import com.myapp.struts.dao.ResultDAO;
import com.myapp.struts.model.Result;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ResultAction extends ActionSupport {

    private int resultId;
    private int matchId;
    private int winnerId;
    private int loserId;
    private int isDraw;

    private List<Result> results;
    private Result result;
    private Map<Integer, String> matchOptions;
    private Map<Integer, String> fighterOptions;

    public List<Result> getResults() {
        return results;
    }

    public Result getResult() {
        return result;
    }

    public Map<Integer, String> getMatchOptions() {
        return matchOptions;
    }

    public Map<Integer, String> getFighterOptions() {
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

    public int getIsDraw() {
        return isDraw;
    }

    public void setIsDraw(int isDraw) {
        this.isDraw = isDraw;
    }

    public String listResults() {
        try (Connection conn = BaseDAO.getConnection()) {
            ResultDAO dao = new ResultDAO(conn);
            results = dao.listResults();

            for (Result r : results) {
                r.setWinnerName(getFighterName(conn, r.getWinnerId()));
                r.setLoserName(getFighterName(conn, r.getLoserId()));
            }
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al listar resultados.");
            return ERROR;
        }
    }

    public String addResultForm() {
        loadOptions();
        return SUCCESS;
    }

    public String createResult() {
        try (Connection conn = BaseDAO.getConnection()) {
            ResultDAO dao = new ResultDAO(conn);
            Result r = new Result();
            r.setMatchId(matchId);
            r.setWinnerId(winnerId);
            r.setLoserId(loserId);
            r.setDraw(isDraw);
            dao.createResult(r);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al crear resultado.");
            loadOptions();
            return INPUT;
        }
    }

    public String updateResult() {
        try (Connection conn = BaseDAO.getConnection()) {
            ResultDAO dao = new ResultDAO(conn);
            Result r = new Result();
            r.setResultId(resultId);
            r.setMatchId(matchId);
            r.setWinnerId(winnerId);
            r.setLoserId(loserId);
            r.setDraw(isDraw);
            dao.updateResult(r);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al actualizar resultado.");
            loadOptions();
            return INPUT;
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
                isDraw = result.isDraw();
                loadOptions();
                return SUCCESS;
            } else {
                addActionError("Resultado no encontrado.");
                return ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al obtener el resultado.");
            return ERROR;
        }
    }

    public String searchResults() {
        try (Connection conn = BaseDAO.getConnection()) {
            ResultDAO dao = new ResultDAO(conn);
            results = dao.searchResults(winnerId, null);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al buscar resultados.");
            return ERROR;
        }
    }

    private void loadOptions() {
        matchOptions = new LinkedHashMap<>();
        fighterOptions = new LinkedHashMap<>();
        try (Connection conn = BaseDAO.getConnection()) {
            String sqlMatches = "SELECT match_id FROM Matches";
            try (PreparedStatement stmt = conn.prepareStatement(sqlMatches);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("match_id");
                    matchOptions.put(id, "Combate #" + id);
                }
            }

            String sqlFighters = "SELECT fighter_id, username FROM Fighters";
            try (PreparedStatement stmt = conn.prepareStatement(sqlFighters);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    fighterOptions.put(rs.getInt("fighter_id"), rs.getString("username"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFighterName(Connection conn, int fighterId) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT username FROM Fighters WHERE fighter_id = ?")) {
            stmt.setInt(1, fighterId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("username");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
