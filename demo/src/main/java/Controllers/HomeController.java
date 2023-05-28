package Controllers;
<<<<<<< Updated upstream

import javafx.application.Application;
=======
import Models.ChartData;
import Services.ConnectionUtil;
import Services.HomeService;
>>>>>>> Stashed changes
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.layout.BorderPane;
import static javafx.application.Application.launch;

public class HomeController {

    @FXML
    private AreaChart<String, Double> homeChart;

    @FXML
    private Label home_totalEmployees;

    @FXML
    private Button en_bttn;

    @FXML
    private Button al_bttn;

    @FXML
    private Label signIn;

    @FXML
    private Label signUp;

    public HomeController() throws SQLException {
        home_totalEmployees.setText(String.valueOf(totalEmployees));
    }

    @FXML
    void changeWindow(ActionEvent event) {
    }

    private Stage primaryStage;
    private MenuBar menuBar;

   //employees
    HomeService homeService = new HomeService();
    int totalEmployees = homeService.homeTotalEmployees();
    @FXML
    public void initialize() {
        try {
            // Set total employees label
            int totalEmployees = homeService.homeTotalEmployees();
            home_totalEmployees.setText(String.valueOf(totalEmployees));

            List<ChartData> chartDataList = homeService.getChartData();

            XYChart.Series<String, Double> chartSeries = new XYChart.Series<>();
            for (ChartData chartData : chartDataList) {
                chartSeries.getData().add(new XYChart.Data<>(chartData.getDate(), chartData.getAverageSalary()));
            }

            homeChart.getData().add(chartSeries);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();

        menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem closeMenuItem = new MenuItem("Close");
        closeMenuItem.setOnAction(event -> primaryStage.close());
        fileMenu.getItems().add(closeMenuItem);

        Menu editMenu = new Menu("Edit");
        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edit Menu");
            alert.setHeaderText("Delete menu item clicked");
            alert.setContentText("Item deleted successfully!");
            alert.showAndWait();
        });
        editMenu.getItems().add(deleteMenuItem);

        Menu helpMenu = new Menu("Help");
        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Help Menu");
            dialog.setHeaderText("Ask a question");
            dialog.setContentText("Please enter your question:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(question -> {
                try {
                    HomeService.HelpService.saveQuestion(question); // Call the service to save the question
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Help Menu");
                    alert.setHeaderText("Question Submitted");
                    alert.setContentText("Thank you for submitting your question!");
                    alert.showAndWait();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Help Menu");
                    alert.setHeaderText("Error");
                    alert.setContentText("An error occurred while submitting your question. Please try again.");
                    alert.showAndWait();
                }
            });
        });

        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(event -> logout());

        root.getChildren().addAll(menuBar, logoutButton);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void logout() {
        // Navigate to the login page

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    void translateEnglish() {
        Locale currentLocale = new Locale("en");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        signIn.setText(translate.getString("button.signIn"));
        signUp.setText(translate.getString("button.signUp"));

    }

    void translateAlbanian() {
        Locale currentLocale = new Locale("sq");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        signIn.setText(translate.getString("button.signIn"));
        signUp.setText(translate.getString("button.signUp"));

    }

    public void initialize(URL url, ResourceBundle rb) {
        Locale.setDefault(new Locale("sq"));
        translateAlbanian();
        al_bttn.setOnMouseClicked(e -> {
            translateAlbanian();
        });
        en_bttn.setOnMouseClicked(e -> {
            translateEnglish();
        });

    }


}