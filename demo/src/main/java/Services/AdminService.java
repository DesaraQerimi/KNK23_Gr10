package Services;

import Models.Admin;
import Repositories.AdminRepository;

import java.sql.*;

public class AdminService {
    private AdminRepository adminRepository;
    private final String DB_URL = "jdbc:mysql://localhost:3306/knk";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";

    public AdminService() throws SQLException {
        this.adminRepository = new AdminRepository();
    }


    public Models.Admin login(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Models.Admin admin = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "SELECT * FROM employees WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String Username = rs.getString("username");
                String Password = rs.getString("password");

                admin = new Admin(id, Username, Password);
            }

        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { stmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }

        return admin;
    }




}
