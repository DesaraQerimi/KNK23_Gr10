package Controllers;

import Models.Employee;
import Services.EmployeeService;
import javafx.collections.FXCollections;
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
import javafx.scene.control.Pagination;
import javafx.util.Callback;

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
    private TextField filter;

    @FXML
    private Button btnFilter;

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
    @FXML
    private Pagination pagination;

    private EmployeeInfoController infoController;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            db = ConnectionUtil.getConnection();
            setupTableColumns();
            //loadDataFromDatabase(); // Load data from the database into the table
            setupPagination();
            setupContextMenu();

            // Add event listener to filter text field
            filter.setOnAction(this::filterEmployees);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setupContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem viewInfoMenuItem = new MenuItem("View Info");
        viewInfoMenuItem.setOnAction(event -> {
            EmployeeService selectedEmployee = tableEmp.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                try {
                    // Krijo dritaren e re për të shfaqur formën EmployeeInfo.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeInfo.fxml"));
                    Parent root = loader.load();
                    infoController = loader.getController();
                    infoController.setEmployee(selectedEmployee); // Përcjellni të dhënat e zgjedhura te EmployeeInfoController

                    // Krijo një skenë dhe shto formën në të
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Employee Information");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        contextMenu.getItems().add(viewInfoMenuItem);

        tableEmp.setContextMenu(contextMenu);
    }

    private void setupPagination() throws SQLException {
        int itemsPerPage = 22; // Number of items to display per page
        String searchText = filter.getText(); // Get the search text from the filter field

        // Retrieve the employee data based on the search text
        if (searchText == null || searchText.isEmpty()) {
            data = EmployeeService.getAllEmployees(""); // Get all employees if search text is not set
        } else {
            data = EmployeeService.getAllEmployees(searchText); // Get employees based on the search text
        }

        int pageCount = (data.size() / itemsPerPage) + 1;

        pagination.setPageCount(pageCount);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                int startIndex = pageIndex * itemsPerPage;
                int endIndex = Math.min(startIndex + itemsPerPage, data.size());
                tableEmp.setItems(FXCollections.observableArrayList(data.subList(startIndex, endIndex)));
                return tableEmp;
            }
        });
    }

    @FXML
    private void filterEmployees(ActionEvent event) {
        try {
            setupPagination();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupTableColumns() {
        colName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colDept.setCellValueFactory(new PropertyValueFactory<>("department"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    //private void loadDataFromDatabase() throws SQLException {
      //  data = EmployeeService.getAllEmployees("");
      //  tableEmp.setItems(data);
   // }


    public void changeWindow(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Salaries.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void changeWindowH(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("home.fxml"));
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




