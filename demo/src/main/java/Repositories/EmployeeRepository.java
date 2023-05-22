package Repositories;

import Models.Roli;
import Services.ConnectionUtil;
import Models.Employee;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    private static Connection conn;

    public EmployeeRepository() throws SQLException {
        this.conn = ConnectionUtil.getConnection();
    }

    public static List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("name");
            String lastName = rs.getString("lastname");
            String department = rs.getString("department");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            Roli roli = (Roli) rs.getObject("roli"); //TODO
            Date start_date = rs.getDate("start_date");
            Date end_date = rs.getDate("end_date");
            Employee employee = new Employee(id, firstName, lastName, department, email, phone);
            employees.add(employee);
        }
        return employees;
    }

    public static Employee addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (name, lastname, department, email, phone, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setString(3, employee.getDepartment());
        statement.setString(4, employee.getEmail());
        statement.setString(5, employee.getPhone());
        statement.executeUpdate();

        return EmployeeRepository.getByName(employee.getFirstName());
    }

    public void updateEmployee(Employee employee) throws SQLException {
        PreparedStatement stmt = this.conn.prepareStatement("UPDATE employees SET name = ?, lastname = ?, department = ?, email = ?, phone = ? WHERE id = ?");
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

