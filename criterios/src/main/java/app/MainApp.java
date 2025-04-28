package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainApp extends JFrame {

    private MatrizPanel matrizPanel;
    private final JPanel mainPanel;
    private final JButton definirMatrizButton;
    private final JButton calcularMaximinButton;
    private final JButton calcularMaximaxButton;
    private final JButton calcularHurwiczButton;
    private final JButton calcularLaplaceButton;
    private final JButton calcularMinimaxRegretButton;
    private final JButton calcularValorEsperadoButton;
    private final JTextField filasField;
    private final JTextField columnasField;

    public MainApp() {
        setTitle("Análisis de Matriz - Decisiones bajo Incertidumbre");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));

        // Panel Superior para definir matriz
        JPanel definirPanel = new JPanel();
        definirPanel.setBackground(new Color(220, 220, 250));
        definirPanel.setBorder(BorderFactory.createTitledBorder("Definir Matriz"));
        filasField = new JTextField(3);
        columnasField = new JTextField(3);
        definirMatrizButton = new JButton("Definir");

        definirPanel.add(new JLabel("Filas:"));
        definirPanel.add(filasField);
        definirPanel.add(new JLabel("Columnas:"));
        definirPanel.add(columnasField);
        definirPanel.add(definirMatrizButton);

        mainPanel.add(definirPanel, BorderLayout.NORTH);

        // Panel de botones de criterios
        JPanel botonesPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        botonesPanel.setBackground(new Color(230, 240, 255));
        botonesPanel.setBorder(BorderFactory.createTitledBorder("Criterios de Decisión"));

        calcularMaximinButton = new JButton("Maximin (Wald)");
        calcularMaximaxButton = new JButton("Maximax");
        calcularHurwiczButton = new JButton("Hurwicz");
        calcularLaplaceButton = new JButton("Laplace");
        calcularMinimaxRegretButton = new JButton("Minimax Regret");
        calcularValorEsperadoButton = new JButton("Valor Esperado");

        botonesPanel.add(calcularMaximinButton);
        botonesPanel.add(calcularMaximaxButton);
        botonesPanel.add(calcularHurwiczButton);
        botonesPanel.add(calcularLaplaceButton);
        botonesPanel.add(calcularMinimaxRegretButton);
        botonesPanel.add(calcularValorEsperadoButton);

        mainPanel.add(botonesPanel, BorderLayout.EAST);

        add(mainPanel);

        // Acciones
        definirMatrizButton.addActionListener(e -> definirMatriz());
        calcularMaximinButton.addActionListener(e -> calcularWald());
        calcularMaximaxButton.addActionListener(e -> calcularMaximax());
        calcularHurwiczButton.addActionListener(e -> calcularHurwicz());
        calcularLaplaceButton.addActionListener(e -> calcularLaplace());
        calcularMinimaxRegretButton.addActionListener(e -> calcularMinimaxRegret());
        calcularValorEsperadoButton.addActionListener(e -> calcularValorEsperado());

        // Inhabilitar botones hasta definir matriz
        habilitarBotones(false);
    }

    private void definirMatriz() {
        try {
            int filas = Integer.parseInt(filasField.getText());
            int columnas = Integer.parseInt(columnasField.getText());

            if (filas <= 0 || columnas <= 0) {
                JOptionPane.showMessageDialog(this, "Las dimensiones deben ser positivas.");
                return;
            }

            if (matrizPanel != null) {
                mainPanel.remove(matrizPanel);
            }

            matrizPanel = new MatrizPanel(filas, columnas);
            JScrollPane scrollPane = new JScrollPane(matrizPanel);
            scrollPane.setPreferredSize(new Dimension(500, 300));

            mainPanel.add(scrollPane, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
            habilitarBotones(true);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese números válidos para filas y columnas.");
        }
    }

    private void calcularWald() {
        try {
            double[][] matriz = matrizPanel.obtenerMatriz();
            double resultado = Criterios.wald(matriz);
            mostrarResultado("Criterio Maximin (Wald)", resultado);
        } catch (NumberFormatException ex) {
            mostrarError();
        }
    }

    private void calcularMaximax() {
        try {
            double[][] matriz = matrizPanel.obtenerMatriz();
            double resultado = Criterios.maximax(matriz);
            mostrarResultado("Criterio Maximax", resultado);
        } catch (NumberFormatException ex) {
            mostrarError();
        }
    }

    private void calcularHurwicz() {
        try {
            double alfa = solicitarAlfa();
            double[][] matriz = matrizPanel.obtenerMatriz();
            double resultado = Criterios.hurwicz(matriz, alfa);
            mostrarResultado("Criterio Hurwicz (α = " + alfa + ")", resultado);
        } catch (NumberFormatException ex) {
            mostrarError();
        }
    }

    private void calcularLaplace() {
        try {
            double[][] matriz = matrizPanel.obtenerMatriz();
            double resultado = Criterios.laplace(matriz);
            mostrarResultado("Criterio Laplace", resultado);
        } catch (NumberFormatException ex) {
            mostrarError();
        }
    }

    private void calcularMinimaxRegret() {
        try {
            double[][] matriz = matrizPanel.obtenerMatriz();
            double resultado = Criterios.minimaxRegret(matriz);
            mostrarResultado("Criterio Minimax Regret", resultado);
        } catch (NumberFormatException ex) {
            mostrarError();
        }
    }

    private void calcularValorEsperado() {
        try {
            double[][] matriz = matrizPanel.obtenerMatriz();
            int columnas = matriz[0].length;
            double[] probabilidades = solicitarProbabilidades(columnas);
            double resultado = Criterios.valorEsperado(matriz, probabilidades);
            mostrarResultado("Valor Esperado", resultado);
        } catch (NumberFormatException ex) {
            mostrarError();
        }
    }

    private double solicitarAlfa() throws NumberFormatException {
        String entrada = JOptionPane.showInputDialog(this, "Ingrese valor de α (entre 0 y 1):", "0.5");
        if (entrada == null) {
            throw new NumberFormatException();
        }
        entrada = entrada.replace(",", ".");
        double alfa = Double.parseDouble(entrada);
        if (alfa < 0 || alfa > 1) {
            throw new NumberFormatException();
        }
        return alfa;
    }

    private double[] solicitarProbabilidades(int cantidad) throws NumberFormatException {
        double[] probabilidades = new double[cantidad];
        for (int i = 0; i < cantidad; i++) {
            String entrada = JOptionPane.showInputDialog(this, "Probabilidad para estado " + (i + 1) + ":", "0.5");
            if (entrada == null) {
                throw new NumberFormatException();
            }
            entrada = entrada.replace(",", ".");
            probabilidades[i] = Double.parseDouble(entrada);
            if (probabilidades[i] < 0 || probabilidades[i] > 1) {
                throw new NumberFormatException();
            }
        }
        return probabilidades;
    }

    private void mostrarResultado(String criterio, double resultado) {
        JOptionPane.showMessageDialog(this, criterio + ": " + resultado);
    }

    private void mostrarError() {
        JOptionPane.showMessageDialog(this, "Error: Verifique los valores ingresados.");
    }

    private void habilitarBotones(boolean habilitar) {
        calcularMaximinButton.setEnabled(habilitar);
        calcularMaximaxButton.setEnabled(habilitar);
        calcularHurwiczButton.setEnabled(habilitar);
        calcularLaplaceButton.setEnabled(habilitar);
        calcularMinimaxRegretButton.setEnabled(habilitar);
        calcularValorEsperadoButton.setEnabled(habilitar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
    }
}
