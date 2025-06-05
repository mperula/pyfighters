package com.myapp.struts;

import com.myapp.struts.dao.ArenaDAO;
import com.myapp.struts.model.Arena;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class ListarArenasAction extends ActionSupport {

    private List<Arena> arenas;

    public String execute() {
        arenas = ArenaDAO.getAllArenasConChallenges();
        return SUCCESS;
    }

    public List<Arena> getArenas() {
        return arenas;
    }

    public void setArenas(List<Arena> arenas) {
        this.arenas = arenas;
    }
}
