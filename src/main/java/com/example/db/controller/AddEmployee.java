package com.example.db.controller;

import com.example.db.DBQuery;
import com.example.db.entity.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddEmployee implements Initializable {

    public TextField passportEm;
    public TextField fullNameEm;
    public TextField educationEm;
    public ComboBox<String> fondsEm;
    public TextField titleEm;
    public TextField startDateEm;
    public Button gotAddButton;
    public Button gotEditButton;
    private static Employee selectedEmployee;

    public AddEmployee() {
    }

    // забираем выбранное значение из таблицы
    public AddEmployee(Employee selectedEmployee) {
        setSelectedEmployee(selectedEmployee);
        System.out.println(selectedEmployee.toString());
    }

    DBQuery dbQuery = new DBQuery();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getFondName();
        if (MyScene.buttonFlag){
            gotAddButton.setVisible(false);
            gotEditButton.setVisible(true);
//            System.out.println(getSelectedEmployee());
            passportEm.setText(getSelectedEmployee().getPassport());
            fullNameEm.setText(getSelectedEmployee().getFull_name());
            educationEm.setText(getSelectedEmployee().getEducation());
            fondsEm.setPromptText(getSelectedEmployee().getFond_name());
            titleEm.setText(getSelectedEmployee().getJob_title());
            startDateEm.setText(getSelectedEmployee().getStart_date());
        }
        // забираем поля, создаём sql запрос на добавление и закрываем окно
        gotAddButton.setOnAction(actionEvent -> {
            Employee employee = new Employee(passportEm.getText(), fullNameEm.getText(), titleEm.getText(),
                    startDateEm.getText(), educationEm.getText(), fondsEm.getValue());
            StringBuilder values = new StringBuilder();
            values.append("'").append(passportEm.getText()).append("', '").append(fullNameEm.getText())
                    .append("', '").append(titleEm.getText()).append("', '")
                    .append(startDateEm.getText()).append("', '")
                    .append(educationEm.getText()).append("', '")
                    .append(fondsEm.getValue()).append("'");
            System.out.println(values);
            try {
                dbQuery.insertInfo("employee", values.toString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            MyScene.data.add(employee);
            Node source = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        });
        gotEditButton.setOnAction(actionEvent -> {
            Employee employee = new Employee(passportEm.getText(), fullNameEm.getText(), titleEm.getText(),
                    startDateEm.getText(), educationEm.getText(), fondsEm.getValue()==null ? getSelectedEmployee().getFond_name():fondsEm.getValue());
            StringBuilder values = new StringBuilder();
            values.append("pk_employee_passport='").append(passportEm.getText())
                    .append("', full_name='").append(fullNameEm.getText())
                    .append("', job_title='").append(titleEm.getText())
                    .append("', start_date='").append(startDateEm.getText())
                    .append("', education='").append(educationEm.getText())
                    .append("', fk_fond_name='").append(fondsEm.getValue()==null ? getSelectedEmployee().getFond_name():fondsEm.getValue()).append("'");
            System.out.println(values);
            try {
                dbQuery.updateInfo("employee", values.toString(), getSelectedEmployee().getPassport());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            MyScene.data.add(employee);
            MyScene.data.remove(selectedEmployee);
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

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }
//    todo навести красоту с проверкой на пустоту полей

}
