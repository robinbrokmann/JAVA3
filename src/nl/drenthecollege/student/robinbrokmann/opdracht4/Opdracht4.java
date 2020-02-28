package nl.drenthecollege.student.robinbrokmann.opdracht4;

import nl.drenthecollege.student.robinbrokmann.eindopdracht1.Persoon;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Opdracht4 {

    private static JFrame frame;
    /**
     * De lijst met Persoon objecten.
     */
    private static List<Persoon> lijst = new ArrayList<>();

    private static int clickedIndex = -1;

    public static void main(String[] args) {
        loadFrame();
        persoonOverzicht();
    }

    /**
     * Stel de basis dingen in voor het JFrame.
     */
    private static void loadFrame(){
        frame = new JFrame();
        frame.setLayout(null);
        //Blokeer het vergroten en verkleinen van het frame.
        frame.setResizable(false);
        //Stop het process wanneer het frame gesloten wordt.
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Het menu met een overzicht van alle personen in de lijst.
     */
    private static void persoonOverzicht(){
        JLabel label = new JLabel("Persoon Overzicht");
        label.setFont(new Font("Serif", Font.PLAIN, 24));
        label.setBounds(110, 0, 200, 40);
        frame.add(label);

        JButton button = new JButton("Toevoegen");
        button.setBounds(70, 290, 110, 50);
        button.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            editMenu();
        });
        frame.add(button);

        //Maak de JList aan.
        DefaultListModel<String> model = new DefaultListModel<>();
        JList jList = new JList<>(model);

        JButton deleteButton = new JButton("Verwijderen");
        deleteButton.setBounds(220, 290, 110, 50);
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(e -> {
            lijst.remove(clickedIndex);
            model.remove(clickedIndex);
            deleteButton.setEnabled(false);
        });
        frame.add(deleteButton);

        //Wanneer er op de lijst wordt geklikt.
        jList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                if(index > -1){
                    clickedIndex = index;
                    deleteButton.setEnabled(true);
                }
            }
            });

        //Voeg de personen toe.
        for(Persoon p : lijst){
            model.add(model.size(), p.getVoornaam() + " " + p.getAchternaam());
        }

        //Doe de lijst in een scroll pane wat het laat scrollen wanneer de lijst te vol wordt.
        JScrollPane scrollPane = new JScrollPane(jList);
        scrollPane.setBounds(100,60, 200,200);
        frame.add(scrollPane);

        frame.setSize(400,400);
        frame.setVisible(true);
    }

    /**
     * Het menu waar je een persoon aan de lijst kan toevoegen.
     */
    private static void editMenu(){
        JLabel label = new JLabel("Persoon Toevoegen");
        label.setFont(new Font("Serif", Font.PLAIN, 24));
        label.setBounds(50, 0, 200, 40);
        frame.add(label);

        JLabel voornaamLabel = new JLabel("Voornaam");
        JLabel achternaamLabel = new JLabel("Achternaam");
        voornaamLabel.setBounds(50, 60, 70, 20);
        achternaamLabel.setBounds(50, 120, 70, 20);
        frame.add(voornaamLabel);
        frame.add(achternaamLabel);

        JTextField voornaamField = new JTextField();
        JTextField achternaamField = new JTextField();
        voornaamField.setBounds(50,80, 200,30);
        achternaamField.setBounds(50,140, 200,30);
        frame.add(voornaamField);
        frame.add(achternaamField);

        JLabel errorLabel = new JLabel("Alle velden zijn verplicht.");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(50, 170, 200, 20);
        errorLabel.setVisible(false);
        frame.add(errorLabel);

        JButton button = new JButton("Toevoegen");
        button.setBounds(40, 200, 100, 50);
        button.addActionListener(e -> {
            //Of er een ingeldige invoer is gegeven.
            boolean invalidInput = false;

            if(!checkTekstVeld(voornaamField)){
                invalidInput = true;
            }
            if(!checkTekstVeld(achternaamField)){
                invalidInput = true;
            }
            if(!invalidInput) {
                //Als er een geldige invoer is gegeven.
                Persoon persoon = new Persoon(voornaamField.getText(), achternaamField.getText());
                lijst.add(persoon);
                frame.getContentPane().removeAll();
                frame.repaint();
                persoonOverzicht();
            } else {
                //Als er een ongeldige invoer is gegeven.
                errorLabel.setVisible(true);
            }
        });
        frame.add(button);
        JButton backButton = new JButton("Terug");
        backButton.setBounds(160, 200, 100, 50);
        backButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            persoonOverzicht();
        });
        frame.add(backButton);
        frame.setSize(300,300);
    }

    /**
     * Kijkt of het gegeven tekst veld leeg is.
     *
     * @param tekstvVeld Het tekst veld.
     * @return Of het gegeven tekstveld een geldige inhoud heeft.
     */
    private static boolean checkTekstVeld(JTextField tekstvVeld){
        if(tekstvVeld.getText().isEmpty()){
            tekstvVeld.setBorder(new LineBorder(Color.RED, 1));
            return false;
        } else {
            tekstvVeld.setBorder(new LineBorder(Color.GRAY, 1));
            return true;
        }
    }
}
