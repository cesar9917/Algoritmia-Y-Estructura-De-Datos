package Actividades;

public class ConteoDuplicados {
    // O(n^2) - Conteo de elementos duplicados en un vector
    public static int ConteoI(int[] v, int n) {
        int conteo = 0; // O(1) - Inicialización de la variable de conteo
        
        for (int i = 0; i < n - 1; i++) { // O(n) - Bucle externo
            for (int j = i + 1; j < n; j++) { // O(n) - Bucle interno
                if (v[i] == v[j]) { // O(1) - Comparación en cada iteración
                    conteo++; // O(1) - Incremento si hay coincidencia
                }
            }
        }
        return conteo; // O(1) - Retorno del total de duplicados encontrados
    }
}