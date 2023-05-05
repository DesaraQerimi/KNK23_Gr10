package EmployeeTab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class tab1controller implements Initializable {
    @FXML
    private Button addnewBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button pagatBtn;

    @FXML
    private Button punetoretBtn;

    @FXML
    private AnchorPane punetoretPage;

    @FXML
    private MenuItem closeItem;

    @FXML
    private MenuBar menuBar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        TODO
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        if (event.getSource() == closeItem) {
            Stage stage = (Stage) menuBar.getScene().getWindow();
            stage.close();
        }
    }

}
