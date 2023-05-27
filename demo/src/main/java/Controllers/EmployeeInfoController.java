package Controllers;

import Models.Employee;
import Services.EmployeeService;
import Services.GradaService;
import Services.RoliService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeInfoController implements Initializable {

    @FXML
    private Button empDelBtn;

    @FXML
    private TextField empDep;

    @FXML
    private Button empEditBtn;

    @FXML
    private TextField empEmail;

    @FXML
    private TextField empFName;

    @FXML
    private TextField empLName;

    @FXML
    private TextField empPhone;

    private EmployeeService employee;


    public void setEmployee(EmployeeService employee) {
        this.employee = employee;

        // Vendosni të dhënat në textfield-at përkatëse
        empFName.setText(employee.getFirstName());
        empLName.setText(employee.getLastname());
        empDep.setText(employee.getDepartment());
        empEmail.setText(employee.getEmail());
        empPhone.setText(employee.getPhone());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
