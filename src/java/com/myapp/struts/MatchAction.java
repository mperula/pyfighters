/**
 *
 * @author pablo
 */
package com.myapp.struts;

import com.myapp.struts.model.Match;
import com.myapp.struts.dao.MatchDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.List;

public class MatchAction extends ActionSupport {

    private Match match;
    private List<Match> matches;
    private int matchId;
    private Date fecha;
    private int fighter1Id;
    private int fighter2Id;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public String createMatch() {
        // Aquí iría la lógica para crear un Match
        return SUCCESS;
    }

    public String updateMatch() {
        // Aquí iría la lógica para actualizar un Match
        return SUCCESS;
    }

    public String deleteMatch() {
        // Aquí iría la lógica para eliminar un Match
        return SUCCESS;
    }

    public String getMatch() {
        // Aquí iría la lógica para obtener un Match por ID
        return SUCCESS;
    }

    public String listMatches() {
        // Aquí iría la lógica para listar todos los Matches
        return SUCCESS;
    }

    public String searchMatches() {
        // Aquí iría la lógica para buscar Matches por fecha o luchadores
        return SUCCESS;
    }

}
