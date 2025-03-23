package Ejercicios_8_10;

public interface Operable<T extends Number> {
    T suma(T a, T b);
    T resta(T a, T b);
    T producto(T a, T b);
    T division(T a, T b);
    T potencia(T base, T exponente);
    T raizCuadrada(T a);
    T raizCubica(T a);
}
