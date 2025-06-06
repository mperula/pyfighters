/**
 *
 * @author pablo
 */
package com.myapp.struts.dao;

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

            stmt.setInt(3, result.getIsDraw());  // Aquí corregido
            stmt.setInt(4, result.getResultId());
            stmt.executeUpdate();
        }
    }

    public void deleteResult(int resultId) throws SQLException {
        String sql = "DELETE FROM Results WHERE result_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, resultId);
            stmt.executeUpdate();
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
                    Result r = new Result();
                    r.setResultId(rs.getInt("result_id"));
                    r.setMatchId(rs.getInt("match_id"));
                    r.setWinnerId(rs.getInt("winner_id"));
                    r.setLoserId(rs.getInt("loser_id"));
                    r.setDraw(rs.getInt("is_draw"));  // Aquí corregido
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
                Result r = new Result();
                r.setResultId(rs.getInt("result_id"));
                r.setMatchId(rs.getInt("match_id"));
                r.setWinnerId(rs.getInt("winner_id"));
                r.setLoserId(rs.getInt("loser_id"));
                r.setDraw(rs.getInt("is_draw"));  // Aquí corregido
                r.setCreatedAt(rs.getTimestamp("created_at"));
                r.setUpdatedAt(rs.getTimestamp("updated_at"));
                r.setWinnerName(rs.getString("winner_name"));
                r.setLoserName(rs.getString("loser_name"));
                results.add(r);
            }
        }
        return results;
    }

    public List<Result> searchResults(Integer fighterId, Integer arenaId) throws SQLException {
        List<Result> results = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT r.*, fw.username AS winner_name, fl.username AS loser_name "
                + "FROM Results r "
                + "LEFT JOIN Fighters fw ON r.winner_id = fw.fighter_id "
                + "LEFT JOIN Fighters fl ON r.loser_id = fl.fighter_id "
                + "JOIN Matches m ON r.match_id = m.match_id WHERE 1=1 "
        );

        List<Object> params = new ArrayList<>();
        if (fighterId != null) {
            sql.append("AND (r.winner_id = ? OR r.loser_id = ?) ");
            params.add(fighterId);
            params.add(fighterId);
        }
        if (arenaId != null) {
            sql.append("AND m.arena_id = ? ");
            params.add(arenaId);
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Result r = new Result();
                    r.setResultId(rs.getInt("result_id"));
                    r.setMatchId(rs.getInt("match_id"));
                    r.setWinnerId(rs.getInt("winner_id"));
                    r.setLoserId(rs.getInt("loser_id"));
                    r.setDraw(rs.getInt("is_draw"));  // Aquí corregido
                    r.setCreatedAt(rs.getTimestamp("created_at"));
                    r.setUpdatedAt(rs.getTimestamp("updated_at"));
                    r.setWinnerName(rs.getString("winner_name"));
                    r.setLoserName(rs.getString("loser_name"));
                    results.add(r);
                }
            }
        }
        return results;
    }
}
