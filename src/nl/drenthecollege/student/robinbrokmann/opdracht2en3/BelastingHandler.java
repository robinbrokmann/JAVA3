package nl.drenthecollege.student.robinbrokmann.opdracht2en3;

import java.util.ArrayList;
import java.util.List;

public class BelastingHandler {

    private List<Belasting> list = new ArrayList<>();

    public BelastingHandler(){
        laadBelastingsLijst();
    }

    public double berekenBelasting(int gewicht){
        for(Belasting b : list){
            if(gewicht <= b.getMinimumGewicht()){
                return b.getBelasting();
            }
        }
        return 0;
    }

    /**
     * Genereerd een lijst met Belastings objecten voor alle niveaus van wegenbelasting.
     * (Alleen voor Auto's in Drenthe).
     *
     * @return Een lijst met Belasting objecten.
     */
    private void laadBelastingsLijst(){
        int[] belasting = new int[]{30, 38, 47, 62, 81, 106, 130, 154, 179,
                203, 227, 252, 276, 300, 325, 349, 373, 398, 422, 446, 471,
                495, 519, 544, 568, 592, 617, 641, 662, 682, 702, 722, 742,
                762, 783, 803, 823, 843, 863, 883, 904, 924, 944, 964, 984};

        int gewichtKeer = 0;
        for(int i : belasting){
            list.add(new Belasting((gewichtKeer * 100) + 550, i));
            gewichtKeer++;
        }
    }
}
