package Repositories;

import Services.ConnectionUtil;
import Services.RoliService;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoliRepository {
    private Connection connection;

    public RoliRepository() throws SQLException {
        this.connection = ConnectionUtil.getConnection();
    }

    public List<RoliService> getAllRoles() {
        List<RoliService> roles = new ArrayList<>();

        try {
            PreparedStatement pst = connection.prepareStatement("SELECT roli_id, grada, titulli, departamenti FROM roli");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                RoliService rd = new RoliService();
                rd.setRoli_id(rs.getString("roli_id"));
                rd.setGrada(rs.getString("grada"));
                rd.setDepartamenti(rs.getString("departamenti"));
                rd.setTitulli(rs.getString("titulli"));
                roles.add(rd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoliRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return roles;
    }

    public void updateRole(String grada, String departamenti, String titulli, int roliId) {
        try {
            PreparedStatement pst = connection.prepareStatement("UPDATE roli SET grada = ?, departamenti = ?, titulli = ? WHERE roli_id = ?");
            pst.setString(1, grada);
            pst.setString(2, departamenti);
            pst.setString(3, titulli);
            pst.setInt(4, roliId);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("You are about to change the data!");
            alert.setContentText("Do you want to make an update?");

            alert.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(RoliRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
