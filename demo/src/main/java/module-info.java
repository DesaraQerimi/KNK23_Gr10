module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports LoginRegistrationForm to javafx.graphics;
    opens LoginRegistrationForm to javafx.fxml;
    exports AddNewForm to javafx.graphics;
    opens AddNewForm to javafx.fxml;
    exports EmployeeTab to javafx.graphics;
    opens EmployeeTab to javafx.fxml;
    exports SalariesTab to javafx.graphics;
    opens SalariesTab to javafx.fxml;
    exports Controllers to javafx.graphics;
    opens Controllers to javafx.fxml;


    opens Services to javafx.base;
}