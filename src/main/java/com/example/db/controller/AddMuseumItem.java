package com.example.db.controller;


import com.example.db.DBQuery;
import com.example.db.entity.MuseumItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.mapping.Array;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddMuseumItem implements Initializable {
    public TextField inventoryNumber;
    public TextField name;
    public ComboBox<String> safely;
    public TextField discovery;
    public TextField author;
    public TextField story;
    public TextField create;
    public ComboBox<String> fondsEm;

    public Button gotAddButton;
    public Button gotEditButton;

    private static MuseumItem selected;
    public static MuseumItem getSelected() {
        return selected;
    }
    public static void setSelected(MuseumItem selected) {
        AddMuseumItem.selected = selected;
    }
    public AddMuseumItem() {
    }
    public AddMuseumItem(MuseumItem selected) {
        setSelected(selected);
        System.out.println(selected.toString());
    }
    DBQuery dbQuery = new DBQuery();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setInventoryNumber();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getFondName();
        getSafely();
//        при изменении заполняем поля выбранным объектом
        if (MyScene.buttonFlag){
            gotAddButton.setVisible(false);
            gotEditButton.setVisible(true);
            inventoryNumber.setText(String.valueOf(getSelected().getPk_inventory_number()));
            name.setText(getSelected().getName_of_item());
            System.out.println("getSelected().getName_of_item() = " + getSelected().getName_of_item());
            safely.setPromptText(getSelected().getSafely());
            discovery.setText(getSelected().getDate_of_discovery());
            author.setText(getSelected().getAuthor());
            story.setText(getSelected().getStory());
            create.setText(getSelected().getDate_of_creation());
            fondsEm.setPromptText(getSelected().getFk_fond_name());
        }
        // забираем поля, создаём sql запрос на добавление и закрываем окно
        gotAddButton.setOnAction(actionEvent -> {
            MuseumItem museumItem = new MuseumItem(Integer.parseInt(inventoryNumber.getText()), author.getText(),
                    create.getText(), discovery.getText(), fondsEm.getValue(), name.getText(), safely.getValue(),
                    story.getText());
            StringBuilder values = new StringBuilder();
            values.append("'").append(inventoryNumber.getText())
                    .append("', '").append(name.getText())
                    .append("', '").append(safely.getValue())
                    .append("', '").append(discovery.getText())
                    .append("', '").append(author.getText())
                    .append("', '").append(story.getText())
                    .append("', '").append(create.getText())
                    .append("', '").append(fondsEm.getValue()).append("'");
            System.out.println(values);
            try {
                dbQuery.insertInfo("museum_item", values.toString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            MyScene.data.add(museumItem);
            Node source = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        });
//        fondsEm.getValue()==null ? getSelectedEmployee().getFond_name():fondsEm.getValue()
        gotEditButton.setOnAction(actionEvent -> {
            MuseumItem museumItem = new MuseumItem(Integer.parseInt(inventoryNumber.getText()), author.getText(),
                    create.getText(), discovery.getText(),
                    fondsEm.getValue()==null ? getSelected().getFk_fond_name():fondsEm.getValue(),
                    name.getText(),
                    safely.getValue()==null ? getSelected().getSafely():safely.getValue(),
                    story.getText());
            System.out.println(museumItem);
            StringBuilder values = new StringBuilder();
            values.append("pk_inventory_number='").append(inventoryNumber.getText())
                    .append("', name_of_item='").append(name.getText())
                    .append("', safely='").append(safely.getValue()==null ? getSelected().getSafely():safely.getValue())
                    .append("', date_of_discovery='").append(discovery.getText())
                    .append("', autor='").append(author.getText())
                    .append("', story='").append(story.getText())
                    .append("', date_of_creation='").append(create.getText())
                    .append("', fk_fond_name='").append(fondsEm.getValue()==null ? getSelected().getFk_fond_name():fondsEm.getValue()).append("'");
            System.out.println(values);
            try {
                dbQuery.updateInfo("museum_item", values.toString(), String.valueOf(getSelected().getPk_inventory_number()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            MyScene.data.add(museumItem);
            MyScene.data.remove(selected);
            Node source = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        });
    }
    //заполняем combobox для названий фонда
    private void getFondName(){
        ObservableList<String> data = FXCollections.observableArrayList();
        String query = "SELECT*FROM fond";
        ResultSet rs = dbQuery.getInfo(query);
        try {
            while (rs.next()){
                data.add(rs.getString("pk_fond_name"));
            }
            fondsEm.setItems(data);
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    private void getSafely(){
        ObservableList<String> data = FXCollections.observableArrayList("Отличное", "Хорошее",
                "Среднее", "Плохое", "Не подлежит востановлению");
        safely.setItems(data);
    }
    private void setInventoryNumber() throws SQLException {
        ResultSet rs = dbQuery.getInfo("SELECT pk_inventory_number FROM museum_item ORDER BY pk_inventory_number DESC");
        rs.next();
        inventoryNumber.setText(String.valueOf(Integer.parseInt(rs.getString(1))+1));
    }
}
