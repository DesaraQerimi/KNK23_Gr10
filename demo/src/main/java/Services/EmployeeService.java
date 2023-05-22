package Services;

import Repositories.EmployeeRepository;
import Models.Employee;
import Services.ConnectionUtil;

import java.sql.*;
import java.util.List;

public class EmployeeService {

    private EmployeeRepository employeeRepository;
    public EmployeeService() throws SQLException {
        this.employeeRepository = new EmployeeRepository();
    }

    public List<Employee> getAllEmployees() throws SQLException {
        return EmployeeRepository.getAllEmployees();
    }

    public void addEmployee(Employee employee) throws SQLException {
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




}
