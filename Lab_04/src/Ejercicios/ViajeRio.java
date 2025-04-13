package Ejercicios;

public class ViajeRio {

    /**
     * Calcula la matriz de costos mínimos entre embarcaderos.
     * Usa programación dinámica basada en una matriz de tarifas T,
     * que representa los costos de ir directamente de un embarcadero a otro más adelante en el río.
     *
     * @param T Matriz triangular superior de tarifas directas entre embarcaderos.
     * @return Matriz C con los costos mínimos entre todos los pares de embarcaderos.
     */
    public static int[][] calcularCostosMinimos(int[][] T) {
        int n = T.length;
        int[][] C = new int[n][n];

        // Inicializar la matriz C:
        // - C[i][i] = 0 (costo de ir al mismo embarcadero)
        // - C[i][j] = T[i][j] si hay ruta directa
        // - C[i][j] = ∞ si no hay ruta directa
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    C[i][j] = 0;
                } else if (T[i][j] != 0) {
                    C[i][j] = T[i][j];
                } else {
                    C[i][j] = Integer.MAX_VALUE; // Representa "infinito" o sin ruta
                }
            }
        }

        // Programación dinámica para encontrar el costo mínimo
        // Se evalúan caminos indirectos entre embarcaderos (i → k → j)
        for (int longitud = 2; longitud < n; longitud++) {
            for (int i = 0; i < n - longitud; i++) {
                int j = i + longitud;
                for (int k = i + 1; k < j; k++) {
                    // Si hay camino de i a k y de k a j
                    if (T[i][k] != 0 && C[k][j] != Integer.MAX_VALUE) {
                        // Se actualiza C[i][j] si se encuentra un camino más barato
                        C[i][j] = Math.min(C[i][j], T[i][k] + C[k][j]);
                    }
                }
            }
        }

        return C;
    }

    /**
     * Imprime una matriz de forma legible.
     * Reemplaza valores infinitos por el símbolo ∞.
     *
     * @param matriz Matriz a imprimir.
     */
    public static void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int valor : fila) {
                if (valor == Integer.MAX_VALUE) {
                    System.out.print("∞ ");
                } else {
                    System.out.print(valor + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Método principal para probar el algoritmo.
     * Define una matriz de tarifas y muestra los costos mínimos.
     */
    public static void main(String[] args) {
        // Matriz de tarifas T:
        // T[i][j] representa el costo directo de i a j.
        // 0 significa que no hay ruta directa.
        int[][] T = {
            {0, 2, 9, 0},
            {0, 0, 3, 8},
            {0, 0, 0, 4},
            {0, 0, 0, 0}
        };

        // Calcular los costos mínimos
        int[][] C = calcularCostosMinimos(T);

        // Mostrar el resultado
        System.out.println("Matriz de costos mínimos C:");
        imprimirMatriz(C);
    }
}

