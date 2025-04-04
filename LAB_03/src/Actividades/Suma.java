package Actividades;

public class Suma {
    public static int suma(int[] v, int tamaño) { // O(n) - Función para sumar elementos de un vector
        int result = 0; // O(1) - Inicialización de la suma
        for (int i = 0; i < tamaño; i++) { // O(n) - Iteración sobre el vector
            result += v[i]; // O(1) - Suma de elementos
        }
        return result; // O(1) - Retorno del resultado
    }
}