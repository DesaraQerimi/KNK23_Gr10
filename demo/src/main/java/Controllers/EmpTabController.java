package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmpTabController implements Initializable {
    @FXML
    private MenuItem addnewItem;

    @FXML
    private MenuItem closeItem;

    @FXML
    private Menu editMenu;

    @FXML
    private Button employeesEBtn;

    @FXML
    private BorderPane employeesPage;

    @FXML
    private Button employeesSBtn;

    @FXML
    private Menu fileMenu;

    @FXML
    private Menu helpMenu;

    @FXML
    private Button logoutBtn;

    @FXML
    private MenuBar menuBar;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        TODO
    }

    public void changeWindow(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Salaries.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }


    public void openAddNew(ActionEvent event) throws IOException {
        MenuItem clickedMenuItem = (MenuItem) event.getSource();
        String menuId = clickedMenuItem.getParentMenu().getId();
        String menuItemId = clickedMenuItem.getId();

        if (menuId.equals("editMenu") && menuItemId.equals("addnewItem")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddNew.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    Stage stage;
    public void logOut(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("You are about to log out!");
        alert.setContentText("Do you want to save before exiting?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Close the current window
            Stage currentStage = (Stage) employeesPage.getScene().getWindow();
            currentStage.close();

            // Open the login window
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LogReg.fxml"));
                Parent root = loader.load();
                Stage loginStage = new Stage();
                loginStage.setScene(new Scene(root));
                loginStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
