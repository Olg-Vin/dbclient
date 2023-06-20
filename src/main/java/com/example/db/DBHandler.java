package com.example.db;
import com.example.db.entity.Hall;

import java.sql.*;

/**
 * класс для подключения к базе данных
 */
public class DBHandler {
    Connection connection;
    ResultSet rs = null;

    /**
     * тестовое подключение к базе данных
     */
    public Connection getConnection(){
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://127.0.0.1:5432/dbformuseum";
        String user = "postgres";       // имя пользователя по умолчанию
        String password = "ewq123456";   // пароль к серверу базы данных
        try {
            Class.forName(driver);
            System.out.println("Connected JDBC Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver is not found. Include it in your library path ");
            throw new RuntimeException("JDBC Driver is not found. Include it in your library path");
        }
        System.out.println();
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Test Connection successful!");
//            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());     // SQL Standard
            System.out.println("ErrorCode: " + e.getErrorCode());   // Vendor-specific exception code for the Database
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connection;
    }
    public void ConnectionClose() throws SQLException {
        connection.close();
    }
}
