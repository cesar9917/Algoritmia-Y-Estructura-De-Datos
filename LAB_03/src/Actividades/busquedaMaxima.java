package Actividades;

public class busquedaMaxima {
    // O(n) - Búsqueda del máximo en un vector de enteros
    public static int BM(int[] v, int n) {
        int m = v[0]; // O(1) - Asignación inicial
        
        for (int i = 1; i < n; i++) { // O(n) - Iteración a través del array
            if (v[i] > m) { // O(1) - Comparación en cada iteración
                m = v[i]; // O(1) - Asignación si se encuentra un nuevo máximo
            }
        }
        return m; // O(1) - Retorno del máximo encontrado
    }
}