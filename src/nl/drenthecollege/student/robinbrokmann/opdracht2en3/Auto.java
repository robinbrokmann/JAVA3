package nl.drenthecollege.student.robinbrokmann.opdracht2en3;

public class Auto extends Vervoermiddel {
    private int gewicht;
    private double belasting;

    @Override
    public int getAantalWielen() {
        return 4;
    }

    public void setBelasting(double belasting) {
        this.belasting = belasting;
    }

    public double getBelasting() {
        return belasting;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }
}
