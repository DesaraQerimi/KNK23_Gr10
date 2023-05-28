package Services;

import Models.Admin;
import Repositories.AdminRepository;

import java.sql.*;

public class AdminService {
    private AdminRepository adminRepository;


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
