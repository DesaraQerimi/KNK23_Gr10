package Services;

import Repositories.GradaRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.SQLException;
import java.util.List;

public class GradaService {
    private final StringProperty grada;
    private final StringProperty koeficient;

    public GradaService(){
        grada = new SimpleStringProperty(this, "grada");
        koeficient = new SimpleStringProperty(this, "koeficient");
    }

    public String getGrada() {
        return grada.get();
    }

    public String getkoeficient() {
        return koeficient.get();
    }

    public void setGrada(String value) {
        grada.set(value);
    }

    public void setkoeficient(String value) {
        koeficient.set(value);
    }

    public StringProperty gradaProperty(){
        return grada;
    }

    public StringProperty koeficientProperty(){
        return koeficient;
    }

    public List<GradaService> getAllGrades() {
        GradaRepository gradaRepository;
        List<GradaService> grades = null;
        try {
            gradaRepository = new GradaRepository();
            grades = gradaRepository.getAllGrades();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // handle the exception
        }
        return grades;
    }

    public void updateGrade(String koeficient, int grada) {
        GradaRepository gradaRepository;
        try {
            gradaRepository = new GradaRepository();
            gradaRepository.updateGrade(koeficient, grada);
        } catch (SQLException ex) {
            ex.printStackTrace();
            // handle the exception
        }
    }
}
