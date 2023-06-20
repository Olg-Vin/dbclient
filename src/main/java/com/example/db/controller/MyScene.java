package com.example.db.controller;

import com.example.db.DBHandler;
import com.example.db.DBQuery;
import com.example.db.HelloApplication;
import com.example.db.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
             static ObservableList<Object> data = FXCollections.observableArrayList();
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
    public Button buttonDelete;
    public Button buttonUpdate;

//    public Object selectedItem = table.getSelectionModel().getSelectedItem();
    public static boolean buttonFlag;
    DBHandler dbHandler = new DBHandler();
    DBQuery dbQuery = new DBQuery();
    private int flag;
    @FXML
    public void openAddScene(ActionEvent actionEvent) throws IOException {
        buttonFlag = false;
        switch (flag) {
            case 1: System.out.println("1");break;
            case 2: addScene(actionEvent, "addEmployee.fxml");break;
        }
    }
    @FXML
    public void openEditScene(ActionEvent actionEvent) throws IOException {
//        Object selectedItem = table.getSelectionModel().getSelectedItem();
        buttonFlag = true;
        switch (flag) {
            case 1: System.out.println("1");break;
            case 2: addScene(actionEvent, "addEmployee.fxml");break;
        }
    }
    private void addScene(ActionEvent actionEvent, String sceneSource) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(sceneSource));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("данные");
        stage.setScene(new Scene(root));
        Node source = (Node) actionEvent.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        stage.initOwner(currentStage);
        stage.show();
    }
    public int getFlag() {
        return flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        buttonHall.setOnAction(actionEvent -> {
            setFlag(1);
            addDataFromHall();
        });
        buttonEmployee.setOnAction(actionEvent -> {
            setFlag(2);
            addDataFromEmployee();
        });
        buttonExcursion.setOnAction(actionEvent -> {
            setFlag(3);
            addDataFromExcursion();
        });
        buttonFond.setOnAction(actionEvent -> {
            setFlag(4);
            addDataFromFond();
        });
        buttonItem.setOnAction(actionEvent -> {
            setFlag(5);
            addDataFromMuseumItem();
        });
        buttonSupport.setOnAction(actionEvent -> {
            setFlag(6);
            addDataFromSupport();
        });
        buttonDelete.setOnAction(actionEvent -> {
            Object selectedItem = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(selectedItem);
            System.out.println(selectedItem.toString());
            try {
                switch (flag) {
                    case 1 -> System.out.println("1");
                    case 2 -> {
                        Employee employee = (Employee) selectedItem;
                        dbQuery.deleteInfo("employee", employee.getPassport());
                    }
                }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        });
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            table.setItems(filterData(newValue, data));
        });
    }
    // Метод для фильтрации данных в таблице
    private ObservableList<Object> filterData(String keyword, ObservableList<Object> originalData) {
        ObservableList<Object> filteredData = FXCollections.observableArrayList();
        for (Object item : originalData) {
            if (item.toString().contains(keyword)) { // Здесь toString() должен возвращать соответствующее значение для фильтрации
                filteredData.add(item);
            }
        }
        return filteredData;
    }
    private void addDataFromSupport (){
        table.getItems().clear();
        data.clear();
        String query = "SELECT*FROM support";
        ResultSet rs = dbQuery.getInfo(query);
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
        data.clear();
        String query = "SELECT*FROM museum_item";
        ResultSet rs = dbQuery.getInfo(query);
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
        data.clear();
        String query = "SELECT*FROM fond";
        ResultSet rs = dbQuery.getInfo(query);
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
        table.getItems().clear();
        data.clear();
        String query = "SELECT*FROM excursion";
        ResultSet rs = dbQuery.getInfo(query);
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
        }
    }
    private void addDataFromHall (){
        table.getItems().clear();
        data.clear();
        String query = "SELECT*FROM hall";
        ResultSet rs = dbQuery.getInfo(query);
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
        data.clear();
        String query = "SELECT*FROM employee";
        ResultSet rs = dbQuery.getInfo(query);

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


    public void handleRowSelection(MouseEvent mouseEvent) {
        System.out.println("row was choose");
        Object selectedEmployee = table.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            AddEmployee addEmployee = new AddEmployee((Employee) selectedEmployee);
            // Вызов методов в классе AddEmployee или открытие нового окна
        }
    }
}

