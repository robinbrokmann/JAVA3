package nl.drenthecollege.student.robinbrokmann.eindopdracht1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Persoon {

    private String voornaam;
    private String achternaam;
    private LocalDate geboortedatum;

    /**
     * Constructor dat gebruikt wordt voor Opdracht 1.
     */
    public Persoon(){}

    /**
     * Constructor dat gebruikt wordt voor Opdracht 4.
     * Hier is altijd een voornaam en achternaam aanwezig wanneer het Persoon object wordt aangemaakt.
     *
     * @param voornaam De voornaam van de persoon.
     * @param achternaam De achternaam van de persoon.
     */
    public Persoon(String voornaam, String achternaam){
        this.voornaam = voornaam;
        this.achternaam = achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setGeboortedatum(int dag, int maand, int jaar) {
        geboortedatum = LocalDate.of(jaar, maand, dag);
    }

    public long getLeeftijdinDagen(){
        return ChronoUnit.DAYS.between(geboortedatum, LocalDate.now());
    }
}
