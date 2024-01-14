public class Radio implements IRadio {
    public boolean frequency; 
    public boolean power; 
    public double station; 
    public int buttonID; 

    /** Constructs a new radio with the given initial settings. */

    @Override
    public boolean isAM() {
        if (frequency == true){
            return true; 
        }
        else {
            return false; 
        }
    }

    @Override
    public boolean isOn() {
        if (power == true){
            return true; 
        }
        else {
            return false; 
        }  
    }

    @Override
    public double nextStation() {
        if (isAM()) { 
            station += 10;
            if (station > 1610) {
                station = 530;
            }
        } else {
            station += 0.2;
            if (station > 107.9) {
                station = 87.9;
            }
        }
        return station;
    }
    
    @Override
    public void saveStation(int buttonID, double station) {
        
    }

    @Override
    public double selectStation(int buttonID) {
       
        return 0;
    }

    @Override
    public void switchAMFM() {
        frequency = !frequency; 
    }

    @Override
    public void switchOnOff() {
        power = !power; 
        if (!power) {
            if (frequency == true){
                station = 530; 
                } 
                else {
                station = 87.9; 
            }
            
        }
    }
    

    public boolean isFrequency() {
        return frequency;
    }

    public void setFrequency(boolean frequency) {
        this.frequency = frequency;
    }

    public boolean isPower() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }

    public double getStation() {
        return station;
    }

    public void setStation(double station) {
        this.station = station;
    }

    public int getButtonID() {
        return buttonID;
    }

    public void setButtonID(int buttonID) {
        this.buttonID = buttonID;
    }
    
}
