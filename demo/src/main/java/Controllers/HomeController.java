package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;



import java.io.IOException;
import java.util.Optional;

public class HomeController extends Application {
    private Stage primaryStage;
    private MenuBar menuBar;


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

    public static void main(String[] args) {
        launch(args);
    }
}