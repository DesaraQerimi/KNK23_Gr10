package EmployeeTab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private MenuItem closeItem1;

    @FXML
    private Button employeesEBtn;

    @FXML
    private Button employeesHBtn;

    @FXML
    private BorderPane employeesPage;

    @FXML
    private Button employeesWBtn;

    @FXML
    private Menu fileMenu;

    @FXML
    private Menu fileMenu1;

    @FXML
    private Button homeEBtn;

    @FXML
    private Button homeHBtn;

    @FXML
    private BorderPane homePage;

    @FXML
    private Button homeWBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button logoutBtn1;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuBar menuBar1;

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

    @FXML
    public void switchPages(ActionEvent event){
        if(event.getSource() == homeHBtn || event.getSource() == employeesHBtn){
            homePage.setVisible(true);
            employeesPage.setVisible(false);
        } else if(event.getSource() == homeEBtn || event.getSource() == employeesEBtn){
            homePage.setVisible(false);
            employeesPage.setVisible(true);
        }
    }
}
