package Services;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RoliDetails {
    private final StringProperty roli_id;
    private final StringProperty grada;
    private final StringProperty departamenti;
    private final StringProperty titulli;


    public RoliDetails(){
        roli_id = new SimpleStringProperty(this, "roli_id");
        grada = new SimpleStringProperty(this, "grada");
        departamenti = new SimpleStringProperty(this, "departamenti");
        titulli = new SimpleStringProperty(this, "titulli");
    }

    public String getRoli_id() {
        return roli_id.get();
    }

    public String getGrada() {
        return grada.get();
    }

    public String getDepartamenti() {
        return departamenti.get();
    }

    public String getTitulli() {
        return titulli.get();
    }


    public void setRoli_id(String value) {
        roli_id.set(value);
    }

    public void setGrada(String value) {
        grada.set(value);
    }

    public void setDepartamenti(String value) {
        departamenti.set(value);
    }

    public void setTitulli(String value) {
        titulli.set(value);
    }



    public StringProperty roliIdProperty(){
        return roli_id;
    }

    public StringProperty gradaProperty(){
        return grada;
    }

    public StringProperty departamentiProperty(){
        return departamenti;
    }

    public StringProperty titulliProperty(){
        return titulli;
    }
}
