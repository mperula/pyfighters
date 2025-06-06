package com.myapp.struts;

import com.myapp.struts.dao.BaseDAO;
import com.myapp.struts.dao.MatchDAO;
import com.myapp.struts.dao.ResultDAO;
import com.myapp.struts.model.Match;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

public class MatchAction extends ActionSupport {

    private int matchId;
    private int fighter1Id;
    private int fighter2Id;
    private int arenaId;
    private String result;
    private String date;

    private List<Match> matches;
    private Match match;

    private Map<Integer, String> fighterOptions;
    private Map<Integer, String> arenaOptions;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getFighter1Id() {
        return fighter1Id;
    }

    public void setFighter1Id(int fighter1Id) {
        this.fighter1Id = fighter1Id;
    }

    public int getFighter2Id() {
        return fighter2Id;
    }

    public void setFighter2Id(int fighter2Id) {
        this.fighter2Id = fighter2Id;
    }

    public int getArenaId() {
        return arenaId;
    }

    public void setArenaId(int arenaId) {
        this.arenaId = arenaId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public Match getMatch() {
        return match;
    }

    public Map<Integer, String> getFighterOptions() {
        return fighterOptions;
    }

    public Map<Integer, String> getArenaOptions() {
        return arenaOptions;
    }

    public String listMatches() {
        try (Connection conn = BaseDAO.getConnection()) {
            MatchDAO dao = new MatchDAO(conn);
            matches = dao.getAllMatches();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al listar combates.");
            return ERROR;
        }
    }

    public String addMatchForm() {
        loadOptions();
        return SUCCESS;
    }

    public String createMatch() {
        try (Connection conn = BaseDAO.getConnection()) {
            MatchDAO dao = new MatchDAO(conn);

            Match m = new Match();
            m.setFighter1Id(fighter1Id);
            m.setFighter2Id(fighter2Id);
            m.setArenaId(arenaId);
            m.setResult(result);
            m.setDate(java.sql.Date.valueOf(date));

            int generatedMatchId = dao.createMatch(m);

            if (generatedMatchId > 0 && result != null && !result.isEmpty()) {
                com.myapp.struts.model.Result r = new com.myapp.struts.model.Result();
                r.setMatchId(generatedMatchId);

                if ("1".equals(result)) {
                    r.setWinnerId(fighter1Id);
                    r.setLoserId(fighter2Id);
                    r.setDraw(0);
                } else if ("2".equals(result)) {
                    r.setWinnerId(fighter2Id);
                    r.setLoserId(fighter1Id);
                    r.setDraw(0);
                } else if ("X".equalsIgnoreCase(result)) {
                    r.setWinnerId(0);
                    r.setLoserId(0);
                    r.setDraw(1);
                }

                ResultDAO resultDAO = new ResultDAO(conn);
                resultDAO.createResult(r);
            }

            return SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al crear el combate: " + e.getMessage());
            return ERROR;
        }
    }

    public String getMatchDetails() {
        try (Connection conn = BaseDAO.getConnection()) {
            MatchDAO dao = new MatchDAO(conn);
            match = dao.getMatch(matchId);
            if (match != null) {
                fighter1Id = match.getFighter1Id();
                fighter2Id = match.getFighter2Id();
                arenaId = match.getArenaId();
                result = match.getResult();
                if (match.getDate() != null) {
                    date = new SimpleDateFormat("yyyy-MM-dd").format(match.getDate());
                }
                loadOptions();
                return SUCCESS;
            } else {
                addActionError("Combate no encontrado.");
                return ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al cargar datos del combate.");
            return ERROR;
        }
    }

    public String updateMatch() {
        try (Connection conn = BaseDAO.getConnection()) {
            Match m = new Match();
            m.setMatchId(matchId);
            m.setFighter1Id(fighter1Id);
            m.setFighter2Id(fighter2Id);
            m.setArenaId(arenaId);
            m.setResult(result);
            m.setDate(java.sql.Date.valueOf(date));

            MatchDAO dao = new MatchDAO(conn);
            dao.updateMatch(m);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al actualizar combate.");
            loadOptions();
            return INPUT;
        }
    }

    public String deleteMatch() {
        try (Connection conn = BaseDAO.getConnection()) {
            MatchDAO dao = new MatchDAO(conn);
            dao.deleteMatch(matchId);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al eliminar combate.");
            return ERROR;
        }
    }

    public String getMatchById() {
        return getMatchDetails();
    }

    public String deleteMatchConfirm() {
        return getMatchDetails();
    }

    public String updateMatchForm() {
        return getMatchDetails();
    }

    private void loadOptions() {
        fighterOptions = new LinkedHashMap<>();
        arenaOptions = new LinkedHashMap<>();

        try (Connection conn = BaseDAO.getConnection()) {
            String sqlFighters = "SELECT fighter_id, username FROM Fighters";
            try (PreparedStatement stmt = conn.prepareStatement(sqlFighters);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    fighterOptions.put(rs.getInt("fighter_id"), rs.getString("username"));
                }
            }

            String sqlArenas = "SELECT arena_id, name FROM Arenas";
            try (PreparedStatement stmt = conn.prepareStatement(sqlArenas);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    arenaOptions.put(rs.getInt("arena_id"), rs.getString("name"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
