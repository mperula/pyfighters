package com.myapp.struts.model;

public class Challenge {
    private int id;
    private int arenaId;
    private String name;
    private String difficulty;
    private String rules;

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }
    

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getArenaId() { return arenaId; }
    public void setArenaId(int arenaId) { this.arenaId = arenaId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
