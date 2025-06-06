package com.myapp.struts.model;

import java.sql.Timestamp;
import java.sql.Date;

public class Match {

    private int matchId;
    private int fighter1Id;
    private int fighter2Id;
    private int arenaId;
    private String result;
    private Date date;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private String fighter1Name;
    private String fighter2Name;
    private String arenaName;

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

    public String getFighter1Name() {
        return fighter1Name;
    }

    public void setFighter1Name(String fighter1Name) {
        this.fighter1Name = fighter1Name;
    }

    public String getFighter2Name() {
        return fighter2Name;
    }

    public void setFighter2Name(String fighter2Name) {
        this.fighter2Name = fighter2Name;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }
}
