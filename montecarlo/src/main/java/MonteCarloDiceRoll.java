

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MonteCarloDiceRoll {

    public MonteCarloDiceRoll() {
    }

    public void metodoMonteCarlo() {
        long numeroDeSimulaciones = 100_000_000; // Número de veces que lanzaremos los dos dados
        Random random = new Random();

        // Usaremos un mapa para almacenar la frecuencia de cada suma.
        // Las sumas posibles van de 2 a 12.
        Map<Integer, Long> frecuenciaSumas = new HashMap<>();
        for (int i = 2; i <= 12; i++) {
            frecuenciaSumas.put(i, 0L); // Inicializar todos los contadores a cero
        }

        System.out.println("Iniciando la simulación de Monte Carlo para lanzamientos de dados con "
                + numeroDeSimulaciones + " lanzamientos...");

        for (long i = 0; i < numeroDeSimulaciones; i++) {
            // Lanzar el primer dado (número aleatorio entre 1 y 6)
            int die1 = random.nextInt(6) + 1; // random.nextInt(6) da 0-5, +1 para 1-6

            // Lanzar el segundo dado (número aleatorio entre 1 y 6)
            int die2 = random.nextInt(6) + 1;

            int sum = die1 + die2; // Calcular la suma de los dos dados

            // Incrementar el contador para esta suma
            frecuenciaSumas.put(sum, frecuenciaSumas.get(sum) + 1);
        }

        System.out.println("\nResultados de la Simulación:");
        System.out.println("---------------------------------");
        System.out.printf("%-10s %-15s %-15s%n", "Suma", "Frecuencia", "Probabilidad (%)");
        System.out.println("---------------------------------");

        // Calcular y mostrar las probabilidades
        for (int sum = 2; sum <= 12; sum++) {
            long count = frecuenciaSumas.get(sum);
            double probability = (double) count / numeroDeSimulaciones * 100;
            System.out.printf("%-10d %-15d %-15.4f%n", sum, count, probability);
        }
        System.out.println("---------------------------------");
    }
}
