/**
 * Universidad del Valle de Guatemala
 * 
 * @author Angie Nadissa Vela López, 23764
 * @author Mia Alejandra Fuentes Merida, 23775
 * @description clase que modela el funcionamiento de un radio
 * @date creación 09/01/2023 última modificación 16/01/2023
 */
public class RadioOtraPareja implements IRadio {

    private boolean flagSelectStation;
    private boolean status;
    // private double actualStation;
    private boolean isAm;
    private double currentStation;
    private double[] stationsAM;
    private double[] stationsFM;

    public RadioOtraPareja() {
        this.flagSelectStation = false;
        this.status = false;
        // this.actualStation = 530.0;
        this.isAm = true; // AM -> true FM -> false
        this.stationsAM = new double[12];
        this.stationsFM = new double[12];
        this.currentStation = 530;
    }

    public void saveStation(int buttonId, double station) {
        
        if (buttonId < 1 || buttonId > 12) {
            // El boton no debe ser menos a 1 ni mayor a 12 (1 a 12)
            System.out.println("ID de botón inválido");
            return;
        }
        if (isAM()) {
            stationsAM[buttonId - 1] = station;
        } else {
            stationsFM[buttonId - 1] = station;
        }
        System.out.println("Estación guardada con éxito!");

    }

    public void switchOnOff() {
        status = !status;
    }

    public void switchAMFM() {
        isAm = !isAm;
        flagSelectStation = false;
    }

    public double selectStation(int buttonId) {

        if (buttonId < 1 || buttonId > 12) {
            System.out.println("Número de botón inválido");
            currentStation = -1;
        } else {
            // Verifica en que emisora se encuentra antes de regresar la emisoria que el
            // usuario guardó
            currentStation = isAm ? stationsAM[buttonId - 1] : stationsFM[buttonId - 1];
            flagSelectStation = true;
        }

        return currentStation;
    }

    public boolean isAM() {
        return isAm;
    }

    public boolean isOn() {
        return status;
    }

    public void verifyStation() {
        if (flagSelectStation) {
            if (currentStation == 0.0) {
                if (isAM()) {
                    currentStation = 530;
                } else {
                    currentStation = 87.9;
                }
            }

        } else {
            if (isAM()) {
                currentStation = 530;
            } else {
                currentStation = 87.9;
            }
        }
    }

    public double nextStation() {

        verifyStation();
        double add = 0.0, range = 0.0;

        if (isAM()) {
            add = 10;
            range = 1610;
        } else {
            add = 0.2;
            range = 107.9;
        }

        currentStation += add;

        if ((currentStation > range) && (add == 10)) {
            currentStation = 530;
        } else if ((currentStation > range) && (add == 107.9)) {
            currentStation = 87.9;
        }
        flagSelectStation = true;
        return currentStation;
    }

}
