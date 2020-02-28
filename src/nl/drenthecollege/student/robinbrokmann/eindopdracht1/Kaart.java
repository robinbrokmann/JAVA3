package nl.drenthecollege.student.robinbrokmann.eindopdracht1;

import javax.swing.*;

public class Kaart {

    private int waarde;
    private ImageIcon icon;

    public Kaart(int waarde, ImageIcon icon){
        this.waarde = waarde;
        this.icon = icon;
    }

    /**
     * Returned het getal dat bij deze kaart hoort.
     * Wordt gebruikt om te controleren of 2 keer dezelfde kaart is geselecteerd.
     *
     * @return Het cijfer.
     */
    public int getWaarde() {
        return waarde;
    }

    /**
     * Returned de afbeelding die bij deze Kaart hoort.
     *
     * @return De afbeelding.
     */
    public ImageIcon getAfbeelding(){
        return icon;
    }
}
