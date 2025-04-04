package Actividades;

import java.util.HashMap;
import java.util.Map;

public class ModaFrecuencia {
    
    // O(n) - Encuentra el número más frecuente en un arreglo usando un diccionario (HashMap)
    public static int moda(int[] v) {
        Map<Integer, Integer> frecuencia = new HashMap<>(); // O(1) - Creación del HashMap
        int maxFrecuencia = 0; // O(1) - Inicialización de la máxima frecuencia
        int moda = v[0]; // O(1) - Inicialización de la moda
        
        for (int num : v) { // O(n) - Recorrido del arreglo
            int f = frecuencia.getOrDefault(num, 0) + 1; // O(1) - Obtener y actualizar frecuencia
            frecuencia.put(num, f); // O(1) - Inserción en HashMap
            
            if (f > maxFrecuencia) { // O(1) - Comparación con la máxima frecuencia
                maxFrecuencia = f; // O(1) - Actualización de la máxima frecuencia
                moda = num; // O(1) - Actualización de la moda
            }
        }
        return moda; // O(1) - Retorno del número con mayor frecuencia
    }

    public static void main(String[] args) {
        int[] v = {4, 2, 4, 3, 2, 4, 5, 2, 2}; // Ejemplo de arreglo
        System.out.println("La moda es: " + moda(v)); // Salida esperada: 2 o 4 (dependiendo de cuál aparezca primero)
    }
}
