/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.dao;


import com.myapp.struts.model.Fighter;
import com.myapp.struts.model.Script;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AchievementDAO extends BaseDAO{
    private Connection connection;

    public AchievementDAO() {
        this.connection = DatabaseUtil.getConnection();
    }

    // Create
    public void createAchievement(Achievement achievement) throws SQLException {
        String sql = "INSERT INTO Achievements (fighter_id, name, description, date_awarded) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, achievement.getFighterId());
            pstmt.setString(2, achievement.getName());
            pstmt.setString(3, achievement.getDescription());
            pstmt.setDate(4, new java.sql.Date(achievement.getDateAwarded().getTime()));
            pstmt.executeUpdate();
        }
    }

    // Read
    public Achievement getAchievementById(int id) throws SQLException {
        String sql = "SELECT * FROM Achievements WHERE achievement_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Achievement(
                    rs.getInt("achievement_id"),
                    rs.getInt("fighter_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDate("date_awarded")
                );
            }
            return null;
        }
    }

    // Update
    public void updateAchievement(Achievement achievement) throws SQLException {
        String sql = "UPDATE Achievements SET fighter_id = ?, name = ?, description = ?, date_awarded = ? WHERE achievement_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, achievement.getFighterId());
            pstmt.setString(2, achievement.getName());
            pstmt.setString(3, achievement.getDescription());
            pstmt.setDate(4, new java.sql.Date(achievement.getDateAwarded().getTime()));
            pstmt.setInt(5, achievement.getAchievementId());
            pstmt.executeUpdate();
        }
    }

    // Delete
    public void deleteAchievement(int id) throws SQLException {
        String sql = "DELETE FROM Achievements WHERE achievement_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // List all
    public List<Achievement> listAllAchievements() throws SQLException {
        List<Achievement> achievements = new ArrayList<>();
        String sql = "SELECT * FROM Achievements";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                achievements.add(new Achievement(
                    rs.getInt("achievement_id"),
                    rs.getInt("fighter_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDate("date_awarded")
                ));
            }
        }
        return achievements;
    }

    // Search by fighter or category
    public List<Achievement> searchAchievements(int fighterId, String category) throws SQLException {
        List<Achievement> results = new ArrayList<>();
        String sql = "SELECT * FROM Achievements WHERE fighter_id = ? OR name LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, fighterId);
            pstmt.setString(2, "%" + category + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                results.add(new Achievement(
                    rs.getInt("achievement_id"),
                    rs.getInt("fighter_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDate("date_awarded")
                ));
            }
        }
        return results;
    }
}

