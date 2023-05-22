package Repositories;

import Models.Admin;
import Models.Employee;
import Services.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
    private Connection conn;

    public AdminRepository() throws SQLException {
        this.conn = ConnectionUtil.getConnection();
    }

    public Admin login(String username, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM admin WHERE username = ? AND password = ?");
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String Username = rs.getString("username");
            String Password = rs.getString("password");

            return new Admin(id, username, password);
        } else {
            return null;
        }
    }
}
