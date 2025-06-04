/**
 *
 * @author pablo
 */
package com.myapp.struts.model;

import java.sql.Timestamp;
import java.util.Date;

public class Match {

    private int matchId;
    private int fighter1;
    private int fighter2;
    private int arenaID;
    private String result;
    private Date date;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getFighter1() {
        return fighter1;
    }

    public void setFighter1(int fighter1) {
        this.fighter1 = fighter1;
    }

    public int getFighter2() {
        return fighter2;
    }

    public void setFighter2(int fighter2) {
        this.fighter2 = fighter2;
    }

    public int getArenaID() {
        return arenaID;
    }

    public void setArenaID(int arenaID) {
        this.arenaID = arenaID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
