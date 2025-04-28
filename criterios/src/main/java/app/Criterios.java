package app;

public class Criterios {

    public static double wald(double[][] matriz) {
        double mejorMinimo = Double.NEGATIVE_INFINITY;
        for (double[] fila : matriz) {
            double minimoFila = Double.POSITIVE_INFINITY;
            for (double valor : fila) {
                if (valor < minimoFila) {
                    minimoFila = valor;
                }
            }
            if (minimoFila > mejorMinimo) {
                mejorMinimo = minimoFila;
            }
        }
        return mejorMinimo;
    }

    public static double maximax(double[][] matriz) {
        double mejorMaximo = Double.NEGATIVE_INFINITY;
        for (double[] fila : matriz) {
            for (double valor : fila) {
                if (valor > mejorMaximo) {
                    mejorMaximo = valor;
                }
            }
        }
        return mejorMaximo;
    }

    public static double hurwicz(double[][] matriz, double alfa) {
        double mejorValor = Double.NEGATIVE_INFINITY;
        for (double[] fila : matriz) {
            double max = Double.NEGATIVE_INFINITY;
            double min = Double.POSITIVE_INFINITY;
            for (double valor : fila) {
                max = Math.max(max, valor);
                min = Math.min(min, valor);
            }
            double valorHurwicz = alfa * max + (1 - alfa) * min;
            if (valorHurwicz > mejorValor) {
                mejorValor = valorHurwicz;
            }
        }
        return mejorValor;
    }

    public static double laplace(double[][] matriz) {
        double mejorPromedio = Double.NEGATIVE_INFINITY;
        for (double[] fila : matriz) {
            double suma = 0;
            for (double valor : fila) {
                suma += valor;
            }
            double promedio = suma / fila.length;
            if (promedio > mejorPromedio) {
                mejorPromedio = promedio;
            }
        }
        return mejorPromedio;
    }

    public static double[][] matrizRegret(double[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        double[][] regret = new double[filas][columnas];

        // Encuentra el máximo de cada columna
        for (int j = 0; j < columnas; j++) {
            double maxCol = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < filas; i++) {
                maxCol = Math.max(maxCol, matriz[i][j]);
            }
            // Calcula regret para cada fila
            for (int i = 0; i < filas; i++) {
                regret[i][j] = maxCol - matriz[i][j];
            }
        }
        return regret;
    }

    public static double minimaxRegret(double[][] matriz) {
        double[][] regret = matrizRegret(matriz);
        double peorRegret = Double.POSITIVE_INFINITY;
        for (double[] fila : regret) {
            double maxFila = Double.NEGATIVE_INFINITY;
            for (double valor : fila) {
                if (valor > maxFila) {
                    maxFila = valor;
                }
            }
            if (maxFila < peorRegret) {
                peorRegret = maxFila;
            }
        }
        return peorRegret;
    }

    public static double valorEsperado(double[][] matriz, double[] probabilidades) {
        double mejorEsperado = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < matriz.length; i++) {
            double suma = 0;
            for (int j = 0; j < matriz[i].length; j++) {
                suma += matriz[i][j] * probabilidades[j];
            }
            if (suma > mejorEsperado) {
                mejorEsperado = suma;
            }
        }
        return mejorEsperado;
    }
}
