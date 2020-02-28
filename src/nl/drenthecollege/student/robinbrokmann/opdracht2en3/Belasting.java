package nl.drenthecollege.student.robinbrokmann.opdracht2en3;

public class Belasting {

    private int minGewicht;
    private double belasting;

    public Belasting(int minGewicht, int belasting){
        this.minGewicht = minGewicht;
        this.belasting = belasting;
    }

    public int getMinimumGewicht() {
        return minGewicht;
    }

    public double getBelasting(){
        return belasting;
    }
}
