package Repositories;

import Services.ConnectionUtil;
import Services.GradaService;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GradaRepository {
    private Connection connection;

    public GradaRepository() throws SQLException {
        this.connection = ConnectionUtil.getConnection();
    }

    public List<GradaService> getAllGrades() {
        List<GradaService> grades = new ArrayList<>();

        try {
            PreparedStatement pst = connection.prepareStatement("SELECT grada, koeficient FROM grada");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                GradaService rd = new GradaService();
                rd.setGrada(rs.getString("grada"));
                rd.setkoeficient(rs.getString("koeficient"));
                grades.add(rd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return grades;
    }

    public void updateGrade(String koeficient, int grada) {
        try {
            PreparedStatement pst = connection.prepareStatement("UPDATE grada SET koeficient = ? WHERE grada = ?");
            pst.setString(1, koeficient);
            pst.setInt(2, grada);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("You are about to change the data!");
            alert.setContentText("Do you want to make an update?");

            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(GradaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
