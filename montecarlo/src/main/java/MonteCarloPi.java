
import java.util.Random;

public class MonteCarloPi {
    public MonteCarloPi() {
    }

    public void metodoMonteCarlo() {
        long numeroDeSimulaciones = 100_000_000; // Número de puntos a "lanzar"
        long puntosDentroDelCirculo = 0;
        Random random = new Random();

        System.out.println("Iniciando la simulación de Monte Carlo para Pi con " + numeroDeSimulaciones + " puntos...");

        for (long i = 0; i < numeroDeSimulaciones; i++) {
            // Generar coordenadas x e y aleatorias entre 0 y 1
            // Imaginamos un cuadrado de 1x1 en el primer cuadrante (0,0) a (1,1)
            double x = random.nextDouble(); // Genera un número aleatorio entre 0.0 (inclusive) y 1.0 (exclusive)
            double y = random.nextDouble();

            // Calcular la distancia del punto al origen (0,0)
            // Usamos el teorema de Pitágoras: distancia^2 = x^2 + y^2
            double distanceSquared = (x * x) + (y * y);

            // Si el punto cae dentro de un círculo de radio 1 centrado en (0,0),
            // su distancia al origen será <= 1.
            // En este caso, estamos usando un cuarto de círculo con radio 1.
            if (distanceSquared <= 1) {
                puntosDentroDelCirculo++;
            }
        }

        // La proporción de puntos dentro del círculo con respecto al total
        // se aproxima a la proporción del área del cuarto de círculo con respecto al
        // área del cuadrado.
        // Área del cuarto de círculo = (Pi * r^2) / 4. Si r=1, es Pi/4.
        // Área del cuadrado = lado * lado. Si lado=1, es 1.
        // Entonces: (puntos_dentro_circulo / total_puntos) = (Pi/4) / 1
        // Pi = 4 * (puntos_dentro_circulo / total_puntos)
        double piEstimado = 4.0 * ((double) puntosDentroDelCirculo / numeroDeSimulaciones);

        System.out.println("Puntos dentro del círculo: " + puntosDentroDelCirculo);
        System.out.println("Total de puntos simulados: " + numeroDeSimulaciones);
        System.out.println("Valor estimado de Pi: " + piEstimado);
        System.out.println("Valor real de Pi (aproximado): " + Math.PI);
    }
}