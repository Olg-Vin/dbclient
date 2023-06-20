package com.example.db.controller;


import com.example.db.DBQuery;
import com.example.db.entity.Excursion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddExcursion implements Initializable {
    public TextField nameEx;
    public TextField countEx;
    public Button gotAddButton;
    public Button gotEditButton;
    private static Excursion selected;

    public static Excursion getSelected() {
        return selected;
    }

    public static void setSelected(Excursion selected) {
        AddExcursion.selected = selected;
    }

    public AddExcursion() {
    }

    // забираем выбранное значение из таблицы
    public AddExcursion(Excursion selected) {
        setSelected(selected);
        System.out.println(selected.toString());
    }
    DBQuery dbQuery = new DBQuery();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (MyScene.buttonFlag){
            gotAddButton.setVisible(false);
            gotEditButton.setVisible(true);
//            System.out.println(getSelectedEmployee());
            nameEx.setText(getSelected().getPk_excursion_name());
            countEx.setText(String.valueOf(getSelected().getNumber_of_seats()));
        }
        // забираем поля, создаём sql запрос на добавление и закрываем окно
        gotAddButton.setOnAction(actionEvent -> {
            Excursion excursion = new Excursion(nameEx.getText(), Integer.parseInt(countEx.getText()));
            StringBuilder values = new StringBuilder();
            values.append("'").append(nameEx.getText()).append("', '").append(countEx.getText()).append("'");
            System.out.println(values);
            try {
                dbQuery.insertInfo("excursion", values.toString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            MyScene.data.add(excursion);
            Node source = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        });
        gotEditButton.setOnAction(actionEvent -> {
            Excursion excursion = new Excursion(nameEx.getText(), Integer.parseInt(countEx.getText()));
            StringBuilder values = new StringBuilder();
            values.append("pk_excursion_name='").append(nameEx.getText())
                    .append("', number_of_seats='").append(countEx.getText()).append("'");
            System.out.println(values);
            try {
                dbQuery.updateInfo("excursion", values.toString(), getSelected().getPk_excursion_name());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            MyScene.data.add(excursion);
            MyScene.data.remove(selected);
            Node source = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        });
    }
//    todo навести красоту с проверкой на пустоту полей
}
