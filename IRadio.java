/**
 * IRadio
 */
public interface IRadio {
    void saveStation(int buttonID, double station); 
    boolean isAM();
    boolean isOn();
    double selectStation(int buttonID); 
    void switchOnOff(); 
    void switchAMFM(); 
    double nextStation(); 
    
}