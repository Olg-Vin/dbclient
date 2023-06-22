package com.example.db;

import com.example.db.entity.Hall;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBQuery {
    public static List<String> joinTables = new ArrayList<>();
    DBHandler dbHandler = new DBHandler();
    ResultSet rs = null;
//     метод возвращает мета-данные таблицы
    public ResultSetMetaData getMeta(String table) throws SQLException {
        Statement statement = dbHandler.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table);
        ResultSetMetaData metaData = resultSet.getMetaData();
        return metaData;
    }
    //     метод возвращает мета-данные таблицы, полученной в результате запроса join
    public ResultSetMetaData getMetaJoin(String query) throws SQLException {
        Statement statement = dbHandler.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData metaData = resultSet.getMetaData();
        return metaData;
    }
//    метод возвращает результат запроса на выборку
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
//    метод возвращает результат запроса на выборку с использованием join
//    public ResultSet getInfoJoin(String query){
//        PreparedStatement ps = null;
//        try {
//            ps = dbHandler.getConnection().prepareStatement(query);
//            rs = ps.executeQuery();
//            dbHandler.ConnectionClose();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return rs;
//    }
//    метод выполняет запрос на добавление данных
    public void insertInfo(String table, String values) throws SQLException {
        String query = "INSERT INTO " + table + " VALUES (" + values + ")";
        System.out.println(query);
        Statement statement = dbHandler.getConnection().createStatement();
        statement.executeUpdate(query);
        dbHandler.ConnectionClose();
    }
//    метод выполняет запрос на удаление записи
    public void deleteInfo(String table, String data) throws SQLException {
        String idColumn = getMeta(table).getColumnName(1);
        String query = "DELETE FROM " + table + " WHERE " + idColumn + " = '" + data+"'";
        System.out.println(query);
        Statement statement = dbHandler.getConnection().createStatement();
        statement.executeUpdate(query);
    }
//    метод выполняет запрос на изменение данных
    public void updateInfo(String table, String values, String data) throws SQLException {
        String idColumn = getMeta(table).getColumnName(1);
        String query = "UPDATE " + table + " SET " + values + " WHERE " + idColumn + " = '" + data + "'";
        System.out.println(query);
        Statement statement = dbHandler.getConnection().createStatement();
        statement.executeUpdate(query);
        dbHandler.ConnectionClose();
    }
//    метод использует мета-данные таблицы и возвращает лист с названиями колонок
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
    //    метод использует мета-данные выполненного запроса и возвращает лист с названиями колонок
    public List<String > createColumnsJoin(String query) throws SQLException {
        ResultSetMetaData resultSet = getMetaJoin(query);
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
