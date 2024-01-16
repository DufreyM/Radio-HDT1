import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class testRadio {
    private Radio radio;

    @Before
    public void setUp() {
        radio = new Radio();
    }
    
    @Test
    public void testNextStation() {
        radio.setFrequency(true);
        radio.setStation(530);
        assertEquals(540, radio.nextStation(), 0.001);
        
        radio.setFrequency(false);
        radio.setStation(87.9);
        assertEquals(88.1, radio.nextStation(), 0.001);
    }

    @Test
    public void testSaveStationAM() {
        radio.setFrequency(true);

        radio.saveStation(1, 540);
        assertEquals(540, radio.selectStation(1), 0.001); 

        radio.saveStation(2, 550);
        assertEquals(550, radio.selectStation(2), 0.001); 

        radio.setFrequency(false);
        radio.saveStation(1, 88.1);
        assertEquals(88.1, radio.selectStation(1), 0.001);
        
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSaveStationInvalidButtonID() {
        radio.setFrequency(true);

        radio.saveStation(13, 540);
    }
    
}
