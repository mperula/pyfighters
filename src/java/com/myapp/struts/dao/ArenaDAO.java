package com.myapp.struts.dao;

import com.myapp.struts.model.Arena;
import com.myapp.struts.model.Challenge;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ArenaDAO extends BaseDAO {

    // Crear nueva arena
    public static boolean createArena(Arena arena) {
        String sql = "INSERT INTO Arenas (name, description, date) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, arena.getName());
            ps.setString(2, arena.getDescription());
            ps.setDate(3, java.sql.Date.valueOf(arena.getDate()));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                // Obtener el ID generado para la arena y luego insertar sus challenges (si los hay)
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int arenaId = rs.getInt(1);
                        arena.setId(arenaId);
                    }
                }

                // Insertar challenges asociados (si existen)
                if (arena.getChallenges() != null && !arena.getChallenges().isEmpty()) {
                    insertChallenges(con, arena.getId(), arena.getChallenges());
                }
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Listar todas las arenas con sus challenges
    public static List<Arena> getAllArenasConChallenges() {
        List<Arena> arenas = new ArrayList<>();
        String sql = "SELECT a.arena_id AS arena_id, a.name AS arena_name, a.description, a.date, " +
                     "c.challenge_id AS challenge_id, c.name AS challenge_name, c.difficulty, c.rules " +
                     "FROM Arenas a LEFT JOIN Challenges c ON a.arena_id = c.arena_id " +
                     "ORDER BY a.arena_id";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            Map<Integer, Arena> arenaMap = new LinkedHashMap<>();

            while (rs.next()) {
                int arenaId = rs.getInt("arena_id");
                Arena arena = arenaMap.get(arenaId);

                if (arena == null) {
                    arena = new Arena();
                    arena.setId(arenaId);
                    arena.setName(rs.getString("arena_name"));
                    arena.setDescription(rs.getString("description"));
                    arena.setDate(rs.getDate("date").toLocalDate());
                    arena.setChallenges(new ArrayList<>());
                    arenaMap.put(arenaId, arena);
                }

                int challengeId = rs.getInt("challenge_id");
                if (challengeId > 0) {
                    Challenge challenge = new Challenge();
                    challenge.setId(challengeId);
                    challenge.setArenaId(arenaId);
                    challenge.setName(rs.getString("challenge_name"));
                    challenge.setDifficulty(rs.getString("difficulty"));
                    challenge.setRules(rs.getString("rules"));
                    arena.getChallenges().add(challenge);
                }
            }

            arenas.addAll(arenaMap.values());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return arenas;
    }

    // Obtener arena por ID (incluye lista de challenges)
    public static Arena getArenaById(int id) {
        String sqlArena = "SELECT * FROM Arenas WHERE arena_id = ?";
        String sqlChallenges = "SELECT challenge_id, name, difficulty, rules FROM Challenges WHERE arena_id = ?";

        Arena arena = null;

        try (Connection con = getConnection();
             PreparedStatement psArena = con.prepareStatement(sqlArena)) {

            psArena.setInt(1, id);
            try (ResultSet rsArena = psArena.executeQuery()) {
                if (rsArena.next()) {
                    arena = new Arena();
                    arena.setId(rsArena.getInt("arena_id"));
                    arena.setName(rsArena.getString("name"));
                    arena.setDescription(rsArena.getString("description"));
                    arena.setDate(rsArena.getDate("date").toLocalDate());
                    arena.setChallenges(new ArrayList<>());
                } else {
                    return null;
                }
            }

            // Si la arena existe, obtener sus challenges
            try (PreparedStatement psCh = con.prepareStatement(sqlChallenges)) {
                psCh.setInt(1, id);
                try (ResultSet rsCh = psCh.executeQuery()) {
                    while (rsCh.next()) {
                        Challenge ch = new Challenge();
                        ch.setId(rsCh.getInt("challenge_id"));
                        ch.setArenaId(id);
                        ch.setName(rsCh.getString("name"));
                        ch.setDifficulty(rsCh.getString("difficulty"));
                        ch.setRules(rsCh.getString("rules"));
                        arena.getChallenges().add(ch);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return arena;
    }

    // Eliminar arena por ID (y sus challenges gracias al ON DELETE CASCADE)
    public static boolean eliminarArenaPorId(int arenaId) {
        String sql = "DELETE FROM Arenas WHERE arena_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, arenaId);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar arena y sincronizar lista de challenges
    public static boolean updateArena(Arena arena) {
        String sqlUpdateArena = "UPDATE Arenas SET name = ?, description = ?, date = ? WHERE arena_id = ?";
        String sqlDeleteChallenges = "DELETE FROM Challenges WHERE arena_id = ?";

        try (Connection con = getConnection()) {
            con.setAutoCommit(false);

            // 1) Actualizar datos básicos de la arena
            try (PreparedStatement psArena = con.prepareStatement(sqlUpdateArena)) {
                psArena.setString(1, arena.getName());
                psArena.setString(2, arena.getDescription());
                psArena.setDate(3, java.sql.Date.valueOf(arena.getDate()));
                psArena.setInt(4, arena.getId());
                psArena.executeUpdate();
            }

            // 2) Borrar todos los challenges existentes de esa arena
            try (PreparedStatement psDel = con.prepareStatement(sqlDeleteChallenges)) {
                psDel.setInt(1, arena.getId());
                psDel.executeUpdate();
            }

            // 3) Insertar todos los challenges enviados desde el formulario
            if (arena.getChallenges() != null && !arena.getChallenges().isEmpty()) {
                insertChallenges(con, arena.getId(), arena.getChallenges());
            }

            con.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método auxiliar para insertar challenges (usa la misma conexión transaccional)
    private static void insertChallenges(Connection con, int arenaId, List<Challenge> challenges) throws SQLException {
        String sqlInsert = "INSERT INTO Challenges (name, difficulty, rules, arena_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement psIns = con.prepareStatement(sqlInsert)) {
            for (Challenge ch : challenges) {
                // Si el nombre está vacío, ignorar este challenge
                if (ch.getName() == null || ch.getName().trim().isEmpty()) {
                    continue;
                }
                psIns.setString(1, ch.getName());
                psIns.setString(2, ch.getDifficulty());
                psIns.setString(3, ch.getRules());
                psIns.setInt(4, arenaId);
                psIns.addBatch();
            }
            psIns.executeBatch();
        }
    }
}
