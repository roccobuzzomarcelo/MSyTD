import java.util.Random;

public class MonteCarloPi {
    public MonteCarloPi() {
    }

    public String metodoMonteCarlo() {
        long numeroDeSimulaciones = 100_000_000;
        long puntosDentroDelCirculo = 0;
        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        sb.append("Iniciando la simulación de Monte Carlo para Pi con ")
          .append(numeroDeSimulaciones)
          .append(" puntos...\n\n");

        for (long i = 0; i < numeroDeSimulaciones; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            double distanceSquared = (x * x) + (y * y);

            if (distanceSquared <= 1) {
                puntosDentroDelCirculo++;
            }
        }

        double piEstimado = 4.0 * ((double) puntosDentroDelCirculo / numeroDeSimulaciones);

        sb.append("Puntos dentro del círculo: ").append(puntosDentroDelCirculo).append("\n");
        sb.append("Total de puntos simulados: ").append(numeroDeSimulaciones).append("\n");
        sb.append("Valor estimado de Pi: ").append(piEstimado).append("\n");
        sb.append("Valor real de Pi (aproximado): ").append(Math.PI).append("\n");

        return sb.toString();
    }
}
