package Actividades;

public class Intercambio {
     int x, y; // O(1) - Declaración de variables

    public Intercambio(int x, int y) { // O(1) - Constructor
        this.x = x; // O(1) - Asignación
        this.y = y; // O(1) - Asignación
    }

    void intercambia() { // O(1) - Método de intercambio
        int aux = x; // O(1) - Asignación
        x = y; // O(1) - Asignación
        y = aux; // O(1) - Asignación
    }
}