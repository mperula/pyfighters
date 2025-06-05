package com.myapp.struts;

import com.myapp.struts.dao.ArenaDAO;
import com.myapp.struts.model.Arena;
import com.myapp.struts.model.Challenge;
import com.opensymphony.xwork2.ActionSupport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class EditarArenaAction extends ActionSupport {

    private int id;
    private String name;
    private String description;
    private String date;             // dd/MM/yyyy

    private List<Challenge> challenges;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public List<Challenge> getChallenges() { return challenges; }
    public void setChallenges(List<Challenge> challenges) { this.challenges = challenges; }


    // -------------------------------
    // 1) Mostrar el formulario
    // -------------------------------
    public String mostrarFormulario() {
        Arena arena = ArenaDAO.getArenaById(id);
        if (arena == null) {
            addActionError("Arena no encontrada.");
            return ERROR;
        }

        // Poner valores en los campos
        this.name = arena.getName();
        this.description = arena.getDescription();

        if (arena.getDate() != null) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.date = arena.getDate().format(fmt);
        } else {
            this.date = "";
        }

        // Cargar la lista de challenges (puede ser vacía)
        if (arena.getChallenges() != null) {
            this.challenges = arena.getChallenges();
        } else {
            this.challenges = new ArrayList<>();
        }

        return SUCCESS;
    }

    // -------------------------------
    // 2) Guardar cambios
    // -------------------------------
    public String guardarCambios() {
        // 2.1. Validar y parsear fecha (dd/MM/yyyy)
        LocalDate parsedDate;
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            parsedDate = LocalDate.parse(date, fmt);
        } catch (DateTimeParseException e) {
            addFieldError("date", "Formato de fecha inválido. Use dd/MM/yyyy");
            return INPUT;
        }

        // 2.2. Si no vienen challenges, inicializarlos vacíos
        if (this.challenges == null) {
            this.challenges = new ArrayList<>();
        }

        // 2.3. Construir el objeto Arena con la lista de challenges
        Arena arenaUpdate = new Arena();
        arenaUpdate.setId(id);
        arenaUpdate.setName(name);
        arenaUpdate.setDescription(description);
        arenaUpdate.setDate(parsedDate);
        arenaUpdate.setChallenges(challenges);

        // 2.4. Llamar al DAO para borrar e insertar los challenges nuevos
        boolean actualizado = ArenaDAO.updateArena(arenaUpdate);
        if (actualizado) {
            addActionMessage("Arena actualizada correctamente.");
            return SUCCESS;
        } else {
            addActionError("Error al actualizar la arena.");
            return ERROR;
        }
    }
}
