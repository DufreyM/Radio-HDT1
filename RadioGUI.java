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

    public RadioGUI() {
        radio = new Radio();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Radio GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

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

    private class PowerButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            radio.switchOnOff();
            updateButtons();
            updateStatusLabel();
        }
    }

    private class AmFmButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (radio.isOn()) {
                radio.switchAMFM();
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
            statusLabel.setText("Botón clickeado: " + buttonText);
            // Puedes agregar aquí la lógica para interactuar con la clase Radio según el botón clickeado
        }
    }

    private class LongPressMouseListener extends MouseAdapter {
        private JButton button;
        private int buttonID;
        private Timer timer;

        public LongPressMouseListener(JButton button, int buttonID) {
            this.button = button;
            this.buttonID = buttonID;
            timer = new Timer(5000, new LongPressTimerListener());
            timer.setRepeats(false);
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
                    statusLabel.setText("Botón " + buttonID + " mantenido presionado por más de 5 segundos");
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
        double station = radio.getStation();
        statusLabel.setText("Estado: " + stateText + " | Modo: " + modeText + " | Estación: " + station);
    }

    
}

