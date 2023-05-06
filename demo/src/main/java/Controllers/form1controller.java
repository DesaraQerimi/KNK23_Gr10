package Controllers;

import Services.Employee;
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


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class form1controller implements Initializable {
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
    public void switchForm(ActionEvent event){
        if(event.getSource() == su_logintobtn){
            loginform.setVisible(true);
            signupform.setVisible(false);
        } else if(event.getSource() == li_createaccbtn){
            loginform.setVisible(false);
            signupform.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        TODO
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        String username = li_username.getText();
        String password = li_password.getText();

        if(username.equals("admin") && password.equals("test")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EmployeeTab/Employee.fxml"));
            Parent root = loader.load();
            Object controller = loader.getController();

            // Create a new scene with the Employee.fxml file
            Scene scene = new Scene(root);

            // Get the current stage and set the scene to the Employee.fxml file
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } else {
            System.out.println("Incorrect username or password");
        }
    }
}
