public class Radio implements IRadio {
    public boolean frequency; 
    public boolean power; 
    public double station; 
    public int buttonID; 
    double[] savedStationsAM;
    private double[] savedStationsFM;

    public Radio() {
        savedStationsAM = new double[12];
        savedStationsFM = new double[12];
        power = false; 
        frequency = true;
        station = 530;
    }

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
        if (isAM()) {
            savedStationsAM[buttonID - 1] = station;
        } else {
            savedStationsFM[buttonID - 1] = station;
        }
    }

    @Override
    public double selectStation(int buttonID) {
        if (isAM()) {
            return savedStationsAM[buttonID - 1]; 
        } else {
            return savedStationsFM[buttonID - 1];
        }
    }

    @Override
    public void switchAMFM() {
        frequency = !frequency; 
        if (frequency == true){ //Se cambian los valores si se modifica la frecuencia
            station = 530; 
            } 
            else {
            station = 87.9; 
        } 
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
    

   
}
