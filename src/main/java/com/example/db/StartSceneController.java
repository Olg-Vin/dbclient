package com.example.db;

import com.example.db.entity.Hall;
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

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StartSceneController implements Initializable {
    @FXML
    public TextField nameField;
    @FXML
    public TextField countField;
    @FXML
    public TextField themeField;
    @FXML
    public Button button_id;
    @FXML
    public Button button2;
    @FXML
    public Button buttonClear;
    @FXML
    public TextField textField;
    DBHandler dbHandler = new DBHandler();
    DBQuery dbQuery = new DBQuery();
    @FXML
    public TableView<Hall> table_id;
    @FXML
    public TableColumn<Hall, String > nameColumn;
    @FXML
    public TableColumn<Hall, Integer> countColumn;
    @FXML
    public TableColumn<Hall, String> themeColumn;

    private final ObservableList<Hall> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_id.setOnAction(actionEvent -> {
            Hall hall = new Hall(nameField.getText(), Integer.parseInt(countField.getText()),
                    themeField.getText());
            data.add(hall);
            table_id.scrollTo(hall);
            try {
                dbQuery.insertInfo("hall", "");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        button2.setOnAction(actionEvent ->{
            addData();

        });
        buttonClear.setOnAction(actionEvent -> {
            System.out.println("clean");
//            cleanData();
//            searchData();
        });
        String query = "";
        ResultSet rs = dbQuery.getInfo(query);
        try {
//            while (rs.next()) {
//                System.out.println("add");
//                Hall hall = new Hall(rs.getString(1),
//                        rs.getInt(2), rs.getString(3));
//                System.out.println(hall.toString());
//                data.add(hall);
//
//            }
//            nameColumn.setCellValueFactory(new PropertyValueFactory<>("hall_name"));
//            countColumn.setCellValueFactory(new PropertyValueFactory<>("count_of_items"));
//            themeColumn.setCellValueFactory(new PropertyValueFactory<>("theme"));
//            table_id.setItems(data);
//        }catch (SQLException e){
//            System.out.println(e.toString());
//        }

        FilteredList<Hall> filteredList = new FilteredList<>(data, b -> true);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(hall -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                System.out.println(newValue);
                String keyWord = newValue.toLowerCase();
                if(hall.getHall_name().toLowerCase().contains(keyWord)){
                    System.out.println("1");
                    return true;
                }
                else if (hall.getCount_of_items().toString().toLowerCase().contains(keyWord)){
                    System.out.println("2");
                    return true;
                }
                else if (hall.getTheme().toLowerCase().contains(keyWord)){
                    System.out.println("3");
                    return true;
                }
                else {
                    System.out.println("4");
                    return false;}
            });
        });
        SortedList<Hall> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(table_id.comparatorProperty());
        table_id.setItems(sortedList);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }


    private void searchData(){
        FilteredList<Hall> filteredList = new FilteredList<>(data, b -> true);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(Hall -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String keyWord = newValue.toLowerCase();
                System.out.println("func");
                if(Hall.getHall_name().toLowerCase().contains(keyWord)){return true;}
                else if (Hall.getCount_of_items().toString().toLowerCase().contains(keyWord)){return true;}
                else if (Hall.getTheme().toLowerCase().contains(keyWord)){return true;}
                else {return false;}
            });
        });
        SortedList<Hall> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(table_id.comparatorProperty());
        table_id.setItems(sortedList);
    }
    private void addData (){
        String query = "";
        ResultSet rs = dbQuery.getInfo(query);
        try {
            while (rs.next()){
                Hall hall = new Hall(rs.getString(1),
                        rs.getInt(2), rs.getString(3));
                data.add(hall);
            }
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("hall_name"));
            countColumn.setCellValueFactory(new PropertyValueFactory<>("count_of_items"));
            themeColumn.setCellValueFactory(new PropertyValueFactory<>("theme"));
            table_id.setItems(data);
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    private void cleanData(){
        if (data.isEmpty()){
            System.out.println("data is empty");
        }
        else {
            data.removeAll();
        }
    }
}
