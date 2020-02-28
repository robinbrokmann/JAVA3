package nl.drenthecollege.student.robinbrokmann.eindopdracht1;

import javax.swing.*;
import java.awt.*;

public class Eindopdracht1 {

    //De laatst geklikte button.
    private static KaartButton laatsteButton = null;
    //Of de timer niet actief is.
    private static boolean timerKlaar = true;

    public static void main(String[] args) {
        Spelbord bord = new Spelbord();

        //Genereer de kaarten.
        for(int rij = 0; rij < 4; rij++){
            for(int kolom = 0; kolom < 4; kolom++){
                int randomWaarde = bord.getRandomNummer();
                Kaart kaart = new Kaart(randomWaarde, AfbeeldingHandler.getAfbeeldingVanCijfer(randomWaarde));

                bord.voegKaartToe(rij, kolom, kaart);
            }
        }
        maakFrame(bord.getKaarten());
    }

    /**
     * Genereer het JFrame.
     *
     * @param kaarten De array met de gegenereerde kaarten.
     */
    private static void maakFrame(Kaart[][] kaarten){
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(800, 800);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Memory Spel");
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        label.setBounds(300, 0, 200, 40);
        frame.add(label);
        frame.add(maakPanel(kaarten));
        frame.setVisible(true);
    }

    /**
     * Genereer het JPanel met de KaartButtons.
     *
     * @param kaarten De array met de gegenereerde kaarten.
     *
     * @return Het nieuwe JPanel.
     */
    private static JPanel maakPanel(Kaart[][] kaarten){
        JPanel panel = new JPanel();
        panel.setBounds(50, 50, 700, 700);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        for(int rij = 0; rij < 4; rij++){
            for(int kolom = 0; kolom < 4; kolom++){
                Kaart kaart = kaarten[rij][kolom];
                KaartButton button = new KaartButton(kaart);
                button.addActionListener(e -> klikKaart(button));
                panel.add(button);
            }
        }
        return panel;
    }

    public static void klikKaart(KaartButton button){
        if(!timerKlaar){
            //Als de foute kaart animatie nog bezig is, doe dan niets.
            return;
        }
        //Laat de afbeelding van de knop zien.
        button.showIcon();

        //Wanneer er geen andere knop geselecteerd is.
        if(laatsteButton == null){
            laatsteButton = button;
            button.markeerSelectie();
            button.setEnabled(false);
            return;
        }

        //Waneer er 2 buttons met dezelfde afbeelding geselecteerd zijn. (Goed)
        if(button.getKaart().getWaarde() == laatsteButton.getKaart().getWaarde()){
            button.markeerGoed();
            button.setEnabled(false);
            laatsteButton.markeerGoed();
            laatsteButton = null;
        } else {
            //Wanneer de 2 kaarten niet gelijk zijn. (Fout)
            timerKlaar = false;
            button.markeerFout();
            button.setEnabled(false);
            laatsteButton.markeerFout();

            //Laat de kaarten een tijdje rood en stop input zodat het duidelijk is dat de selectie fout was.
            Timer timer = new Timer(500, ae -> {
                button.setEnabled(true);
                button.resetMarkering();
                laatsteButton.resetMarkering();
                laatsteButton.setEnabled(true);
                laatsteButton = null;
                timerKlaar = true;
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
}
