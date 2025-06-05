/**
 *
 * @author pablo
 */
package com.myapp.struts;

import com.myapp.struts.dao.BaseDAO;
import com.myapp.struts.dao.MatchDAO;
import com.myapp.struts.model.Match;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.Connection;
import java.util.List;

public class MatchAction extends ActionSupport {

    private Match match;
    private List<Match> matches;
    private int matchId;

    // Getters y Setters
    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    // Listar todos los combates
    public String listMatches() {
        try (Connection conn = BaseDAO.getConnection()) {
            MatchDAO matchDAO = new MatchDAO(conn);
            matches = matchDAO.getAllMatches();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al listar combates: " + e.getMessage());
            return ERROR;
        }
    }

    // Obtener detalles de un combate por ID
    public String getMatchDetails() {
        try (Connection conn = BaseDAO.getConnection()) {
            MatchDAO matchDAO = new MatchDAO(conn);
            match = matchDAO.getMatch(matchId);
            if (match != null) {
                return SUCCESS;
            } else {
                addActionError("No se encontró el combate con el ID proporcionado.");
                return ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al obtener detalles del combate: " + e.getMessage());
            return ERROR;
        }
    }

    // Crear nuevo combate
    public String createMatch() {
        try (Connection conn = BaseDAO.getConnection()) {
            MatchDAO matchDAO = new MatchDAO(conn);
            matchDAO.createMatch(match);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al crear combate: " + e.getMessage());
            return ERROR;
        }
    }

    // Actualizar un combate existente
    public String updateMatch() {
        try (Connection conn = BaseDAO.getConnection()) {
            MatchDAO matchDAO = new MatchDAO(conn);
            matchDAO.updateMatch(match);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al actualizar combate: " + e.getMessage());
            return ERROR;
        }
    }

    // Eliminar un combate
    public String deleteMatch() {
        try (Connection conn = BaseDAO.getConnection()) {
            MatchDAO matchDAO = new MatchDAO(conn);
            matchDAO.deleteMatch(match.getMatchId());
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al eliminar combate: " + e.getMessage());
            return ERROR;
        }
    }

    // Método para buscar un combate por ID (igual que getMatchDetails, pero más semántico)
    public String getMatchById() {
        return getMatchDetails();
    }
}
