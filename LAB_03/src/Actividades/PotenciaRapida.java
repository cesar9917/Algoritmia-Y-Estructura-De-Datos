package Actividades;

public class PotenciaRapida {
    public static double potencia(double x, int y) { // O(log y) - Función de exponenciación rápida
        double t; // O(1) - Declaración de variable
        
        if (y == 0) // O(1) - Caso base: cualquier número elevado a 0 es 1
            return 1.0; // O(1) - Retorno constante
        
        if (y % 2 == 1) // O(1) - Verificación de si el exponente es impar
            return x * potencia(x, y - 1); // T(y-1) - Llamada recursiva restando 1
        else {
            t = potencia(x, y / 2); // T(y/2) - Llamada recursiva dividiendo el exponente por 2
            return t * t; // O(1) - Multiplicación del resultado para obtener la potencia
        }
    }
}