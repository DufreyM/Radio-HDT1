import javax.swing.SwingUtilities;

/**
 * Nombre del programa: Hoja de Trabajo 1 - Radio
 * Descripción: Simulación de radio, algoritmos y estructuras de datos. 
 * Autores: Leonardo Dufrey Mejía Mejía, Maria José Girón Isidro
 * Fecha de creación: 12 de enero de 2024
 * Fecha de última modificación: 16 de enero de 2024
 * Fuentes de información: [crédito a toda fuente de información que haya aportado al desarrollo del programa]
 * Programación ATS. (2018, April 9). 86. Programación en Java || Gráficas || Ventanas (JFrame) - Creación de una ventana [Video]. YouTube. https://www.youtube.com/watch?v=RF7Ko3AgRf8
 * Programando en JAVA. (2022, June 9). PRUEBAS UNITARIAS en JAVA (JUNIT 5) - Tutorial Completo Fácil [Video]. YouTube. https://www.youtube.com/watch?v=74sClDEYSQ4
 */


public class Principal {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RadioGUI();
        });
    }
}