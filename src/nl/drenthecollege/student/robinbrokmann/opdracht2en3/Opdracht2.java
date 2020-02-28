package nl.drenthecollege.student.robinbrokmann.opdracht2en3;

public class Opdracht2 {

    public static void main(String[] args) {
        Auto auto = new Auto();

        auto.setMerk("Mercedes");
        auto.setType("S350d");
        auto.setKleur("selenitgrau metallic");

        auto.setGewicht(1875);
        //auto.setBelasting(570.00);

        BelastingHandler belastingHandler = new BelastingHandler();
        auto.setBelasting(belastingHandler.berekenBelasting(auto.getGewicht()));

        System.out.println("De wegenbelasting bedraagt "+
                auto.getBelasting()+" euro per maand");
    }


}
