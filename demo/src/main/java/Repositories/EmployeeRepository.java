package Repositories;

import Models.Roli;
import Services.ConnectionUtil;
import Models.Employee;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {


    private static Connection conn;

    static {
        try {
            conn = ConnectionUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public EmployeeRepository() throws SQLException {
        conn = ConnectionUtil.getConnection();
    }

    public static List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("name");
                String lastName = rs.getString("lastname");
                String department = rs.getString("department");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Employee employee = new Employee(id, firstName, lastName, department, email, phone);
                employees.add(employee);
            }
        }
        return employees;
    }

    public static Employee addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (name, lastname, department, email, phone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getDepartment());
            statement.setString(4, employee.getEmail());
            statement.setString(5, employee.getPhone());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    employee.setId(generatedId);
                    return employee;
                } else {
                    throw new SQLException("Failed to add employee, no ID obtained.");
                }
            }
        }
    }

    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employees SET name = ?, lastname = ?, department = ?, email = ?, phone = ? WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getDepartment());
            statement.setString(4, employee.getEmail());
            statement.setString(5, employee.getPhone());
            statement.setInt(6, employee.getId());
            statement.executeUpdate();
        }
    }

    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public Employee getByName(String name) throws SQLException {
        String sql = "SELECT * FROM employees WHERE name = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String lastName = resultSet.getString("lastname");
                    String department = resultSet.getString("department");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    return new Employee(id, name, lastName, department, email, phone);
                } else {
                    return null;
                }
            }
        }
    }

    public static List<Employee> getAllEmployees(String string) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql;
        if(string.equals("")){
            sql = "SELECT * FROM employees";
        }else{
            sql = "SELECT * FROM employees WHERE name LIKE '%"+string+"%' OR lastname LIKE '%"+string+"%' OR department LIKE '%"+string+"%'";
        }
        try (Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastname");
                String department = rs.getString("department");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Employee employee = new Employee(id, name, lastName, department, email, phone);
                employees.add(employee);
            }
        }
        return employees;
    }
}
