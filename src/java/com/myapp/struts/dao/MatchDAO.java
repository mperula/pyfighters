package com.myapp.struts.dao;

import com.myapp.struts.model.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO {

    private final Connection conn;

    public MatchDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Match> getAllMatches() throws SQLException {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT m.match_id, m.result, m.date, "
                + "       f1.username AS fighter1_name, "
                + "       f2.username AS fighter2_name, "
                + "       a.name AS arena_name "
                + "FROM Matches m "
                + "JOIN Fighters f1 ON m.fighter1_id = f1.fighter_id "
                + "JOIN Fighters f2 ON m.fighter2_id = f2.fighter_id "
                + "JOIN Arenas a ON m.arena_id = a.arena_id "
                + "ORDER BY m.date DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Match match = new Match();
                match.setMatchId(rs.getInt("match_id"));
                match.setResult(rs.getString("result"));
                match.setDate(rs.getDate("date"));
                match.setFighter1Name(rs.getString("fighter1_name"));
                match.setFighter2Name(rs.getString("fighter2_name"));
                match.setArenaName(rs.getString("arena_name"));
                matches.add(match);
            }
        }
        return matches;
    }

    public Match getMatch(int matchId) throws SQLException {
        String sql = "SELECT m.match_id, m.result, m.date, m.fighter1_id, m.fighter2_id, m.arena_id, "
                + "       f1.username AS fighter1_name, "
                + "       f2.username AS fighter2_name, "
                + "       a.name AS arena_name "
                + "FROM Matches m "
                + "JOIN Fighters f1 ON m.fighter1_id = f1.fighter_id "
                + "JOIN Fighters f2 ON m.fighter2_id = f2.fighter_id "
                + "JOIN Arenas a ON m.arena_id = a.arena_id "
                + "WHERE m.match_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matchId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Match match = new Match();
                    match.setMatchId(rs.getInt("match_id"));
                    match.setResult(rs.getString("result"));
                    match.setDate(rs.getDate("date"));
                    match.setFighter1Id(rs.getInt("fighter1_id"));
                    match.setFighter2Id(rs.getInt("fighter2_id"));
                    match.setArenaId(rs.getInt("arena_id"));
                    match.setFighter1Name(rs.getString("fighter1_name"));
                    match.setFighter2Name(rs.getString("fighter2_name"));
                    match.setArenaName(rs.getString("arena_name"));
                    return match;
                }
            }
        }
        return null;
    }

    public int createMatch(Match match) throws SQLException {
        String sql = "INSERT INTO Matches (fighter1_id, fighter2_id, arena_id, result, date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, match.getFighter1Id());
            stmt.setInt(2, match.getFighter2Id());
            stmt.setInt(3, match.getArenaId());
            stmt.setString(4, match.getResult());
            stmt.setDate(5, match.getDate());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public void updateMatch(Match match) throws SQLException {
        String sql = "UPDATE Matches SET fighter1_id = ?, fighter2_id = ?, arena_id = ?, result = ?, date = ? "
                + "WHERE match_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, match.getFighter1Id());
            stmt.setInt(2, match.getFighter2Id());
            stmt.setInt(3, match.getArenaId());
            stmt.setString(4, match.getResult());
            stmt.setDate(5, match.getDate());
            stmt.setInt(6, match.getMatchId());
            stmt.executeUpdate();
        }
    }

    public void deleteMatch(int matchId) throws SQLException {
        String sql = "DELETE FROM Matches WHERE match_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matchId);
            stmt.executeUpdate();
        }
    }

    public void clearResultField(int matchId) throws SQLException {
        String sql = "UPDATE Matches SET result = NULL WHERE match_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matchId);
            stmt.executeUpdate();
        }
    }

    public void clearResultId(int matchId) throws SQLException {
        String sql = "UPDATE Matches SET result_id = NULL WHERE match_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matchId);
            stmt.executeUpdate();
        }
    }
}
