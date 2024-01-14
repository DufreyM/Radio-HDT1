import javax.swing.SwingUtilities;

/**
 * Nombre del programa: Hoja de Trabajo 1 - Radio
 * Descripción: Simulación de radio, algoritmos y estructuras de datos. 
 * Autores: Leonardo Dufrey Mejía Mejía, Maria José Girón Isidro
 * Fecha de creación: 12 de enero de 2024
 * Fecha de última modificación: 16 de enero de 2024
 * Fuentes de información: [crédito a toda fuente de información que haya aportado al desarrollo del programa]
 */


public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RadioGUI();
        });
    }
}