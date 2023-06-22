package com.example.db.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Connect implements Initializable {
    public Pane popupEmployee;
    public Pane popupHall;
    public Pane popupExcursion;
    public Pane popupFond;
    public Pane popupMuseumItem;
    public Pane popupSupport;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        popupHall.setVisible(false);
        popupEmployee.setVisible(false);
        popupExcursion.setVisible(false);
        popupFond.setVisible(false);
        popupMuseumItem.setVisible(false);
        popupSupport.setVisible(false);
        switch (MyScene.flag){
            case 1 -> popupHall.setVisible(true);
            case 2 -> popupEmployee.setVisible(true);
            case 3 -> popupExcursion.setVisible(true);
            case 4 -> popupFond.setVisible(true);
            case 5 -> popupMuseumItem.setVisible(true);
            case 6 -> popupSupport.setVisible(true);
        }
    }

    public void employeeToHall(ActionEvent actionEvent) {

    }
    public void employeeToExcursion(ActionEvent actionEvent) {
    }
    public void employeeToFond(ActionEvent actionEvent) {
    }
    public void hallToExcursion(ActionEvent actionEvent) {
    }
    public void hallToItem(ActionEvent actionEvent) {
    }
    public void fondToItem(ActionEvent actionEvent) {
    }
    public void fondToSupport(ActionEvent actionEvent) {
    }
    public void itemToSupport(ActionEvent actionEvent) {
    }

}
