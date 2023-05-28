package Controllers;

import Models.Employee;
import Services.ConnectionUtil;
import Services.EmployeeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    @FXML
    private Button handelAddBtn;

    Alert alert = new Alert(Alert.AlertType.ERROR,"");

    public void add(ActionEvent ae) throws Exception {
        if (!addnewName.getText().isBlank() && !addnewSurname.getText().isBlank() &&
                !addnewDepartment.getText().isBlank() && !addnewEmail.getText().isBlank() &&
                !addnewPhone.getText().isBlank()) {

            Employee employee = new Employee(addnewName.getText(), addnewSurname.getText(),
                    addnewDepartment.getText(), addnewEmail.getText(), addnewPhone.getText());

            EmployeeService.addEmployee(employee);

            // Close the window
            addNew.getScene().getWindow().hide();
        } else {
            alert.setContentText("Fill all the fields!");
            alert.show();
        }
    }
    //translation to english
   @Override
    void translateEnglish() {
        Locale currentLocale = new Locale("en");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content_en.properties", currentLocale);
        addtitle.setText(translate.getString("addNewForm.addTitle"));
        name.setText(translate.getString("addNewForm.name"));
       	surname.setText(translate.getString("addNewForm.surname"));
        email.setText(translate.getString("addNewForm.email"));
        phone.setText(translate.getString("addNewForm.phone"));
        addButton.setText(translate.getString("addNewForm.addButton"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Register event handler for Enter key press
        addnewName.setOnKeyPressed(this::handleEnterKeyPress);
        addnewSurname.setOnKeyPressed(this::handleEnterKeyPress);
        addnewDepartment.setOnKeyPressed(this::handleEnterKeyPress);
        addnewEmail.setOnKeyPressed(this::handleEnterKeyPress);
        addnewPhone.setOnKeyPressed(this::handleEnterKeyPress);
    }

    private void handleEnterKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                add(new ActionEvent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
