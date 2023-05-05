module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;



    exports LoginRegistrationForm to javafx.graphics;
    opens LoginRegistrationForm to javafx.fxml;
    exports EmployeeTab to javafx.graphics;
    opens EmployeeTab to javafx.fxml;
    exports HomeTab to javafx.graphics;
    opens HomeTab to javafx.fxml;
}