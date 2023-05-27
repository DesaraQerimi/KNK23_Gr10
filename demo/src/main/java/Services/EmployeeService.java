package Services;

import Repositories.EmployeeRepository;
import Models.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class EmployeeService {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty department;
    private final StringProperty email;
    private final StringProperty phone;

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getDepartment() {
        return department.get();
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public String getEmail() {
        return email.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public EmployeeService(String firstName, String lastName, String department, String email, String phone) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.department = new SimpleStringProperty(department);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }


    public EmployeeService(StringProperty firstName, StringProperty lastname, StringProperty lastName, StringProperty department, StringProperty email, StringProperty phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.email = email;
        this.phone = phone;
    }

    public EmployeeService(Employee employee) {
        this.firstName = new SimpleStringProperty(employee.getFirstName());
        this.lastName = new SimpleStringProperty(employee.getLastName());
        this.department = new SimpleStringProperty(employee.getDepartment());
        this.email = new SimpleStringProperty(employee.getEmail());
        this.phone = new SimpleStringProperty(employee.getPhone());
    }
    private EmployeeRepository employeeRepository;

    public static ObservableList<EmployeeService> getAllEmployees(String filter) throws SQLException {
        List<Employee> employeeList = EmployeeRepository.getAllEmployees(filter);
        ObservableList<EmployeeService> employees = FXCollections.observableArrayList();

        for (Employee employee : employeeList) {
            EmployeeService employeeService = new EmployeeService(employee);
            employees.add(employeeService);
        }

        return employees;
    }


    public static void addEmployee(Employee employee) throws SQLException {
        EmployeeRepository.addEmployee(employee);
    }

    public void updateEmployee(Employee employee) throws SQLException {
        employeeRepository.updateEmployee(employee);
    }

    public void deleteEmployee(int id) throws SQLException {
        employeeRepository.deleteEmployee(id);
    }

    public Employee getEmployeeById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Employee employee = null;

        try {
            String query = "SELECT * FROM employees WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return employee;
    }

    public Employee login(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Employee employee = null;

        try {
            String sql = "SELECT * FROM employees WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String department = rs.getString("department");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                employee = new Employee(id, firstName, lastName, department, email, phone);
            }

        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { stmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }

        return employee;
    }




    public StringProperty nameProperty(){
        return firstName;
    }

    public StringProperty lastnameProperty(){
        return lastName;
    }

    public StringProperty deptProperty(){
        return department;
    }

    public StringProperty emailProperty(){
        return email;
    }

    public StringProperty phoneProperty(){
        return phone;
    }
}
