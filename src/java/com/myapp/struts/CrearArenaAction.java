package com.myapp.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.myapp.struts.dao.ArenaDAO;
import com.myapp.struts.model.Arena;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CrearArenaAction extends ActionSupport {

    private String name;
    private String description;
    private String date;

    // Getters y Setters
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String execute() {
        System.out.println("Ejecutando crear arena");

        if (date == null || date.trim().isEmpty()) {
            addActionError("La fecha es obligatoria.");
            return ERROR;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedDate = LocalDate.parse(date, formatter);

            Arena arena = new Arena();
            arena.setName(name);
            arena.setDescription(description);
            arena.setDate(parsedDate);

            boolean created = ArenaDAO.createArena(arena);

            if (created) {
                addActionMessage("Arena creada con éxito.");
                return SUCCESS;
            } else {
                addActionError("No se pudo crear la arena.");
                return ERROR;
            }

        } catch (Exception e) {
            addActionError("Formato de fecha inválido. Debe ser yyyy-MM-dd.");
            return ERROR;
        }
    }
}
