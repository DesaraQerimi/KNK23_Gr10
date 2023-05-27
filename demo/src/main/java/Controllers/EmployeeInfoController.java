package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
