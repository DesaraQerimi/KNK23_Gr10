package Controllers;

import Services.ConnectionUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewFormController implements Initializable {

    @FXML
    private AnchorPane addNew;

    @FXML
    private TextField addnewDepartment;

    @FXML
    private TextField addnewEmail;

    @FXML
    private TextField addnewName;

    @FXML
    private TextField addnewPhone;

    @FXML
    private TextField addnewSurname;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        TODO
    }

}
