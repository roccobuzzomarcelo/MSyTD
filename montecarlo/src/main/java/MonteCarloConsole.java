import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MonteCarloConsole extends JFrame {
    private JTextArea outputArea;
    private final MonteCarloDiceRoll monteCarloDiceRoll;
    private final MonteCarloPi monteCarloPi;

    public MonteCarloConsole() {
        super("Consola Monte Carlo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1024, 758);
        setLocationRelativeTo(null);

        monteCarloDiceRoll = new MonteCarloDiceRoll();
        monteCarloPi = new MonteCarloPi();

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(Color.black);
        outputArea.setForeground(new Color(0, 255, 0)); // Verde consola
        outputArea.setFont(new Font("Courier New", Font.PLAIN, 16));
        outputArea.setCaretColor(new Color(0, 255, 0)); // Cursor verde

        JScrollPane scrollPane = new JScrollPane(outputArea);

        JButton diceButton = createStyledButton("Ejecutar MonteCarlo Dados");
        JButton piButton = createStyledButton("Ejecutar MonteCarlo Pi");
        JButton clearButton = createStyledButton("Limpiar Consola");

        diceButton.addActionListener(e -> runMonteCarloDice());
        piButton.addActionListener(e -> runMonteCarloPi());
        clearButton.addActionListener(e -> outputArea.setText(""));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.black);
        buttonPanel.add(diceButton);
        buttonPanel.add(piButton);
        buttonPanel.add(clearButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(30, 30, 30)); // Gris oscuro
        button.setForeground(new Color(0, 255, 0)); // Verde consola
        button.setFont(new Font("Courier New", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 1));
        // Cambiar el cursor al pasar sobre el botón
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void runMonteCarloDice() {
        outputArea.append("Ejecutando Monte Carlo Dados...\n");
        String result = monteCarloDiceRoll.metodoMonteCarlo();
        outputArea.append(result + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void runMonteCarloPi() {
        outputArea.append("Ejecutando Monte Carlo Pi...\n");
        String result = monteCarloPi.metodoMonteCarlo();
        outputArea.append(result + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MonteCarloConsole console = new MonteCarloConsole();
            console.setVisible(true);
        });
    }
}
