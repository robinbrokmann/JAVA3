package nl.drenthecollege.student.robinbrokmann.eindopdracht1;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AfbeeldingHandler {
    /**
     * Hier kunnen de afbeeldingen getest worden.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(200, 200);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Memory Test");
        label.setFont(new Font("Serif", Font.PLAIN, 24));
        label.setBounds(30, 0, 200, 40);
        frame.add(label);

        JLabel image = new JLabel();
        image.setBounds(120, 60, 70, 70);
        image.setBorder(new LineBorder(Color.black, 1));

        JTextField textField = new JTextField();
        textField.setBounds(40, 80, 20, 20);

        JButton button = new JButton("Test");
        button.setBounds(10, 110, 90, 20);
        button.addActionListener(e -> {
            try {
                int cijfer = Integer.parseInt(textField.getText());
                image.setIcon(getAfbeeldingVanCijfer(cijfer));
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(frame, "Dit is geen geldig nummer!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        JButton randomButton = new JButton("Genereer");
        randomButton.setBounds(10, 130, 90, 20);
        randomButton.addActionListener(e -> {
            try {
                Spelbord bord = new Spelbord();
                int random = bord.getRandomNummer();
                image.setIcon(getAfbeeldingVanCijfer(random));
                textField.setText(String.valueOf(random));
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(frame, "Dit is geen geldig nummer!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(textField);
        frame.add(image);
        frame.add(button);
        frame.add(randomButton);
        frame.setVisible(true);
    }

    /**
     * Geeft een afbeelding van het gegeven cijfer.
     *
     * @param cijfer Het cijfer.
     *
     * @return De afbeelding als ImageIcon.
     */
    public static ImageIcon getAfbeeldingVanCijfer(int cijfer) {
        switch (cijfer) {
            case 1:
                return resizeIcon(new ImageIcon("resources/great-war.jpg"));
            case 2:
                return resizeIcon(new ImageIcon("resources/CoatOfArms.jpg"));
            case 3:
                return resizeIcon(new ImageIcon("resources/CarolusRex.jpg"));
            case 4:
                return resizeIcon(new ImageIcon("resources/ArtOfWar.jpg"));
            case 5:
                return resizeIcon(new ImageIcon("resources/Heroes.jpg"));
            case 6:
                return resizeIcon(new ImageIcon("resources/LastStand.jpg"));
            case 7:
                return resizeIcon(new ImageIcon("resources/Metalizer.jpg"));
            case 8:
                return resizeIcon(new ImageIcon("resources/primoVictoria.jpg"));
            default:
                return resizeIcon(new ImageIcon("resources/unknown.png"));
        }
    }


    /**
     * Past de grote van het ImageIcon aan naar het formaat dat gebruikt wordt door het JPanel.
     *
     * @param icon De afbeelding.
     *
     * @return De aangepaste afbeelding.
     */
    private static ImageIcon resizeIcon(ImageIcon icon) {
        return new ImageIcon(icon.getImage().getScaledInstance(170, 170, Image.SCALE_DEFAULT));
    }
}
