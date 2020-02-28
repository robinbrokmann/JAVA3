import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {

    @Test
    void testSQLConnection(){
        try {
            //Dit zijn aparte variabelen zodat de database gegevens makkelijk kunnen worden aangepast.
            String database = "dc";
            String user = "DC";
            String password = "admin";
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/" + database  +"?"
                    + "user=" + user + "&password=" + password + "&useLegacyDatetimeCode=false&serverTimezone=Europe/Amsterdam");
            connect.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
