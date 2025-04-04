package Actividades;

public class Maximo {
    public static int max(int x, int y) { // O(1) - Función para obtener el máximo
        int result; // O(1) - Declaración de variable
        if (x >= y) { // O(1) - Comparación
            result = x; // O(1) - Asignación
        } else {
            result = y; // O(1) - Asignación
        }
        return result; // O(1) - Retorno del resultado
    }
}