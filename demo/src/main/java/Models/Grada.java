package Models;

public class Grada {
    private int grada;
    private double v_koeficient;

    public Grada(int grada, double v_koeficient) {
        this.grada = grada;
        this.v_koeficient = v_koeficient;
    }

    public int getGrada() {
        return grada;
    }

    public void setGrada(int grada) {
        this.grada = grada;
    }

    public double getV_koeficient() {
        return v_koeficient;
    }

    public void setV_koeficient(double v_koeficient) {
        this.v_koeficient = v_koeficient;
    }
}
