import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.layout.BorderPane;

import static javafx.application.Application.launch;

public class HomeController extends Application {

    @FXML
    private MenuBar MenuItem;

    @FXML
    private Button employeesEBtn;

    @FXML
    private Button employeesEBtn1;

    @FXML
    private BorderPane employeesPage;

    @FXML
    private Button employeesSBtn;

    @FXML
    private Button employeesSBtn1;

    @FXML
    private CheckMenuItem enMenuItem;
    @FXML
    private SplitMenuButton msLang;

    @FXML
    private AreaChart<?, ?> homeChart;

    @FXML
    private Label home_income;

    @FXML
    private Label home_totalEmployees;

    @FXML
    void changeWindow(ActionEvent event) {
    }


        private Stage primaryStage;
    private MenuBar menuBar;

   //employees
    public void homeTotalEmployees() {
        String sql = "SELECT COUNT(id) FROM employee";

        connect = database.connectDb();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            home_totalEmployees.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//linechart
public void homeChart() {

    home_chart.getData().clear();

    String sql = "SELECT date, AVG(salary) FROM employee GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 7";

    connect = database.connectDb();

    try {
        XYChart.Series chart = new XYChart.Series();

        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();

        while (result.next()) {
            chart.getData().add(new XYChart.Data(result.getString(1), result.getDouble(2)));
        }

        home_chart.getData().add(chart);

    } catch (Exception e) {
        e.printStackTrace();
    }

}

    @Override
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
                String helpCenterLink = "https://openjfx.io/";
                getHostServices().showDocument(helpCenterLink);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class MainController {

    @FXML
    private Button en_bttn;

    @FXML
    private Button al_bttn;

    @FXML
    private Label signIn;

    @FXML
    private Label signUp;

    private ResourceBundle bundle;



    public static void main(String[] args) {
        launch(args);
    }
}