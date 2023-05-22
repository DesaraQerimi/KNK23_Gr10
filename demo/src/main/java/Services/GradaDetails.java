package Services;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GradaDetails {
    private final StringProperty grada;
    private final StringProperty v_koeficient;

    public GradaDetails(){
        grada = new SimpleStringProperty(this, "grada");
        v_koeficient = new SimpleStringProperty(this, "v_koeficient");
    }

    public String getGrada() {
        return grada.get();
    }

    public String getV_koeficient() {
        return v_koeficient.get();
    }

    public void setGrada(String value) {
        grada.set(value);
    }

    public void setV_koeficient(String value) {
        v_koeficient.set(value);
    }

    public StringProperty gradaProperty(){
        return grada;
    }

    public StringProperty v_koeficientProperty(){
        return v_koeficient;
    }
}
