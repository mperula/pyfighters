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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class BaseDAO {

    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    static {
        try {
            Properties props = new Properties();
            InputStream input = BaseDAO.class.getClassLoader().getResourceAsStream("db.properties");
            props.load(input);

            dbUrl = props.getProperty("db.url");
            dbUser = props.getProperty("db.user");
            dbPassword = props.getProperty("db.password");

            //System.out.println("DB URL: " + dbUrl);
            //System.out.println("DB User: " + dbUser);
            //System.out.println("DB Password: " + dbPassword);

            // Cargar driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (Exception e) {
            System.out.println("Error cargando configuración de base de datos: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
}
