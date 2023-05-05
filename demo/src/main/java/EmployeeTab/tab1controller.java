package EmployeeTab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class tab1controller implements Initializable {
    @FXML
    private Button addnewBtn;

    @FXML
    private MenuItem addnewItem;

    @FXML
    private MenuItem closeItem;

    @FXML
    private MenuItem closeItem1;

    @FXML
    private Menu editMenu;

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

    @FXML
    private AnchorPane addNew;

    @FXML
    private Button addnewClose;

    @FXML
    private TextField addnewDepartment;

    @FXML
    private TextField addnewEmail;

    @FXML
    private Button addnewEmployee;

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

    Stage stage;
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

    public void closeAddnew(ActionEvent event) {
        if (event.getSource() == addnewClose) {
            stage = (Stage) addNew.getScene().getWindow();
            stage.close();
        }
    }

    public void openAddnew(ActionEvent event) throws IOException {
        MenuItem clickedMenuItem = (MenuItem) event.getSource();
        String menuId = clickedMenuItem.getParentMenu().getId();
        String menuItemId = clickedMenuItem.getId();

        if (menuId.equals("editMenu") && menuItemId.equals("addnewItem")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Addnew.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

}
