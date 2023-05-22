package Repositories;

import Models.dto.createEmployeeDto;
import Services.ConnectionUtil;
import Models.Employee;
import Repositories.interfaces.EmployeeRepositoryInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements EmployeeRepositoryInterface {

    private Connection conn;

    public EmployeeRepository() throws SQLException {
        this.conn = ConnectionUtil.getConnection();
    }

    public Employee login(String username, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM admin WHERE username = ? AND password = ?");
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String department = rs.getString("department");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            return new Employee(id, firstName, lastName, department, email, phone);
        } else {
            return null;
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String department = rs.getString("department");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String username = rs.getString("username");
            String password = rs.getString("password");
            Employee employee = new Employee(id, firstName, lastName, department, email, phone);
            employees.add(employee);
        }
        return employees;
    }

    public Employee addEmployee(createEmployeeDto employee) throws SQLException {
        String sql = "INSERT INTO employees (first_name, last_name, department, email, phone, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employee.getName());
        statement.setString(2, employee.getLastname());
        statement.setString(3, employee.getDepartment());
        statement.setString(4, employee.getEmail());
        statement.setString(5, employee.getPhone());
        statement.executeUpdate();

        return EmployeeRepository.getByName(employee.getName());
    }

    public void updateEmployee(Employee employee) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE employees SET first_name = ?, last_name = ?, department = ?, email = ?, phone = ?, username = ?, password = ? WHERE id = ?");
        stmt.setString(1, employee.getFirstName());
        stmt.setString(2, employee.getLastName());
        stmt.setString(3, employee.getDepartment());
        stmt.setString(4, employee.getEmail());
        stmt.setString(5, employee.getPhone());
        stmt.setInt(8, employee.getId());
        stmt.executeUpdate();
    }

    public void deleteEmployee(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM employees WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public static Employee getByName(String name) throws SQLException{
        String sql = "SELECT * FROM employees WHERE name = ?";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String lastname = resultSet.getString("lastname");
                String department = resultSet.getString("department");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                return new Employee(id, name, lastname, department, email, phone);
            } else {
                return null;
            }
        }
    }


    }

