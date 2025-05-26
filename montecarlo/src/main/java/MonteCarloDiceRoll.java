import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MonteCarloDiceRoll {

    public MonteCarloDiceRoll() {
    }

    public String metodoMonteCarlo() {
        long numeroDeSimulaciones = 100_000_000;
        Random random = new Random();

        Map<Integer, Long> frecuenciaSumas = new HashMap<>();
        for (int i = 2; i <= 12; i++) {
            frecuenciaSumas.put(i, 0L);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Iniciando la simulación de Monte Carlo para lanzamientos de dados con ")
                .append(numeroDeSimulaciones)
                .append(" lanzamientos...\n\n");

        for (long i = 0; i < numeroDeSimulaciones; i++) {
            int die1 = random.nextInt(6) + 1;
            int die2 = random.nextInt(6) + 1;
            int sum = die1 + die2;
            frecuenciaSumas.put(sum, frecuenciaSumas.get(sum) + 1);
        }

        sb.append("Resultados de la Simulación:\n");
        sb.append("---------------------------------\n");
        sb.append(String.format("%-10s %-15s %-15s%n", "Suma", "Frecuencia", "Probabilidad (%)"));
        sb.append("---------------------------------\n");

        for (int sum = 2; sum <= 12; sum++) {
            long count = frecuenciaSumas.get(sum);
            double probability = (double) count / numeroDeSimulaciones * 100;
            sb.append(String.format("%-10d %-15d %-15.4f%n", sum, count, probability));
        }
        sb.append("---------------------------------\n");

        return sb.toString();
    }
}
