package com.example.db.controller;

import com.example.db.DBQuery;
import com.example.db.entity.Excursion;
import com.example.db.entity.Fond;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddFond implements Initializable {
    public TextField nameFond;
    public TextField dateFond;
    public Button gotAddButton;
    public Button gotEditButton;
    private static Fond selected;

    public static Fond getSelected() {
        return selected;
    }
    public static void setSelected(Fond selected) {
        AddFond.selected = selected;
    }
    public AddFond() {
    }
    // забираем выбранное значение из таблицы
    public AddFond(Fond selected) {
        setSelected(selected);
        System.out.println(selected.toString());
    }
    DBQuery dbQuery = new DBQuery();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (MyScene.buttonFlag){
            gotAddButton.setVisible(false);
            gotEditButton.setVisible(true);
            nameFond.setText(getSelected().getFond_name());
            dateFond.setText(String.valueOf(getSelected().getFounding_date()));
        }
        // забираем поля, создаём sql запрос на добавление и закрываем окно
        gotAddButton.setOnAction(actionEvent -> {
            Fond fond = new Fond(nameFond.getText(), dateFond.getText());
            System.out.println(nameFond.getText());
            System.out.println(fond);
            StringBuilder values = new StringBuilder();
            values.append("'").append(nameFond.getText()).append("', '").append(dateFond.getText()).append("'");
            System.out.println(values);
            try {
                dbQuery.insertInfo("fond", values.toString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            MyScene.data.add(fond);
            System.out.println("add fond = " + fond);
            Node source = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        });
        gotEditButton.setOnAction(actionEvent -> {
            Fond fond = new Fond(nameFond.getText(), dateFond.getText());
            StringBuilder values = new StringBuilder();
            values.append("pk_fond_name='").append(nameFond.getText())
                    .append("', founding_date='").append(dateFond.getText()).append("'");
            System.out.println(values);
            try {
                dbQuery.updateInfo("fond", values.toString(), getSelected().getFond_name());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            MyScene.data.add(fond);
            MyScene.data.remove(selected);
            Node source = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        });
    }
}
