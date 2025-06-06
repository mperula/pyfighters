package com.myapp.struts.dao;

import com.myapp.struts.model.Match;
import com.myapp.struts.model.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO extends BaseDAO {

    private final Connection conn;

    public ResultDAO(Connection conn) {
        this.conn = conn;
    }

    public void createResult(Result result) throws SQLException {
        String sql = "INSERT INTO Results (match_id, winner_id, loser_id, is_draw) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, result.getMatchId());

            int winnerId = result.getWinnerId();
            int loserId = result.getLoserId();

            if (winnerId != 0) {
                stmt.setInt(2, winnerId);
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            if (loserId != 0) {
                stmt.setInt(3, loserId);
            } else {
                stmt.setNull(3, Types.INTEGER);
            }

            int isDraw = (winnerId == 0 && loserId == 0) ? 1 : 0;
            stmt.setInt(4, isDraw);

            stmt.executeUpdate();
        }
    }

    public void updateResult(Result result) throws SQLException {
        String sql = "UPDATE Results SET winner_id = ?, loser_id = ?, is_draw = ? WHERE result_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (result.getWinnerId() != 0) {
                stmt.setInt(1, result.getWinnerId());
            } else {
                stmt.setNull(1, Types.INTEGER);
            }

            if (result.getLoserId() != 0) {
                stmt.setInt(2, result.getLoserId());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            stmt.setInt(3, result.getIsDraw());
            stmt.setInt(4, result.getResultId());
            stmt.executeUpdate();
        }
    }

    public void deleteResult(int resultId) throws SQLException {
        int matchId = -1;

        String selectSql = "SELECT match_id FROM Results WHERE result_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
            stmt.setInt(1, resultId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    matchId = rs.getInt("match_id");
                }
            }
        }

        String deleteSql = "DELETE FROM Results WHERE result_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(deleteSql)) {
            stmt.setInt(1, resultId);
            stmt.executeUpdate();
        }

        if (matchId != -1) {
            MatchDAO matchDAO = new MatchDAO(conn);
            matchDAO.clearResultId(matchId);
        }
    }

    public Result getResult(int resultId) throws SQLException {
        String sql = "SELECT r.*, fw.username AS winner_name, fl.username AS loser_name "
                + "FROM Results r "
                + "LEFT JOIN Fighters fw ON r.winner_id = fw.fighter_id "
                + "LEFT JOIN Fighters fl ON r.loser_id = fl.fighter_id "
                + "WHERE r.result_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, resultId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractResultFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public Result getResultByMatchId(int matchId) throws SQLException {
        String sql = "SELECT r.*, fw.username AS winner_name, fl.username AS loser_name "
                + "FROM Results r "
                + "LEFT JOIN Fighters fw ON r.winner_id = fw.fighter_id "
                + "LEFT JOIN Fighters fl ON r.loser_id = fl.fighter_id "
                + "WHERE r.match_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matchId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Result r = new Result();
                    r.setResultId(rs.getInt("result_id"));
                    r.setMatchId(rs.getInt("match_id"));
                    r.setWinnerId(rs.getInt("winner_id"));
                    r.setLoserId(rs.getInt("loser_id"));
                    r.setDraw(rs.getInt("is_draw"));
                    r.setCreatedAt(rs.getTimestamp("created_at"));
                    r.setUpdatedAt(rs.getTimestamp("updated_at"));
                    r.setWinnerName(rs.getString("winner_name"));
                    r.setLoserName(rs.getString("loser_name"));
                    return r;
                }
            }
        }
        return null;
    }

    public List<Result> listResults() throws SQLException {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT r.*, fw.username AS winner_name, fl.username AS loser_name "
                + "FROM Results r "
                + "LEFT JOIN Fighters fw ON r.winner_id = fw.fighter_id "
                + "LEFT JOIN Fighters fl ON r.loser_id = fl.fighter_id "
                + "ORDER BY r.created_at DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                results.add(extractResultFromResultSet(rs));
            }
        }
        return results;
    }

    private Result extractResultFromResultSet(ResultSet rs) throws SQLException {
        Result r = new Result();
        r.setResultId(rs.getInt("result_id"));
        r.setMatchId(rs.getInt("match_id"));
        r.setWinnerId(rs.getInt("winner_id"));
        r.setLoserId(rs.getInt("loser_id"));
        r.setDraw(rs.getInt("is_draw"));
        r.setCreatedAt(rs.getTimestamp("created_at"));
        r.setUpdatedAt(rs.getTimestamp("updated_at"));
        r.setWinnerName(rs.getString("winner_name"));
        r.setLoserName(rs.getString("loser_name"));
        return r;
    }

}
