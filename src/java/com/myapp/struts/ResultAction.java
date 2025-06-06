/**
 *
 * @author pablo
 */
package com.myapp.struts;

import com.myapp.struts.dao.BaseDAO;
import com.myapp.struts.dao.MatchDAO;
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
    private String resultado;

    private List<Result> results;
    private Result result;
    private Map<Integer, String> matchOptions;
    private Map<Integer, String> fighterOptions;
    private Map<String, String> resultOptions;

    public List<Result> getResults() {
        return results;
    }

    public Result getResult() {
        return result;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Map<String, String> getResultOptions() {
        return resultOptions;
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

            int fighter1Id = getFighter1IdFromMatch(conn, matchId);
            int fighter2Id = getFighter2IdFromMatch(conn, matchId);

            switch (resultado) {
                case "1":
                    r.setWinnerId(fighter1Id);
                    r.setLoserId(fighter2Id);
                    r.setDraw(0);
                    break;
                case "2":
                    r.setWinnerId(fighter2Id);
                    r.setLoserId(fighter1Id);
                    r.setDraw(0);
                    break;
                case "X":
                default:
                    r.setWinnerId(0);
                    r.setLoserId(0);
                    r.setDraw(1);
                    break;
            }

            dao.createResult(r);

            // Guardar el resultado textual en el Match
            String updateMatch = "UPDATE Matches SET result = ? WHERE match_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateMatch)) {
                stmt.setString(1, resultado);
                stmt.setInt(2, matchId);
                stmt.executeUpdate();
            }

            return SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al crear resultado: " + e.getMessage());
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

            // Obtener el matchId antes de eliminar
            Result resultToDelete = dao.getResult(resultId);
            if (resultToDelete == null) {
                addActionError("Resultado no encontrado.");
                return ERROR;
            }

            int matchIdToUpdate = resultToDelete.getMatchId();

            // Eliminar el resultado
            dao.deleteResult(resultId);

            // Limpiar el campo 'result' del combate correspondiente
            MatchDAO matchDAO = new MatchDAO(conn);
            matchDAO.clearResultField(matchIdToUpdate);

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
                isDraw = result.getIsDraw();
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
        resultOptions = new LinkedHashMap<>();

        try (Connection conn = BaseDAO.getConnection()) {
            // Cargar combates sin resultado
            String sqlMatches = "SELECT m.match_id FROM Matches m "
                    + "LEFT JOIN Results r ON m.match_id = r.match_id "
                    + "WHERE r.result_id IS NULL";
            try (PreparedStatement stmt = conn.prepareStatement(sqlMatches);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("match_id");
                    matchOptions.put(id, "Combate #" + id);
                }
            }
            if (matchOptions.isEmpty()) {
                matchOptions.put(-1, "No hay combates disponibles");
            }

            // Cargar luchadores
            String sqlFighters = "SELECT fighter_id, username FROM Fighters";
            try (PreparedStatement stmt = conn.prepareStatement(sqlFighters);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    fighterOptions.put(rs.getInt("fighter_id"), rs.getString("username"));
                }
            }

            // Cargar opciones de resultado
            resultOptions.put("1", "Gana Luchador 1");
            resultOptions.put("2", "Gana Luchador 2");
            resultOptions.put("X", "Empate");

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

    private int getFighter2IdFromMatch(Connection conn, int matchId) {
        String sql = "SELECT fighter2_id FROM Matches WHERE match_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matchId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("fighter2_id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getFighter1IdFromMatch(Connection conn, int matchId) {
        String sql = "SELECT fighter1_id FROM Matches WHERE match_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matchId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("fighter1_id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // valor por defecto si no se encuentra
    }

}
