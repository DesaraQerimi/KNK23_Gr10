package Controllers;

import Models.Employee;
import Services.ConnectionUtil;
import Services.EmployeeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeInfoController extends BaseController implements Initializable {

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

    @FXML
    private Button empUpload;

    @FXML
    private Label contractStatusLabel;
    @FXML
    private Button en_bttn;

    @FXML
    private Button al_bttn;
    private EmployeeService employeeService;

    private File selectedContractFile;

    @FXML
    private void uploadContract(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Contract PDF File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        // Show the file chooser dialog
        File selectedFile = fileChooser.showOpenDialog(empUpload.getScene().getWindow());

        if (selectedFile != null) {
            // File selected, store the reference
            selectedContractFile = selectedFile;

            // Get the file name
            String fileName = selectedContractFile.getName();

            // Update the contract column in the database with the file name
            try {
                Connection conn = ConnectionUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE employees SET contract = ? WHERE email = ?");
                stmt.setString(1, fileName);
                stmt.setString(2, email);
                stmt.executeUpdate();

                // Update the label text
                contractStatusLabel.setText("Ky employee ka kontratë pune.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void initializeContractStatusLabel() {
        try {
            // Retrieve the employee's contract value from the database using their email
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT contract FROM employees WHERE email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Get the contract value from the result set
                String contractValue = rs.getString("contract");

                if (contractValue != null && !contractValue.isEmpty()) {
                    // If the contract value is not empty, set the label text
                    contractStatusLabel.setText("Ky employee ka kontratë pune");
                }
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void setEmployee(EmployeeService employee) {
        this.employeeService = employee;

        // Set the other employee data in the text fields
        empFName.setText(employee.getFirstName());
        empLName.setText(employee.getLastName());
        empDep.setText(employee.getDepartment());
        empEmail.setText(employee.getEmail());
        empPhone.setText(employee.getPhone());

        email = empEmail.getText();

        initializeContractStatusLabel();
    }
private String email;
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
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM employees WHERE email = ?");
                stmt.setString(1, email);
                stmt.executeUpdate();

                // Close the current window
                empDelBtn.getScene().getWindow().hide();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void updateEmployee(ActionEvent event) {
        // Retrieve the updated employee data from the text fields
        String firstName = empFName.getText();
        String lastName = empLName.getText();
        String department = empDep.getText();
        String email = empEmail.getText();
        String phone = empPhone.getText();

        // Set the updated data to the employee object
        employeeService.setFirstName(firstName);
        employeeService.setLastName(lastName);
        employeeService.setDepartment(department);
        employeeService.setEmail(email);
        employeeService.setPhone(phone);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Edit Employee");
        alert.setHeaderText("Confirm Update");
        alert.setContentText("Are you sure you want to update data for this employee?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            try {
                // Update the employee in the database
                Connection conn = ConnectionUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE employees SET name=?, lastname=?, department=?, email=?, phone=? WHERE id=?");
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, department);
                stmt.setString(4, email);
                stmt.setString(5, phone);
                stmt.setInt(6, Employee.getId());
                stmt.executeUpdate();

                // Close the current window
                empEditBtn.getScene().getWindow().hide();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    void translateEN() {
        Locale.Builder builder = new Locale.Builder();
        builder.setLanguage("en");
        Locale currentLocale = builder.build();

        ResourceBundle translate = ResourceBundle.getBundle("translation.content_en", currentLocale);

        empFName.setText(translate.getString("textfield.empLName"));
        empLName.setText(translate.getString("textfield.empLName"));
        empEmail.setText(translate.getString("textfield.email"));
        empDep.setText(translate.getString("textfield.empDep"));
        empPhone.setText(translate.getString("textfield.empPhone"));
        empEditBtn.setText(translate.getString("button.empEditBtn"));
        empDelBtn.setText(translate.getString("button.empDelBtn"));
    }
    //translation to albanian
    @Override
    void translateSQ() {
        Locale.Builder builder = new Locale.Builder();
        builder.setLanguage("sq");
        Locale currentLocale = builder.build();

        ResourceBundle translate = ResourceBundle.getBundle("translation.content_sq", currentLocale);

        empFName.setText(translate.getString("textfield.empLName"));
        empLName.setText(translate.getString("textfield.empLName"));
        empEmail.setText(translate.getString("textfield.email"));
        empDep.setText(translate.getString("textfield.empDep"));
        empPhone.setText(translate.getString("textfield.empPhone"));
        empEditBtn.setText(translate.getString("button.empEditBtn"));
        empDelBtn.setText(translate.getString("button.empDelBtn"));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale albanianLocale = new Locale.Builder().setLanguage("sq").build();
        Locale.setDefault(albanianLocale);
        translateSQ();
        en_bttn.setOnMouseClicked(e -> {
            translateEN();
        });
        al_bttn.setOnMouseClicked(e -> {
            translateSQ();
        });

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(empEmail.getText());
    }
}
