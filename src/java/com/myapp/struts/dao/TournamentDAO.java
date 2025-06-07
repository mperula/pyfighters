/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.dao;


import com.myapp.struts.model.Tournament;
import com.myapp.struts.model.Achievement;
import java.sql.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournamentDAO extends BaseDAO{
    private Connection connection;

    

    // Create
    public void createTournament(Tournament tournament) throws SQLException {
        String sql = "INSERT INTO Tournaments (name, start_date, end_date, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tournament.getName());
            pstmt.setDate(2, new java.sql.Date(tournament.getStartDate().getTime()));
            pstmt.setDate(3, new java.sql.Date(tournament.getEndDate().getTime()));
            pstmt.setString(4, tournament.getDescription());
            pstmt.executeUpdate();
        }
    }

    // Read
    public Tournament getTournamentById(int id) throws SQLException {
        String sql = "SELECT * FROM Tournaments WHERE tournament_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Tournament(
                    rs.getInt("tournament_id"),
                    rs.getString("name"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    rs.getString("description")
                );
            }
            return null;
        }
    }

    // Update
    public void updateTournament(Tournament tournament) throws SQLException {
        String sql = "UPDATE Tournaments SET name = ?, start_date = ?, end_date = ?, description = ? WHERE tournament_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tournament.getName());
            pstmt.setDate(2, new java.sql.Date(tournament.getStartDate().getTime()));
            pstmt.setDate(3, new java.sql.Date(tournament.getEndDate().getTime()));
            pstmt.setString(4, tournament.getDescription());
            pstmt.setInt(5, tournament.getTournamentId());
            pstmt.executeUpdate();
        }
    }

    // Delete
    public void deleteTournament(int id) throws SQLException {
        String sql = "DELETE FROM Tournaments WHERE tournament_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // List all
    public List<Tournament> listTournaments() throws SQLException {
        List<Tournament> tournaments = new ArrayList<>();
        String sql = "SELECT * FROM Tournaments";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tournaments.add(new Tournament(
                    rs.getInt("tournament_id"),
                    rs.getString("name"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    rs.getString("description")
                ));
            }
        }
        return tournaments;
    }

    // Search by name or date
    public List<Tournament> searchTournaments(String name, Date date) throws SQLException {
        List<Tournament> results = new ArrayList<>();
        String sql = "SELECT * FROM Tournaments WHERE name LIKE ? OR start_date = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
            pstmt.setDate(2, new java.sql.Date(date.getTime()));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                results.add(new Tournament(
                    rs.getInt("tournament_id"),
                    rs.getString("name"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    rs.getString("description")
                ));
            }
        }
        return results;
    }
}
