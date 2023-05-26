package Controllers;

import java.sql.Connection;
import java.sql.SQLException;

import Models.Admin;
import Services.ConnectionUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import Services.AdminService;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LogRegFormController implements Initializable {
    @FXML
    private Button li_btn;

    @FXML
    private Button li_createaccbtn;

    @FXML
    private PasswordField li_password;

    @FXML
    private TextField li_username;

    @FXML
    private BorderPane loginform;

    @FXML
    private BorderPane signupform;

    @FXML
    private Button su_btn;

    @FXML
    private TextField su_email;

    @FXML
    private TextField su_firstname;

    @FXML
    private TextField su_lastname;

    @FXML
    private Button su_logintobtn;

    @FXML
    private PasswordField su_password;

    @FXML
    private TextField su_username;

    @FXML
    private Label li_errorLabel;

    private AdminService AdminService;


    public LogRegFormController() throws SQLException {
        this.AdminService = new AdminService();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = li_username.getText();
        String password = li_password.getText();

        try {
            Admin admin = this.AdminService.login(username, password);

            if (admin == null) {
                // Incorrect username or password
                li_errorLabel.setText("Incorrect username or password");
                li_errorLabel.setVisible(true);
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Controllers/Employee.fxml"));
                Parent root = loader.load();
                Object controller = loader.getController();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred during login.", e);
        }
    }

    @FXML
    public void login(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            handleLoginButtonAction(new ActionEvent(keyEvent.getSource(), null)); // Pass keyEvent.getSource() instead of keyEvent
        }
    }

}
