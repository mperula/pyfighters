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
import java.sql.*;

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
}