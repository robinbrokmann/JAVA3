import nl.drenthecollege.student.robinbrokmann.eindopdracht1.Persoon;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class PersonenTest {

    @Test
    void testNaam(){
        String voornaam = "Robin";
        String achternaam = "Brokmann";
        Persoon persoon = new Persoon(voornaam, achternaam);
        assertEquals(voornaam, persoon.getVoornaam());
        assertEquals(achternaam, persoon.getAchternaam());
    }
}
