import org.junit.Test;
import static org.junit.Assert.*;

public class RadioTest {

    @Test
    public void testNextStationAM() {
        Radio radio = new Radio();
        radio.frequency = true; 
        radio.station = 530;

        assertEquals(540.0, radio.nextStation(), 0.01);

        radio.station = 1610;
        assertEquals(530.0, radio.nextStation(), 0.01);
    }

    @Test
    public void testNextStationFM() {
        Radio radio = new Radio();
        radio.frequency = false; 
        radio.station = 87.9;

        assertEquals(88.1, radio.nextStation(), 0.01);

        radio.station = 107.9;
        assertEquals(87.9, radio.nextStation(), 0.01);
    }

    @Test
    public void testSaveStation() {
        Radio radio = new Radio();
        radio.frequency = true; 
        radio.saveStation(1, 600);

        assertEquals(600.0, radio.savedStationsAM[0], 0.01);
    }

    @Test
    public void testSelectStationAM() {
        Radio radio = new Radio();
        radio.frequency = true;
        radio.savedStationsAM[0] = 700;

        assertEquals(700.0, radio.selectStation(1), 0.01);
    }

}
