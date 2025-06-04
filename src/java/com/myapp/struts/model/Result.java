/**
 *
 * @author pablo
 */
package com.myapp.struts.model;

import java.sql.Timestamp;

public class Result {
    private int resultId;
    private int matchId;
    private int winnerId;
    private int loserId;
    private boolean isDraw;
    private Timestamp createdAt;
    private Timestamp updatedAt;

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

    public void setWinnerId(Integer winnerId) {
        this.winnerId = winnerId;
    }

    public int getLoserId() {
        return loserId;
    }

    public void setLoserId(Integer loserId) {
        this.loserId = loserId;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
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
