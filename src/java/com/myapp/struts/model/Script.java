/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.model;

/**
 *
 * @author User
 */

import java.sql.Timestamp;

public class Script {
    private int script_id;
    private int fighter_id;
    private String title;
    private String code;
    private Timestamp created_at;

    public int getScript_id() { return script_id; }
    public void setScript_id(int script_id) { this.script_id = script_id; }

    public int getFighter_id() { return fighter_id; }
    public void setFighter_id(int fighter_id) { this.fighter_id = fighter_id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Timestamp getCreated_at() { return created_at; }
    public void setCreated_at(Timestamp created_at) { this.created_at = created_at; }
}
