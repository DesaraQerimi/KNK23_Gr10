package Controllers;

import Services.EmployeeService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import Services.ConnectionUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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

    @FXML
    private TableColumn<EmployeeService, String> colName;

    @FXML
    private TableColumn<EmployeeService, String> colSurname;

    @FXML
    private TableColumn<EmployeeService, String> colDept;

    @FXML
    private TableColumn<EmployeeService, String> colEmail;

    @FXML
    private TableColumn<EmployeeService, String> colPhone;

    @FXML
    private TableView<EmployeeService> tableEmp;

    private ObservableList<EmployeeService> data;

    private Connection db;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            db = ConnectionUtil.getConnection();
            setupTableColumns();
            loadDataFromDatabase(); // Load data from the database into the table
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setupTableColumns() {
        colName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colDept.setCellValueFactory(new PropertyValueFactory<>("department"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void loadDataFromDatabase() throws SQLException {
        data = EmployeeService.getAllEmployees();
        tableEmp.setItems(data);
    }


    public void changeWindow(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Salaries.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
//        alert.setContentText("Do you want to save before exiting?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Close the current window
            Stage currentStage = (Stage) employeesPage.getScene().getWindow();
            currentStage.close();

            // Open the login window
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
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




