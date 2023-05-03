module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;



    exports LoginRegistrationForm to javafx.graphics;
    opens LoginRegistrationForm to javafx.fxml;
}