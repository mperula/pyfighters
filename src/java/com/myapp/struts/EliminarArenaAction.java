package com.myapp.struts;

import com.myapp.struts.dao.ArenaDAO;
import com.opensymphony.xwork2.ActionSupport;

public class EliminarArenaAction extends ActionSupport {

    private int arenaId; // Debe coincidir con el input hidden del JSP

    public int getArenaId() {
        return arenaId;
    }

    public void setArenaId(int arenaId) {
        this.arenaId = arenaId;
    }

    @Override
    public String execute() {
        boolean eliminado = ArenaDAO.eliminarArenaPorId(arenaId);

        if (eliminado) {
            addActionMessage("Arena eliminada correctamente.");
            return SUCCESS;
        } else {
            addActionError("No se pudo eliminar la arena.");
            return ERROR;
        }
    }
}
