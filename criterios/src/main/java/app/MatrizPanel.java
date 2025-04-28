package app;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class MatrizPanel extends JPanel {

    private final int filas;
    private final int columnas;
    private final JTextField[][] campos;

    public MatrizPanel(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.campos = new JTextField[filas][columnas];

        setLayout(new GridLayout(filas, columnas, 5, 5));

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                campos[i][j] = new JTextField();
                campos[i][j].setHorizontalAlignment(JTextField.CENTER);
                add(campos[i][j]);
            }
        }
    }

    public double[][] obtenerMatriz() throws NumberFormatException {
        double[][] matriz = new double[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String texto = campos[i][j].getText().trim();
                if (texto.isEmpty()) {
                    throw new NumberFormatException("Campo vacío en (" + (i + 1) + ", " + (j + 1) + ")");
                }
                // Permitir números con coma o punto decimal
                texto = texto.replace(',', '.');
                matriz[i][j] = Double.parseDouble(texto);
            }
        }
        return matriz;
    }
}
