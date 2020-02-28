package nl.drenthecollege.student.robinbrokmann.Eindopdracht2;

import nl.drenthecollege.student.robinbrokmann.eindopdracht1.Persoon;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonenSQLHandler {

    private Connection connect;

    /**
     * Een lijst met alle SQL ID's.
     * Hiermee kan het ID wat bij de geklikte jList index hoort worden opgevraagd zodat het programma weet welk SQL record het moet verwijderen.
     */
    private List<Integer> idList = new ArrayList<>();

    public PersonenSQLHandler(){
        try {
            connectDatabase();
        } catch (SQLException e) {
            //Geef een error wanneer er geen verbinding kan worden gemaakt met de SQL database.
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Kon geen verbinding maken met SQL database!", "SQL Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        maakTabel();

        //Sluit de database connectie wanneer het programma wordt afgesloten.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, "Shutdown-thread"));
    }

    /**
     * Vervind met de database.
     *
     * @throws SQLException
     */
    public void connectDatabase() throws SQLException{
        String database = "namen";
        String user = "DC";
        String password = "admin";
        connect = DriverManager.getConnection("jdbc:mysql://localhost/" + database  +"?"
                + "user=" + user + "&password=" + password + "&useLegacyDatetimeCode=false&serverTimezone=Europe/Amsterdam");
    }

    /**
     * Maakt een SQL tabel aan als deze nog niet bestaat.
     */
    public void maakTabel(){
        try {
            PreparedStatement preparedStatement = connect
                    .prepareStatement("create table if not exists namen (ID int AUTO_INCREMENT PRIMARY KEY , Voornaam varchar(16), Achternaam varchar(16))");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Kon geen nieuwe tabel aanmaken!", "SQL Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Laad alle personen uit de database en vul de idList met de ID's.
     *
     * @return Een DefaultListModel met alle Personen.
     */
    public DefaultListModel<String> laadPersonen(){
        DefaultListModel<String> model = new DefaultListModel<>();
        try {
            // Statements allow to issue SQL queries to the database
            Statement statement = connect.createStatement();
            // Result set get the result of the SQL query
            ResultSet resultSet = statement.executeQuery("select * from namen");

            //Voeg de personen toe.
            while (resultSet.next()){
                model.add(model.size(), resultSet.getString("Voornaam") + " " + resultSet.getString("Achternaam"));
                idList.add(resultSet.getInt("ID"));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Kon Personen niet uit SQL laden!", "SQL Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return model;
    }

    /**
     * Voeg een persoon toe aan de database.
     *
     * @param persoon Het persoon object met de gegevens van de persoon.
     */
    public void voegPersoonToe(Persoon persoon){
        try {
            PreparedStatement preparedStatement = connect
                    .prepareStatement("insert into namen values (default, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, persoon.getVoornaam());
            preparedStatement.setString(2, persoon.getAchternaam());
            preparedStatement.executeUpdate();

            //Pak het gegenereerde ID zodat het in de idList kan worden geplaatst.
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                idList.add(rs.getInt(1));
            }

            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Persoon " + persoon.getVoornaam() + " " + persoon.getAchternaam() + " kon niet worden toegevoegd aan de database!", "SQL Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Verwijder een persoon uit de database.
     *
     * @param index De jList index van het geklikte persoon.
     */
    public void verwijderPersoon(int index){
        int ID = idList.get(index);
        try {
            PreparedStatement preparedStatement = connect
                    .prepareStatement("delete from namen where ID= ?");
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();

            preparedStatement.close();

            idList.remove(index);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"De persoon kon niet verwijdert worden uit de database!", "SQL Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
