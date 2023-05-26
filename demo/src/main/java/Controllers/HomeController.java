package Controllers;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class HomeController extends Application {

    private HostServices hostServices;

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();

        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem closeMenuItem = new MenuItem("Close");
        closeMenuItem.setOnAction(event -> {
            // Handle the action for "Close" menu item
            primaryStage.close();
        });
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

                hostServices.showDocument(helpCenterLink);
            });
        });
        helpMenu.getItems().add(aboutMenuItem);

        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        root.getChildren().add(menuBar);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        hostServices = getHostServices();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
