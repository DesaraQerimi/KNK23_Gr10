package Controllers;

import java.sql.Connection;
import java.sql.SQLException;

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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        TODO
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        String username = li_username.getText();
        String password = li_password.getText();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM admin WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // User logged in successfully
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Controllers/Employee.fxml"));
                Parent root = loader.load();
                Object controller = loader.getController();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                // Incorrect username or password
                li_errorLabel.setText("Incorrect username or password");
                li_errorLabel.setVisible(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
