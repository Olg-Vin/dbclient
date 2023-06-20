package com.example.db;

import com.example.db.entity.Hall;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBQuery {
    public static List<String> joinTables = new ArrayList<>();
    DBHandler dbHandler = new DBHandler();
    ResultSet rs = null;
    public ResultSetMetaData getMeta(String table) throws SQLException {
        Statement statement = dbHandler.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table);
        ResultSetMetaData metaData = resultSet.getMetaData();

//        ResultSetMetaData metaData =
        return metaData;
    }
    public ResultSet getInfo(String getInfo){
        PreparedStatement ps = null;
        try {
            ps = dbHandler.getConnection().prepareStatement(getInfo);
            rs = ps.executeQuery();
            dbHandler.ConnectionClose();
            System.out.println("connection close");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getInfo(List<String> joinTables){
        PreparedStatement ps = null;
        String query = "SELECT * FROM ";
        try {
            ps = dbHandler.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            dbHandler.ConnectionClose();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public void insertInfo(String table, String values) throws SQLException {
        String query = "INSERT INTO " + table + " VALUES (" + values + ")";
        System.out.println(query);
        Statement statement = dbHandler.getConnection().createStatement();
        statement.executeUpdate(query);
        dbHandler.ConnectionClose();
    }
    public void deleteInfo(String table, String data) throws SQLException {
        String idColumn = getMeta(table).getColumnName(1);
        String query = "DELETE FROM " + table + " WHERE " + idColumn + " = '" + data+"'";
        System.out.println(query);
        Statement statement = dbHandler.getConnection().createStatement();
        statement.executeUpdate(query);
    }
    public void updateInfo(String table, String values, String data) throws SQLException {
        String idColumn = getMeta(table).getColumnName(1);
        String query = "UPDATE " + table + " SET " + values + " WHERE " + idColumn + " = '" + data + "'";
        System.out.println(query);
        Statement statement = dbHandler.getConnection().createStatement();
        statement.executeUpdate(query);
        dbHandler.ConnectionClose();
    }

    public List<String > createColumns(String table) throws SQLException {
        ResultSetMetaData resultSet = getMeta(table);
        int count = resultSet.getColumnCount();
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= count; i++){
            list.add(resultSet.getColumnName(i));
        }
        for (int i = 0; i < count; i++){
            System.out.println(list.get(i));
        }
        return list;
    }

}
