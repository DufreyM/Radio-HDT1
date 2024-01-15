import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RadioGUI {

    private Radio radio;
    private JButton[] buttons;
    private JLabel statusLabel;
    private double currentDisplayedStation;

    public RadioGUI() {
        radio = new Radio();
        currentDisplayedStation = radio.getStation();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Radio La Tracalosa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JLabel stationLabel = new JLabel("Radio La Tracalosa", SwingConstants.CENTER);
        frame.add(stationLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 3));


        JButton powerButton = new JButton("Encender/Apagar");
        powerButton.addActionListener(new PowerButtonClickListener());
        panel.add(powerButton);

        JButton amFmButton = new JButton("Cambiar AM/FM");
        amFmButton.addActionListener(new AmFmButtonClickListener());
        panel.add(amFmButton);

        JButton nextStationButton = new JButton("Avanzar Estación");
        nextStationButton.addActionListener(new NextStationButtonClickListener());
        panel.add(nextStationButton);

        buttons = new JButton[12];
        for (int i = 0; i < 12; i++) {
            buttons[i] = new JButton("Botón " + (i + 1));
            buttons[i].setEnabled(false);
            buttons[i].addActionListener(new ButtonClickListener());
            buttons[i].addMouseListener(new LongPressMouseListener(buttons[i], i + 1));
            panel.add(buttons[i]);
        }

        statusLabel = new JLabel("Estado: Apagado");
        panel.add(statusLabel);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);
    }
    //BOTÓN PARA ENCENDER Y APAGAR
    private class PowerButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            radio.switchOnOff();
            updateButtons();
            updateStatusLabel();
        }
    }
    //BOTÓN PARA CAMBIAR DE AM Y FM 
    private class AmFmButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (radio.isOn()) {
                radio.switchAMFM();
                updateButtons();
                updateStatusLabel();
            } else {
                statusLabel.setText("La radio está apagada. Enciéndela para cambiar la frecuencia.");
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            String buttonText = clickedButton.getText();

            int buttonID = Integer.parseInt(buttonText.substring(buttonText.indexOf(" ") + 1));
            double savedStation = radio.selectStation(buttonID);

            currentDisplayedStation = savedStation;
            updateStatusLabel();
        }
    }

    private class LongPressMouseListener extends MouseAdapter {

        private int buttonID;
        private Timer timer;

        public LongPressMouseListener(JButton button, int buttonID) {
            this.buttonID = buttonID;
            this.timer = new Timer(5000, new LongPressTimerListener());
            this.timer.setRepeats(false);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            timer.start();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            timer.stop();
        }

        private class LongPressTimerListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radio.isOn()) {
                    double station = radio.getStation();
                    radio.saveStation(buttonID, station);

                    currentDisplayedStation = station;
                    updateStatusLabel();

                    statusLabel.setText("Botón " + buttonID + " mantenido presionado por más de 5 segundos. Estación guardada: " + station);
                } else {
                    statusLabel.setText("La radio está apagada. Enciéndela para acceder a esta función.");
                }
            }
        }
    }

    private class NextStationButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (radio.isOn()) {
                radio.nextStation();
                currentDisplayedStation = radio.getStation();
                updateStatusLabel();
            } else {
                statusLabel.setText("La radio está apagada. Enciéndela para avanzar de estación.");
            }
        }
    }

    private void updateButtons() {
        boolean radioOn = radio.isOn();
        for (JButton button : buttons) {
            button.setEnabled(radioOn);
        }
    }

    private void updateStatusLabel() {
        String stateText = radio.isOn() ? "Encendida" : "Apagada";
        String modeText = radio.isAM() ? "AM" : "FM";
        double station = currentDisplayedStation;
        statusLabel.setText("Estado: " + stateText + " | Modo: " + modeText + " | Estación: " + station);
    }

}
