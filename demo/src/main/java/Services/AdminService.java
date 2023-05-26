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


    public Admin login(String username, String password) throws SQLException {
        Admin loginAdmin = AdminRepository.getByUsername(username);

        if (loginAdmin == null) {
            return null;
        }

        boolean isPasswordCorrect = password.equals(loginAdmin.getPassword());

        if (isPasswordCorrect) {
            return loginAdmin;
        }

        return null;
    }






}
