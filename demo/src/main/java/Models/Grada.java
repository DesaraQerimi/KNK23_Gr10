package Models;

public class Grada {
    private int grada;
    private double koeficient;

    public Grada(int grada, double koeficient) {
        this.grada = grada;
        this.koeficient = koeficient;
    }

    public int getGrada() {
        return grada;
    }

    public void setGrada(int grada) {
        this.grada = grada;
    }

    public double getkoeficient() {
        return koeficient;
    }

    public void setkoeficient(double koeficient) {
        this.koeficient = koeficient;
    }
}
