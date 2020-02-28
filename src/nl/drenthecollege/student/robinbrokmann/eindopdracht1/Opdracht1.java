package nl.drenthecollege.student.robinbrokmann.eindopdracht1;

public class Opdracht1 {

    public static void main(String[] args) {
        Persoon persoon = new Persoon();
        persoon.setVoornaam("Robin");
        persoon.setAchternaam("Brokmann");
        persoon.setGeboortedatum(21, 05, 1998);

        System.out.println("De leeftijd in dagen van " + persoon.getVoornaam() + " " + persoon.getAchternaam() + " is: " + persoon.getLeeftijdinDagen() + " dagen.");
    }
}
