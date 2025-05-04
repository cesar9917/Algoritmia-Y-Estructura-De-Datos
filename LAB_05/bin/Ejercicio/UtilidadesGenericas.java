package Actividades;

import java.util.ArrayList;
import java.util.List;

public class UtilidadesGenericas {

    // EJERCICIO 01: Buscar un elemento genérico en una lista
    public static <T> boolean buscarElemento(List<T> lista, T valor) {
        for (T elemento : lista) {
            if (elemento.equals(valor)) {
                return true;
            }
        }
        return false;
    }

    // EJERCICIO 02: Invertir una lista genérica
    public static <T> List<T> invertirLista(List<T> lista) {
        List<T> invertida = new ArrayList<>();
        for (int i = lista.size() - 1; i >= 0; i--) {
            invertida.add(lista.get(i));
        }
        return invertida;
    }

    // EJERCICIO 03: Insertar un nodo al final (usando Nodo<T>)
    public static <T> Nodo<T> insertarAlFinal(Nodo<T> cabeza, T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (cabeza == null) {
            return nuevo;
        }

        Nodo<T> actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevo;
        return cabeza;
    }

    // EJERCICIO 04: Contar los nodos
    public static <T> int contarNodos(Nodo<T> cabeza) {
        int contador = 0;
        Nodo<T> actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    // EJERCICIO 05: Comparar dos listas enlazadas
    public static <T> boolean compararListas(Nodo<T> cabeza1, Nodo<T> cabeza2) {
        Nodo<T> actual1 = cabeza1;
        Nodo<T> actual2 = cabeza2;

        while (actual1 != null && actual2 != null) {
            if (!actual1.dato.equals(actual2.dato)) {
                return false;
            }
            actual1 = actual1.siguiente;
            actual2 = actual2.siguiente;
        }

        return actual1 == null && actual2 == null;
    }

    // EJERCICIO 06: Concatenar dos listas enlazadas
    public static <T> Nodo<T> concatenarListas(Nodo<T> cabeza1, Nodo<T> cabeza2) {
        if (cabeza1 == null) return cabeza2;
        if (cabeza2 == null) return cabeza1;

        Nodo<T> actual = cabeza1;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = cabeza2;

        return cabeza1;
    }
}
