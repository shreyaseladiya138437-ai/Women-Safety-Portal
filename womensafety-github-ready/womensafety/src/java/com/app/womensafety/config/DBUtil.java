package com.app.womensafety.config;

import java.sql.*;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/womensafety?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // XAMPP default username
    private static final String PASS = "";     // XAMPP default has no password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
