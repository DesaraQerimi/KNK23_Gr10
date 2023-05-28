package Controllers;

import Models.Employee;
import Services.ConnectionUtil;
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
import java.util.Optional;
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

    private EmployeeService employeeService;

    public void setEmployee(EmployeeService employee) {
        this.employeeService = employee;

        // Vendosni të dhënat në textfield-at përkatëse
        empFName.setText(employee.getFirstName());
        empLName.setText(employee.getLastName());
        empDep.setText(employee.getDepartment());
        empEmail.setText(employee.getEmail());
        empPhone.setText(employee.getPhone());
    }

    @FXML
    private void deleteEmployee(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Employee");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete this employee?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // Delete the employee from the database
                Connection conn = ConnectionUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM employees WHERE id = ?");
                stmt.setInt(1, Employee.getId());
                stmt.executeUpdate();

                // Close the current window
                empDelBtn.getScene().getWindow().hide();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }



}
