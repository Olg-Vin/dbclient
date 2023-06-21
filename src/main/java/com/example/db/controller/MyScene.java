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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MyScene implements Initializable {

    @FXML
    public TableView<Object> table;
//    List<TableColumn<Object, String>> list = new ArrayList<>();

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
    public static boolean joinFlag = false;
    public Button buttonConnect;

    DBHandler dbHandler = new DBHandler();
    DBQuery dbQuery = new DBQuery();
    private int flag;
    @FXML
    public void openAddScene(ActionEvent actionEvent) throws IOException {
        buttonFlag = false;
        switch (flag) {
            case 1: addScene(actionEvent, "addHall.fxml");break;
            case 2: addScene(actionEvent, "addEmployee.fxml");break;
            case 3: addScene(actionEvent, "addExcursion.fxml");break;
            case 4: addScene(actionEvent, "addFond.fxml");break;
            case 5: addScene(actionEvent, "addMuseumItem.fxml");break;
            case 6: addScene(actionEvent, "addSupport.fxml");break;
        }
    }
    @FXML
    public void openEditScene(ActionEvent actionEvent) throws IOException {
        buttonFlag = true;
        switch (flag) {
            case 1: addScene(actionEvent, "addHall.fxml");break;
            case 2: addScene(actionEvent, "addEmployee.fxml");break;
            case 3: addScene(actionEvent, "addExcursion.fxml");break;
            case 4: addScene(actionEvent, "addFond.fxml");break;
            case 5: addScene(actionEvent, "addMuseumItem.fxml");break;
            case 6: addScene(actionEvent, "addSupport.fxml");break;
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
//        createColumns();
        buttonHall.setOnAction(actionEvent -> {
            String nameTable = "hall";
            if(joinFlag) {
                if (DBQuery.joinTables.contains(nameTable)) {
                    buttonHall.setStyle(
                            "-fx-background-color: white;");
                    DBQuery.joinTables.remove(nameTable);
                } else {
                    buttonHall.setStyle(
                            "-fx-background-color: blue;");
                    DBQuery.joinTables.add(nameTable);
                }
            }else {
                addDataFromHall();
            setFlag(1);}
        });
        buttonEmployee.setOnAction(actionEvent -> {
            String nameTable = "employee";
            if(joinFlag) {
                if (DBQuery.joinTables.contains(nameTable)) {
                    buttonEmployee.setStyle(
                            "-fx-background-color: white;");
                    DBQuery.joinTables.remove(nameTable);
                } else {
                    buttonEmployee.setStyle(
                            "-fx-background-color: blue;");
                    DBQuery.joinTables.add(nameTable);
                }
            }else {
            setFlag(2);
            addDataFromEmployee();}
        });
        buttonExcursion.setOnAction(actionEvent -> {
            String nameTable = "excursion";
            if(joinFlag) {
                if (DBQuery.joinTables.contains(nameTable)) {
                    buttonExcursion.setStyle(
                            "-fx-background-color: white;");
                    DBQuery.joinTables.remove(nameTable);
                } else {
                    buttonExcursion.setStyle(
                            "-fx-background-color: blue;");
                    DBQuery.joinTables.add(nameTable);
                }
            }else {
            setFlag(3);
            addDataFromExcursion();}
        });
        buttonFond.setOnAction(actionEvent -> {
            String nameTable = "fond";
            if(joinFlag) {
                if (DBQuery.joinTables.contains(nameTable)) {
                    buttonFond.setStyle(
                            "-fx-background-color: white;");
                    DBQuery.joinTables.remove(nameTable);
                } else {
                    buttonFond.setStyle(
                            "-fx-background-color: blue;");
                    DBQuery.joinTables.add(nameTable);
                }
            }else {
            setFlag(4);
            addDataFromFond();}
        });
        buttonItem.setOnAction(actionEvent -> {
            String nameTable = "museum_item";
            if(joinFlag) {
                if (DBQuery.joinTables.contains(nameTable)) {
                    buttonItem.setStyle(
                            "-fx-background-color: white;");
                    DBQuery.joinTables.remove(nameTable);
                } else {
                    buttonItem.setStyle(
                            "-fx-background-color: blue;");
                    DBQuery.joinTables.add(nameTable);
                }
            }else {
            setFlag(5);
            addDataFromMuseumItem();}
        });
        buttonSupport.setOnAction(actionEvent -> {
            String nameTable = "support";
            if(joinFlag) {
                if (DBQuery.joinTables.contains(nameTable)) {
                    buttonSupport.setStyle(
                            "-fx-background-color: white;");
                    DBQuery.joinTables.remove(nameTable);
                } else {
                    buttonSupport.setStyle(
                            "-fx-background-color: blue;");
                    DBQuery.joinTables.add(nameTable);
                }
            }else {
            setFlag(6);
            addDataFromSupport();}
        });
        buttonDelete.setOnAction(actionEvent -> {
            Object selectedItem = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(selectedItem);
            System.out.println(selectedItem.toString());
//            todo
            try {
                switch (flag) {
                    case 1 -> {
                        Hall hall = (Hall) selectedItem;
                        dbQuery.deleteInfo("hall", hall.getPk_hall_name());
                    }
                    case 2 -> {
                        Employee employee = (Employee) selectedItem;
                        dbQuery.deleteInfo("employee", employee.getPk_employee_passport());
                    }
                    case 3 -> {
                        Excursion excursion = (Excursion) selectedItem;
                        dbQuery.deleteInfo("excursion", excursion.getPk_excursion_name());
                    }
                    case 4 -> {
                        Fond fond = (Fond) selectedItem;
                        dbQuery.deleteInfo("fond", fond.getPk_fond_name());
                    }
                    case 5 -> {
                        MuseumItem museumItem = (MuseumItem) selectedItem;
                        dbQuery.deleteInfo("museum_item", String.valueOf(museumItem.getPk_inventory_number()));
                    }
                    case 6 -> {
                        Support support = (Support) selectedItem;
                        dbQuery.deleteInfo("support", String.valueOf(support.getPk_support_id()));
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
            createColumns("support");
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
                MuseumItem museumItem = new MuseumItem(rs.getInt(1), rs.getString(5),
                        rs.getString(7),rs.getString(4), rs.getString(8),
                        rs.getString(2), rs.getString(3),rs.getString(6));
                data.add(museumItem);
            }
            createColumns("museum_item");
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
                        rs.getString(2));
                data.add(fond);
            }
            createColumns("fond");
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
            createColumns("excursion");
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
            createColumns("hall");
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
            createColumns("employee");
            table.setItems(data);
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

// выбираем строку и передаём объект в нужный класс для работы
    public void handleRowSelection(MouseEvent mouseEvent) {
        System.out.println("row was choose");
        Object selected = table.getSelectionModel().getSelectedItem();
        System.out.println("selected obj = " + selected.toString());
        if (selected != null) {
            switch (flag){
                case 1: AddHall addHall = new AddHall((Hall) selected); break;
                case 2: AddEmployee addEmployee = new AddEmployee((Employee) selected); break;
                case 3: AddExcursion addExcursion = new AddExcursion((Excursion) selected); break;
                case 4: AddFond addFond = new AddFond((Fond) selected); break;
                case 5: AddMuseumItem museumItem = new AddMuseumItem((MuseumItem) selected);
                    System.out.println("choose row = " + selected);break;
                case 6: AddSupport addSupport = new AddSupport((Support) selected); break;
            }
        }
    }
    private static List<Integer> tables = new ArrayList<>();

    public static ObservableList<TableColumn<Object, String>> columns = FXCollections.observableArrayList();

    private void createColumns(String tab) throws SQLException {
        table.getColumns().clear();
        columns.clear();
        List<String > columnsName = dbQuery.createColumns(tab);
        for (int i = 0; i<columnsName.size(); i++){
//            columns.get(i).setCellValueFactory(new PropertyValueFactory<>(columnsName.get(i)));
            TableColumn<Object, String> column = new TableColumn<>("Column " + (i + 1));
            column.setCellValueFactory(new PropertyValueFactory<>(columnsName.get(i)));
            columns.add(column);
        }
        table.getColumns().addAll(columns);
    }
    public void joinMode(ActionEvent actionEvent) {
        if (joinFlag){
            DBQuery.joinTables.clear();
            table.getColumns().clear();
            data.clear();
            buttonHall.setStyle(
                    "-fx-background-color: white;");
            buttonExcursion.setStyle(
                    "-fx-background-color: white;");
            buttonEmployee.setStyle(
                    "-fx-background-color: white;");
            buttonSupport.setStyle(
                    "-fx-background-color: white;");
            buttonFond.setStyle(
                    "-fx-background-color: white;");
            buttonItem.setStyle(
                    "-fx-background-color: white;");
            joinFlag=false;}
        else {joinFlag=true;}
    }

    public void getConnectedTables(ActionEvent actionEvent) {
    }
}



