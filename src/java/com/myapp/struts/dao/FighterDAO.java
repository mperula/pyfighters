/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.dao;

/**
 *
 * @author empichi
 */
import com.myapp.struts.model.Fighter;
import com.myapp.struts.model.Script;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FighterDAO extends BaseDAO {

    public static Fighter login(String email, String password) {
        Fighter f = null;
        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM Fighters WHERE email = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                f = new Fighter();
                f.setFighter_id(rs.getInt("fighter_id"));
                f.setUsername(rs.getString("username"));
                f.setEmail(rs.getString("email"));
                f.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public static boolean existsUser(String email, String username) {
        try (Connection con = getConnection()) {
            String sql = "SELECT COUNT(*) FROM Fighters WHERE email = ? OR username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true; // En caso de error, asumir que existe para prevenir duplicados
    }

    public static boolean register(String username, String email, String password) {
        try (Connection con = getConnection()) {
            String sql = "INSERT INTO Fighters (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Fighter getById(int id) {
        Fighter f = null;
        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM Fighters WHERE fighter_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                f = new Fighter();
                f.setFighter_id(rs.getInt("fighter_id"));
                f.setUsername(rs.getString("username"));
                f.setEmail(rs.getString("email"));
                f.setPassword(rs.getString("password"));

                // 👇 Aquí lo que te faltaba:
                f.setVictories(rs.getInt("victories"));
                f.setDefeats(rs.getInt("defeats"));
                f.setDraws(rs.getInt("draws"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public static boolean update(Fighter f) {
        try (Connection con = getConnection()) {
            String sql = "UPDATE Fighters SET username = ?, email = ?, password = ? WHERE fighter_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, f.getUsername());
            ps.setString(2, f.getEmail());
            ps.setString(3, f.getPassword());
            ps.setInt(4, f.getFighter_id());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*No se recomienda por dependencias*/
    public static boolean delete(int id) {
        try (Connection con = getConnection()) {
            String sql = "DELETE FROM Fighters WHERE fighter_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Fighter> getAll() {
        List<Fighter> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM Fighters ORDER BY victories DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Fighter f = new Fighter();
                f.setFighter_id(rs.getInt("fighter_id"));
                f.setUsername(rs.getString("username"));
                f.setEmail(rs.getString("email"));
                f.setPassword(rs.getString("password"));
                f.setVictories(rs.getInt("victories"));
                f.setDefeats(rs.getInt("defeats"));
                f.setDraws(rs.getInt("draws"));
                list.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Script> getScriptsByFighterId(int fighterId) {
        List<Script> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM Scripts WHERE fighter_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, fighterId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Script s = new Script();
                s.setScript_id(rs.getInt("script_id"));
                s.setFighter_id(rs.getInt("fighter_id"));
                s.setTitle(rs.getString("title"));
                s.setCode(rs.getString("code"));
                s.setCreated_at(rs.getTimestamp("created_at"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateScriptTitle(int scriptId, String newTitle) {
        try (Connection con = getConnection()) {
            String sql = "UPDATE Scripts SET title = ?, updated_at = NOW() WHERE script_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newTitle);
            ps.setInt(2, scriptId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertScript(int fighterId, String title, String code) {
        try (Connection con = getConnection()) {
            String sql = "INSERT INTO Scripts (fighter_id, title, code) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, fighterId);
            ps.setString(2, title);
            ps.setString(3, code);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Script getScriptById(int scriptId) {
        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM Scripts WHERE script_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, scriptId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Script s = new Script();
                s.setScript_id(rs.getInt("script_id"));
                s.setFighter_id(rs.getInt("fighter_id"));
                s.setTitle(rs.getString("title"));
                s.setCode(rs.getString("code"));
                s.setCreated_at(rs.getTimestamp("created_at"));
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public static Script getScriptById(int id) {
        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM Scripts WHERE script_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Script s = new Script();
                s.setScript_id(rs.getInt("script_id"));
                s.setFighter_id(rs.getInt("fighter_id"));
                s.setTitle(rs.getString("title"));
                s.setCode(rs.getString("code"));
                s.setCreated_at(rs.getTimestamp("created_at"));
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
    public static List<Script> getAllScripts() {
        List<Script> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            String sql = "SELECT s.*, f.username AS fighter_name FROM Scripts s JOIN Fighters f ON s.fighter_id = f.fighter_id";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Script s = new Script();
                s.setScript_id(rs.getInt("script_id"));
                s.setFighter_id(rs.getInt("fighter_id"));
                s.setTitle(rs.getString("title"));
                s.setCode(rs.getString("code"));
                s.setCreated_at(rs.getTimestamp("created_at"));
                s.setFighterName(rs.getString("fighter_name"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*public static List<Script> searchScripts(String query) {
        List<Script> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            String sql = "SELECT s.*, f.username AS fighter_name FROM Scripts s JOIN Fighters f ON s.fighter_id = f.fighter_id "
                    + "WHERE s.title LIKE ? OR f.username LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Script s = new Script();
                s.setScript_id(rs.getInt("script_id"));
                s.setFighter_id(rs.getInt("fighter_id"));
                s.setTitle(rs.getString("title"));
                s.setCode(rs.getString("code"));
                s.setCreated_at(rs.getTimestamp("created_at"));
                s.setFighterName(rs.getString("fighter_name"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }*/
    public static List<Script> searchScripts(String query) {
        List<Script> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            String sql = "SELECT s.*, f.username AS fighter_name "
                    + "FROM Scripts s JOIN Fighters f ON s.fighter_id = f.fighter_id "
                    + "WHERE s.title LIKE ? OR f.username LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Script s = new Script();
                s.setScript_id(rs.getInt("script_id"));
                s.setFighter_id(rs.getInt("fighter_id"));
                s.setTitle(rs.getString("title"));
                s.setCode(rs.getString("code"));
                s.setCreated_at(rs.getTimestamp("created_at"));
                s.setFighterName(rs.getString("fighter_name")); // <== ahora sí funciona
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
