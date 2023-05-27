package Controllers;

import Services.ConnectionUtil;
import Services.GradaService;
import Services.RoliService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalTabController implements Initializable {
    @FXML
    private Button btnLoadData;

    @FXML
    private Button btnLoadData1;

    @FXML
    private MenuItem closeItem;

    @FXML
    private Button employeesEBtn;

    @FXML
    private Button employeesSBtn;

    @FXML
    private Menu fileMenu;

    @FXML
    private Button gradaUpBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button roliUpBtn;

    @FXML
    private BorderPane salariesPage;

    @FXML
    private TableColumn<RoliService, String> colDepartamenti;

    @FXML
    private TableColumn<GradaService, String> colGrada;

    @FXML
    private TableColumn<RoliService, String> colGrada1;

    @FXML
    private TableColumn<RoliService, String> colRoli_id;

    @FXML
    private TableColumn<RoliService, String> colTitulli;

    @FXML
    private TableColumn<GradaService, String> colkoeficient;

    @FXML
    private TableView<GradaService> tableGrada;

    @FXML
    private TableView<RoliService> tableRoli;

    @FXML
    private TextField txtDepart;

    @FXML
    private TextField txtGrada1;

    @FXML
    private TextField txtTit;

    @FXML
    private TextField txtVKoef;

    private ObservableList<GradaService> data;
    private ObservableList<RoliService> data1;
    private ConnectionUtil db;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        TODO
        db = new ConnectionUtil();
        tableRoli();
        tableGrada();

        txtVKoef.addEventHandler(KeyEvent.KEY_TYPED, numberInputEventHandler());
        txtGrada1.addEventHandler(KeyEvent.KEY_TYPED, numberInputEventHandler());
        txtDepart.addEventHandler(KeyEvent.KEY_TYPED, letterInputEventHandler());
        txtTit.addEventHandler(KeyEvent.KEY_TYPED, letterInputEventHandler());
    }

    public void changeWindow(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Employee.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    Stage stage;

    public void logOut(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("You are about to log out!");
//        alert.setContentText("Do you want to save before exiting?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Close the current window
            Stage currentStage = (Stage) salariesPage.getScene().getWindow();
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

    Connection con;
    PreparedStatement pst;
    int myIndex;
    int grada;
    int roli_id;

    @FXML
    void gradaUpFunc(ActionEvent event) {
        String koeficient;

        grada = Integer.parseInt(String.valueOf(tableGrada.getItems().get(myIndex).getGrada()));
        myIndex = tableGrada.getSelectionModel().getSelectedIndex();

        koeficient = txtVKoef.getText();
        try {
            Connect();
            pst = con.prepareStatement("update grada set koeficient = ? where grada = ? ");
            pst.setString(1, koeficient);
            pst.setInt(2, grada);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("You are about to change the data!");
            alert.setContentText("Do you want to make an update?");

            alert.showAndWait();
            tableGrada();
        } catch (SQLException ex) {
            Logger.getLogger(SalTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void roliUpFunc(ActionEvent event) {
        String grada;
        String departamenti;
        String titulli;

        roli_id = Integer.parseInt(String.valueOf(tableRoli.getItems().get(myIndex).getRoli_id()));
        myIndex = tableRoli.getSelectionModel().getSelectedIndex();

        grada = txtGrada1.getText();
        departamenti = txtDepart.getText();
        titulli = txtTit.getText();
        try {
            Connect();
            pst = con.prepareStatement("UPDATE roli SET grada = ?, departamenti = ?, titulli = ? WHERE roli_id = ? ");
            pst.setString(1, grada);
            pst.setString(2, departamenti);
            pst.setString(3, titulli);
            pst.setInt(4, roli_id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("You are about to change the data!");
            alert.setContentText("Do you want to make an update?");

            alert.showAndWait();
            tableRoli();
        } catch (SQLException ex) {
            Logger.getLogger(SalTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/knk", "root", "");
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void tableRoli() {
        Connect();
        ObservableList<RoliService> roles = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select roli_id, grada, titulli, departamenti from roli");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    RoliService rd = new RoliService();
                    rd.setRoli_id(rs.getString("roli_id"));
                    rd.setGrada(rs.getString("grada"));
                    rd.setDepartamenti(rs.getString("departamenti"));
                    rd.setTitulli(rs.getString("titulli"));
                    roles.add(rd);
                }
            }
            tableRoli.setItems(roles);
            colRoli_id.setCellValueFactory(f -> f.getValue().roliIdProperty());
            colGrada1.setCellValueFactory(f -> f.getValue().gradaProperty());
            colDepartamenti.setCellValueFactory(f -> f.getValue().departamentiProperty());
            colTitulli.setCellValueFactory(f -> f.getValue().titulliProperty());
        } catch (SQLException ex) {
            Logger.getLogger(SalTabController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableRoli.setRowFactory(tv -> {
            TableRow<RoliService> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableRoli.getSelectionModel().getSelectedIndex();

                    roli_id = Integer.parseInt(String.valueOf(tableRoli.getItems().get(myIndex).getRoli_id()));
                    txtGrada1.setText(tableRoli.getItems().get(myIndex).getGrada());
                    txtDepart.setText(tableRoli.getItems().get(myIndex).getDepartamenti());
                    txtTit.setText(tableRoli.getItems().get(myIndex).getTitulli());
                }
            });
            return myRow;
        });
    }

    public void tableGrada()
    {
        Connect();
        ObservableList<GradaService> grades = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select grada, koeficient from grada");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    GradaService rd = new GradaService();
                    rd.setGrada(rs.getString("grada"));
                    rd.setkoeficient(rs.getString("koeficient"));
                    grades.add(rd);
                }
            }
            tableGrada.setItems(grades);
            colGrada.setCellValueFactory(f -> f.getValue().gradaProperty());
            colkoeficient.setCellValueFactory(f -> f.getValue().koeficientProperty());
        }

        catch (SQLException ex)
        {
            Logger.getLogger(SalTabController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableGrada.setRowFactory( tv -> {
            TableRow<GradaService> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tableGrada.getSelectionModel().getSelectedIndex();

                    grada = Integer.parseInt(String.valueOf(tableGrada.getItems().get(myIndex).getGrada()));
                    txtVKoef.setText(tableGrada.getItems().get(myIndex).getkoeficient());
                }
            });
            return myRow;
        });
    }

    private EventHandler<KeyEvent> numberInputEventHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!validateNumberInput(event.getCharacter())) {
                    showAlert("Invalid Input", "You can only write number characters");
                    event.consume();
                }
            }
        };
    }

    private EventHandler<KeyEvent> letterInputEventHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!validateLetterInput(event.getCharacter())) {
                    showAlert("Invalid Input", "You can only write letter characters");
                    event.consume();
                }
            }
        };
    }

    private boolean validateNumberInput(String input) {
        return input.matches("\\d");
    }

    private boolean validateLetterInput(String input) {
        return input.matches("[a-zA-Z]");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
