package com.example.db.controller;


import com.example.db.DBQuery;
import com.example.db.entity.Support;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddSupport implements Initializable {
    public TextField idSupport;
    public ComboBox<String > nameFond;
    public TextField material;
    public Button gotAddButton;
    public Button gotEditButton;
    private static Support selected;
    public static Support getSelected() {
        return selected;
    }
    public static void setSelected(Support selected) {
        AddSupport.selected = selected;
    }
    public AddSupport() {
    }
    public AddSupport(Support selected) {
        setSelected(selected);
        System.out.println(selected.toString());
    }
    DBQuery dbQuery = new DBQuery();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getFondName();
        try {
            setIdSupport();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (MyScene.buttonFlag){
            gotAddButton.setVisible(false);
            gotEditButton.setVisible(true);
            idSupport.setText(String.valueOf(getSelected().getPk_support_id()));
            nameFond.setPromptText(getSelected().getFk_fond_name());
            material.setText(getSelected().getMaterials());
        }
        // забираем поля, создаём sql запрос на добавление и закрываем окно
        gotAddButton.setOnAction(actionEvent -> {
            Support support = new Support(Integer.parseInt(idSupport.getText()), nameFond.getValue(), material.getText());
            StringBuilder values = new StringBuilder();
            values.append("'").append(idSupport.getText())
                    .append("', '").append(nameFond.getValue())
                    .append("', '").append(material.getText()).append("'");
            System.out.println(values);
            try {
                dbQuery.insertInfo("support", values.toString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            MyScene.data.add(support);
            Node source = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        });
        gotEditButton.setOnAction(actionEvent -> {
            Support support = new Support(Integer.parseInt(idSupport.getText()), nameFond.getValue(), material.getText());
            StringBuilder values = new StringBuilder();
            values.append("pk_support_id='").append(idSupport.getText())
                    .append("', fk_fond_name='").append(nameFond.getValue())
                    .append("', materials='").append(material.getText()).append("'");
            System.out.println(values);
            try {
                dbQuery.updateInfo("support", values.toString(), String.valueOf(getSelected().getPk_support_id()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            MyScene.data.add(support);
            MyScene.data.remove(selected);
            Node source = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        });
    }
    private void getFondName(){
        ObservableList<String> data = FXCollections.observableArrayList();
        String query = "SELECT*FROM fond";
        ResultSet rs = dbQuery.getInfo(query);
        try {
            while (rs.next()){
                data.add(rs.getString("pk_fond_name"));
            }
            nameFond.setItems(data);
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    private void setIdSupport() throws SQLException {
        ResultSet rs = dbQuery.getInfo("SELECT pk_support_id FROM support ORDER BY pk_support_id DESC");
        rs.next();
        idSupport.setText(String.valueOf(Integer.parseInt(rs.getString(1))+1));
    }
}
