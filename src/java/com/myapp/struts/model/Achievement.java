/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.model;

import java.util.Date;

/**
 *
 * @author pablouceda
 */
public class Achievement {
    private int achievementId;
    private int fighterId;
    private String name;
    private String description;
    private Date dateAwarded;

    
    public Achievement(int achievementId, int fighterId, String name, String description, Date dateAwarded) {
        this.achievementId = achievementId;
        this.fighterId = fighterId;
        this.name = name;
        this.description = description;
        this.dateAwarded = dateAwarded;
    }
    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    public int getFighterId() {
        return fighterId;
    }

    public void setFighterId(int fighterId) {
        this.fighterId = fighterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateAwarded() {
        return dateAwarded;
    }

    public void setDateAwarded(Date dateAwarded) {
        this.dateAwarded = dateAwarded;
    }

   
}
