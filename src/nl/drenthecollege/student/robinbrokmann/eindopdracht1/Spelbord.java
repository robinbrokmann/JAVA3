package nl.drenthecollege.student.robinbrokmann.eindopdracht1;

import java.util.HashMap;
import java.util.Map;

public class Spelbord {
    /**
     * De multidimensionale array waar de kaarten in worden opgeslagen.
     */
    private Kaart[][] kaarten = new Kaart[4][4];

    /**
     * De hashmap waar alle gebruikte getallen worden opgeslagen.
     * (Getal -> Aantal keer gebruikt)
     */
    private Map<Integer, Integer> gebruikteNummers = new HashMap<>();

    /**
     * Het laatst gebruikte nummer.
     * Dit voorkomt dat 2 kaarten naast elkaar komen.
     */
    private int laatsteNummer;

    public Spelbord(){
        this.laatsteNummer = 0;
    }

    /**
     * Voegt een Kaart object toe aan de array.
     *
     * @param rij De rij van de Kaart.
     * @param kolom De kolom van de Kaart.
     * @param kaart De kaart zelf.
     */
    public void voegKaartToe(int rij, int kolom, Kaart kaart){
        this.kaarten[rij][kolom] = kaart;
    }

    /**
     * Genereerd een willekeurig nummer van 1 tot 8;
     *
     * @return Het willekeurige getal.
     */
    public int getRandomNummer(){
        return getRandomNummer(0);
    }

    /**
     * Genereerd een willekeurig nummer van 1 tot 8;
     *
     * @param tries Het aantal keer dat het niet gelukt is om een getal te genereren.
     *              Dit gebeurt wanneer er wordt geprobeert om 2 dezelfde kaarten naast elkaar te zetten.
     *              Wanneer dit 5 keer niet lukt dan wordt dit toegestaan.
     *
     * @return Het willekeurige getal.
     */
    private int getRandomNummer(int tries){
        //Het willekeurige getal.
        double randomDouble = Math.random();
        randomDouble = randomDouble * 8 + 1;
        int random = (int) randomDouble;
        //Als het nog niet gebruikt is.
        if(!gebruikteNummers.containsKey(random)){
            gebruikteNummers.put(random, 1);
            laatsteNummer = random;
            return random;
        }
        //Wanneer dit getal hetzelfde is als het laatst gebruikte getal.
        if(random == laatsteNummer){
            //Als het 5 keer is gebeurt, laat het dan toe.
            if(tries > 5){
                //Alleen wanneer het getal nog maar 1 keer is gebruikt.
                if(gebruikteNummers.get(random) < 2) {
                    return random;
                }
            }
            //Genereer een nieuw nummer (recursief)
            return getRandomNummer(tries + 1);
        }
        //Wanneer het getal 1 keer gebruikt is.
        if(gebruikteNummers.get(random) == 1){
            gebruikteNummers.put(random, 2);
            laatsteNummer = random;
            return random;
        }
        //Genereer een nieuw nummer (recursief)
        return getRandomNummer(tries);
    }

    /**
     * Returned de array met Kaarten.
     *
     * @return De multidimensionale array.
     */
    public Kaart[][] getKaarten() {
        return kaarten;
    }
}
