package Services;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GradaDetails {
    private final StringProperty grada;
    private final StringProperty koeficient;

    public GradaDetails(){
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
}
