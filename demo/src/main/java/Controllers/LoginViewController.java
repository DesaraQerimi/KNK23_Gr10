package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Services.Employee;
import Services.EmployeeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginViewController implements Initializable {

    @FXML
    private Button li_createaccbtn;

    @FXML
    private TextField li_username;

    @FXML
    private PasswordField li_password;

    @FXML
    private Button li_btn;

    @FXML
    private BorderPane loginform;

    private EmployeeService employeeService;

    public LoginViewController() throws SQLException {
        employeeService = new EmployeeService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // perform initialization here
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = li_username.getText();
        String password = li_password.getText();

        try {
            Employee employee = employeeService.login(username, password);
            if (employee == null) {
                System.out.println("Username or password is incorrect!");
                return;
            }

            // Load the Employee.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Controllers/Employee.fxml"));
            Parent root = loader.load();
            Object controller = loader.getController();



            // Create a new scene with the Employee.fxml file
            Scene scene = new Scene(root);

            // Get the current stage and set the scene to the Employee.fxml file
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (SQLException sqlException) {
            System.out.println("Error connecting to database!");
        } catch (IOException e) {
            System.out.println("Error loading Employee.fxml!");
        }
    }

    @FXML
    private void handleCreateAccountButtonAction(ActionEvent event) {
        // handle create account button action here
    }
}
