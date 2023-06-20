package com.example.db.controller;

import com.example.db.DBQuery;
import com.example.db.entity.Fond;
import com.example.db.entity.Hall;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddHall implements Initializable {
    public TextField nameHall;
    public TextField countItems;
    public TextField hallTheme;
    public Button gotAddButton;
    public Button gotEditButton;
    private static Hall selected;

    public static Hall getSelected() {
        return selected;
    }
    public static void setSelected(Hall selected) {
        AddHall.selected = selected;
    }
    public AddHall() {
    }
    public AddHall(Hall selected) {
        setSelected(selected);
        System.out.println(selected.toString());
    }

    DBQuery dbQuery = new DBQuery();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (MyScene.buttonFlag){
            gotAddButton.setVisible(false);
            gotEditButton.setVisible(true);
            nameHall.setText(getSelected().getPk_hall_name());
            countItems.setText(String.valueOf(getSelected().getCount_of_items()));
            hallTheme.setText(getSelected().getTheme());
        }
        // забираем поля, создаём sql запрос на добавление и закрываем окно
        gotAddButton.setOnAction(actionEvent -> {
            Hall hall = new Hall(nameHall.getText(), Integer.parseInt(countItems.getText()), hallTheme.getText());
            StringBuilder values = new StringBuilder();
            values.append("'").append(nameHall.getText()).append("', '").append(countItems.getText()).append("', '")
                    .append(hallTheme.getText()).append("'");
            System.out.println(values);
            try {
                dbQuery.insertInfo("hall", values.toString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            MyScene.data.add(hall);
            Node source = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        });
        gotEditButton.setOnAction(actionEvent -> {
            Hall hall = new Hall(nameHall.getText(), Integer.parseInt(countItems.getText()), hallTheme.getText());
            StringBuilder values = new StringBuilder();
            values.append("pk_hall_name='").append(nameHall.getText())
                    .append("', count_of_items='").append(countItems.getText())
                    .append("', theme='").append(hallTheme.getText()).append("'");
            System.out.println(values);
            try {
                dbQuery.updateInfo("hall", values.toString(), getSelected().getPk_hall_name());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            MyScene.data.add(hall);
            MyScene.data.remove(selected);
            Node source = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        });
    }
}
