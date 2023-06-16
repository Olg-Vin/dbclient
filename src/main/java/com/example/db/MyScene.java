package com.example.db;

import com.example.db.entity.*;
import com.example.db.hibernate.ExcursionEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MyScene implements Initializable {

    @FXML
    public TableView<Object> table;
    @FXML public TableColumn<Object, String> A; @FXML public TableColumn<Object, String> B;
    @FXML public TableColumn<Object, String> C; @FXML public TableColumn<Object, String> D;
    @FXML public TableColumn<Object, String> E; @FXML public TableColumn<Object, String> F;
    @FXML public TableColumn<Object, String> G; @FXML public TableColumn<Object, String> H;
    @FXML public TableColumn<Object, String> I;@FXML public TableColumn<Object, String> J;
    @FXML public Button buttonEmployee; @FXML public Button buttonHall;
            private final ObservableList<Object> data = FXCollections.observableArrayList();
    @FXML
    public TextField textField;
    @FXML
    public Button buttonItem;
    @FXML
    public Button buttonSupport;
    @FXML
    public Button buttonExcursion;
    @FXML
    public Button buttonFond;
    @FXML
    public Button buttonAdd;
    @FXML
    public Pane shadowPane;
    @FXML
    public Pane popupEmployee;
    @FXML
    public Button buttonClose;
    @FXML
    public Button gotAddButton;
    public TextField passportEm; public TextField fullNameEm; public TextField educationEm;
    public TextField FondNameEm; public TextField titleEm; public TextField startDateEm;
    DBHandler dbHandler = new DBHandler();
    private int flag = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonHall.setOnAction(actionEvent -> {
            flag = 1;
            addDataFromHall();
        });
        buttonEmployee.setOnAction(actionEvent -> {
            flag = 2;
            addDataFromEmployee();
        });
        buttonExcursion.setOnAction(actionEvent -> {
            flag = 3;
            addDataFromExcursion();
        });
        buttonFond.setOnAction(actionEvent -> {
            flag = 4;
            addDataFromFond();
        });
        buttonItem.setOnAction(actionEvent -> {
            flag = 5;
            addDataFromMuseumItem();
        });
        buttonSupport.setOnAction(actionEvent -> {
            flag = 6;
            addDataFromSupport();
        });
        buttonAdd.setOnAction(actionEvent -> {
            shadowPane.setVisible(true);
            switch (flag) {
                case 1: break;
                case 2: popupEmployee.setVisible(true); break;
            }
        });
        buttonClose.setOnAction(actionEvent -> {
            shadowPane.setVisible(false);
            popupEmployee.setVisible(false);
        });
        gotAddButton.setOnAction(actionEvent -> {
            switch (flag){
                case 1: break;
                case 2: Employee employee = new Employee(passportEm.getText(), fullNameEm.getText(), educationEm.getText(),
                        FondNameEm.getText(), titleEm.getText(), startDateEm.getText());
            }
        });
    }
    private void addDataFromSupport (){
        table.getItems().clear();
        String query = "SELECT*FROM support";
        ResultSet rs = dbHandler.getInfo(query);
        try {
            while (rs.next()){
                Support support = new Support(rs.getInt(1), rs.getString(2),
                        rs.getString(3));
                data.add(support);
            }
            Support support = new Support();
            createColumns(support);
            table.setItems(data);
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    private void addDataFromMuseumItem (){
        table.getItems().clear();
        String query = "SELECT*FROM museum_item";
        ResultSet rs = dbHandler.getInfo(query);
        try {
            while (rs.next()){
                MuseumItem museumItem = new MuseumItem(rs.getInt(1), rs.getString(2),
                        rs.getString(3),rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7),rs.getString(8));
                data.add(museumItem);
            }
            MuseumItem museumItem = new MuseumItem();
            createColumns(museumItem);
            table.setItems(data);
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    private void addDataFromFond (){
        table.getItems().clear();
        String query = "SELECT*FROM fond";
        ResultSet rs = dbHandler.getInfo(query);
        try {
            while (rs.next()){
                Fond fond = new Fond(rs.getString(1),
                        rs.getDate(2));
                data.add(fond);
            }
            Fond fond = new Fond();
            createColumns(fond);
            table.setItems(data);
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    private void addDataFromExcursion (){
//        Hiber hiber = new Hiber();
        List<ExcursionEntity> list = new ArrayList<>(Hiber.query());
        data.addAll(list);
        createColumns(new Excursion());
        table.setItems(data);
//        while (list.next()){
//            Excursion excursion = new Excursion(rs.getString(1),
//                    rs.getInt(2));
//            data.add(excursion);
//        }

        /*table.getItems().clear();
        String query = "SELECT*FROM excursion";
        ResultSet rs = dbHandler.getInfo(query);
        try {
            while (rs.next()){
                Excursion excursion = new Excursion(rs.getString(1),
                        rs.getInt(2));
                data.add(excursion);
            }
            Excursion excursion = new Excursion();
            createColumns(excursion);
            table.setItems(data);
        }
        catch (SQLException e){
            System.out.println(e);
        }*/
    }
    private void addDataFromHall (){
        table.getItems().clear();
        String query = "SELECT*FROM hall";
        ResultSet rs = dbHandler.getInfo(query);
        try {
            while (rs.next()){
                Hall hall = new Hall(rs.getString(1),
                        rs.getInt(2), rs.getString(3));
                data.add(hall);
            }
            Hall hall = new Hall();
            createColumns(hall);
            table.setItems(data);
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    private void addDataFromEmployee (){
        table.getItems().clear();
        String query = "SELECT*FROM employee";
        ResultSet rs = dbHandler.getInfo(query);

        try {
            while (rs.next()){
                Employee employee = new Employee(rs.getString(1),
                        rs.getString(2), rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getString(6));
                data.add(employee);

            }
            Employee employee = new Employee();
            createColumns(employee);
            table.setItems(data);
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    private void createColumns (Entity ent){
        String[] c = ent.columns();
        A.setCellValueFactory(new PropertyValueFactory<>(c.length <= 0 ? null:c[0]));
        B.setCellValueFactory(new PropertyValueFactory<>(c.length <= 1 ? null:c[1]));
        C.setCellValueFactory(new PropertyValueFactory<>(c.length <= 2 ? null:c[2]));
        D.setCellValueFactory(new PropertyValueFactory<>(c.length <= 3 ? null:c[3]));
        E.setCellValueFactory(new PropertyValueFactory<>(c.length <= 4 ? null:c[4]));
        F.setCellValueFactory(new PropertyValueFactory<>(c.length <= 5 ? null:c[5]));
        G.setCellValueFactory(new PropertyValueFactory<>(c.length <= 6 ? null:c[6]));
        H.setCellValueFactory(new PropertyValueFactory<>(c.length <= 7 ? null:c[7]));
        I.setCellValueFactory(new PropertyValueFactory<>(c.length <= 8 ? null:c[8]));
        J.setCellValueFactory(new PropertyValueFactory<>(c.length <= 9 ? null:c[9]));
    }
}

