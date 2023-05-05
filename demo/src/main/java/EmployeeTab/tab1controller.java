package EmployeeTab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class tab1controller implements Initializable {
    @FXML
    private Button addnewBtn;

    @FXML
    private MenuItem closeItem;

    @FXML
    private Menu fileMenu;

    @FXML
    private Button homeBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button pagatBtn;

    @FXML
    private Button punetoretBtn;

    @FXML
    private AnchorPane punetoretPage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        TODO
    }

    Stage stage;

    @FXML
    public void closeWindow(ActionEvent event) {
        if (event.getSource() == closeItem) {
            stage = (Stage) punetoretPage.getScene().getWindow();
            stage.close();
        }
    }

    public void logOut(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("You are about to log out!");
        alert.setContentText("Do you want to save before exiting?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) punetoretPage.getScene().getWindow();
            stage.close();
        }
    }
}
