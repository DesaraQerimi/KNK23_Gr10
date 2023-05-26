package Controllers;

import Services.ConnectionUtil;
import Services.GradaDetails;
import Services.RoliDetails;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
    private TableColumn<RoliDetails,String> colDepartamenti;

    @FXML
    private TableColumn<GradaDetails, String> colGrada;

    @FXML
    private TableColumn<RoliDetails,String> colGrada1;

    @FXML
    private TableColumn<RoliDetails,String> colRoli_id;

    @FXML
    private TableColumn<RoliDetails,String> colTitulli;

    @FXML
    private TableColumn<GradaDetails, String> colkoeficient;

    @FXML
    private TableView<GradaDetails> tableGrada;

    @FXML
    private TableView<RoliDetails> tableRoli;

    @FXML
    private TextField txtDepart;

    @FXML
    private TextField txtGrada1;

    @FXML
    private TextField txtTit;

    @FXML
    private TextField txtVKoef;


    private ObservableList<GradaDetails>data;
    private ObservableList<RoliDetails>data1;
    private ConnectionUtil db;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        TODO
        db = new ConnectionUtil();
        tableRoli();
        tableGrada();
    }

    public void changeWindow(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Employee.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
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
        try
        {
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
        }
        catch (SQLException ex)
        {
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
        try
        {
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
        }
        catch (SQLException ex)
        {
            Logger.getLogger(SalTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/knk","root","");
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void tableRoli()
    {
        Connect();
        ObservableList<RoliDetails> roles = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select roli_id, grada, titulli, departamenti from roli");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    RoliDetails rd = new RoliDetails();
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
        }

        catch (SQLException ex)
        {
            Logger.getLogger(SalTabController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableRoli.setRowFactory( tv -> {
            TableRow<RoliDetails> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tableRoli.getSelectionModel().getSelectedIndex();

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
        ObservableList<GradaDetails> grades = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select grada, koeficient from grada");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    GradaDetails rd = new GradaDetails();
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
            TableRow<GradaDetails> myRow = new TableRow<>();
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
}
