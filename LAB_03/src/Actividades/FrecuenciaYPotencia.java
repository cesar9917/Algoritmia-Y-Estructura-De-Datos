package Actividades;

import java.util.HashMap;
import java.util.Map;

public class FrecuenciaYPotencia {
    
    // Función para encontrar el número más frecuente
    public static int numeroMasFrecuente(int[] arreglo) {
        // Complejidad: O(n) - Recorremos todo el arreglo
        Map<Integer, Integer> frecuencias = new HashMap<>();
        
        // Complejidad: O(n) - Recorremos el arreglo para contar frecuencias
        for (int num : arreglo) {
            frecuencias.put(num, frecuencias.getOrDefault(num, 0) + 1);
        }

        // Complejidad: O(n) - Recorremos el diccionario para encontrar el número más frecuente
        int numeroFrecuente = arreglo[0];
        int maxFrecuencia = 0;

        for (Map.Entry<Integer, Integer> entry : frecuencias.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                numeroFrecuente = entry.getKey();
                maxFrecuencia = entry.getValue();
            }
        }

        // Complejidad: O(1) - Retorna el resultado
        return numeroFrecuente;
    }

    // Función para calcular potencia rápida
    public static int potenciaRapida(int x, int y) {
        // Complejidad: O(log y) - La función recursiva reduce el exponente a la mitad en cada paso
        if (y == 0) {
            // Complejidad: O(1) - Caso base
            return 1;
        } else if (y % 2 == 0) {
            // Complejidad: O(log y) - Caso de exponente par, se llama a la recursión
            int mitad = potenciaRapida(x, y / 2);
            return mitad * mitad;
        } else {
            // Complejidad: O(log y) - Caso de exponente impar, se llama a la recursión
            return x * potenciaRapida(x, y - 1);
        }
    }

    // Función principal para probar el código
    public static void main(String[] args) {
        int[] arreglo = {1, 3, 2, 3, 4, 3, 2, 5, 3};
        System.out.println("Número más frecuente: " + numeroMasFrecuente(arreglo));

        int x = 2, y = 10;
        System.out.println("Potencia rápida: " + potenciaRapida(x, y));
    }
}

