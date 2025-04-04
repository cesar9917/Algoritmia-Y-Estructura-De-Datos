package Actividades;

public class Ordenar {
    public static void ordenar(int[] v, int tamaño) { // O(n^2) - Ordenamiento burbuja
        for (int i = 0; i < tamaño - 1; i++) { // O(n) - Iteración externa
            for (int j = 0; j < tamaño - 1; j++) { // O(n) - Iteración interna
                if (v[j] > v[j + 1]) { // O(1) - Comparación
                    int aux = v[j]; // O(1) - Asignación temporal
                    v[j] = v[j + 1]; // O(1) - Intercambio
                    v[j + 1] = aux; // O(1) - Intercambio
                }
            }
        }
    }
}