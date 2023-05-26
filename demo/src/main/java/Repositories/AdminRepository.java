package Repositories;

import Models.Admin;
import Models.Employee;
import Services.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
    private static Connection conn;

    public AdminRepository() throws SQLException {
        this.conn = ConnectionUtil.getConnection();
    }


    public static Admin getByUsername(String username) throws SQLException{
        String sql = "SELECT * FROM admin WHERE username = ?";
        try{
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int id = rs.getInt("admin_id");
                String password = rs.getString("password");

            return new Admin(id, username, password);
        }}

        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


}
