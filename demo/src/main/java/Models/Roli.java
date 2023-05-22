package Models;

public class Roli {
    private int id;
    private Grada grada;
    private String departamenti;
    private String titulli;

    public Roli(int id, Grada grada, String departamenti, String titulli) {
        this.id = id;
        this.grada = grada;
        this.departamenti = departamenti;
        this.titulli = titulli;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Grada getGrada() {
        return grada;
    }

    public void setGrada(Grada grada) {
        this.grada = grada;
    }

    public String getDepartamenti() {
        return departamenti;
    }

    public void setDepartamenti(String departamenti) {
        this.departamenti = departamenti;
    }

    public String getTitulli() {
        return titulli;
    }

    public void setTitulli(String titulli) {
        this.titulli = titulli;
    }
}
