package LoginRegistrationForm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


import java.net.URL;
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
}
