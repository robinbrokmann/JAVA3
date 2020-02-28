package nl.drenthecollege.student.robinbrokmann.eindopdracht1;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class KaartButton extends JButton {

    private final Kaart kaart;

    public KaartButton(Kaart kaart){
        this.kaart = kaart;
    }

    /**
     * Returned de Kaart die bij deze button hoort.
     * @return
     */
    public Kaart getKaart() {
        return kaart;
    }

    /**
     * Laat de afbeelding van de kaart zien wanneer er op is geklikt.
     */
    public void showIcon(){
        setIcon(kaart.getAfbeelding());
        setDisabledIcon(kaart.getAfbeelding());
    }

    /**
     * Markeert de button als geklikt.
     */
    public void markeerSelectie(){
        setBorder(new LineBorder(Color.BLUE, 2));
    }

    /**
     * Markeer de button als een foute combinatie.
     */
    public void markeerFout(){
        setBorder(new LineBorder(Color.RED, 2));
    }

    /**
     * Reset de makering en haalt de afbeelding weg.
     */
    public void resetMarkering(){
        setBorder(new LineBorder(Color.GRAY, 1));
        setIcon(null);
        setDisabledIcon(null);
    }

    /**
     * Makeer de kaart wanneer 2 dezelfde kaarten zijn geklikt.
     * Deze kaarten worden dan grijs.
     */
    public void markeerGoed(){
        setBorder(new LineBorder(Color.GREEN, 2));
        setDisabledIcon(null);
    }
}
