import nl.drenthecollege.student.robinbrokmann.eindopdracht1.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MemoryTest {

    /**
     * Test of er 2 van iedere kaart zijn op het Spelbord.
     */
    @Test
    void testSpel(){
        Spelbord bord = new Spelbord();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        //Genereer de kaarten.
        for(int rij = 0; rij < 4; rij++){
            for(int kolom = 0; kolom < 4; kolom++){
                int randomWaarde = bord.getRandomNummer();
                Kaart kaart = new Kaart(randomWaarde, AfbeeldingHandler.getAfbeeldingVanCijfer(randomWaarde));
                if(kaart.getAfbeelding() == null){
                    System.out.println(randomWaarde + " is null!");
                }
                if(kaart.getAfbeelding().getImage() == null){
                    System.out.println(randomWaarde + " img is null!");
                }
                KaartButton button = new KaartButton(kaart);
                button.addActionListener(e -> Eindopdracht1.klikKaart(button));
                panel.add(button);
            }
        }

        Map<Integer, Integer> valueMap = new HashMap<>();

        //Voeg alle components toe aan de Map.
        for (Component component : panel.getComponents()) {
            int waarde = ((KaartButton) component).getKaart().getWaarde();
            int count = valueMap.getOrDefault(waarde, 0);
            valueMap.put(waarde, count + 1);
        }

        //Kijkt of er van elke kaartwaarde 2 zijn.
        for(Map.Entry e : valueMap.entrySet()){
            System.out.println("Checking number " + e.getKey());
            assertEquals(e.getValue(), 2);
            System.out.println("Matched!");
        }
    }

    /**
     * Test of de afbeeldingen bestaan en willen laden.
     */
    @Test
     void getAfbeeldingVanCijfer() {
        createImageIcon("apple");
        createImageIcon("chrome");
        createImageIcon("dc");
        createImageIcon("edge");
        createImageIcon("eng");
        createImageIcon("nl");
        createImageIcon("ubuntu");
        createImageIcon("windows");
        createImageIcon("unknown");
    }

    private void createImageIcon(String imgName){
        System.out.println("Testing " + imgName);
        File file = new File("resources/" + imgName + ".png");
        assertTrue(file.exists());
    }
}
