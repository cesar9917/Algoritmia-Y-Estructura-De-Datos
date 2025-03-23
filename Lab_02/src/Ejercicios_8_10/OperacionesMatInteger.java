package Ejercicios_8_10;

public class OperacionesMatInteger implements Operable<Integer> {
    @Override
    public Integer suma(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer resta(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer producto(Integer a, Integer b) {
        return a * b;
    }

    @Override
    public Integer division(Integer a, Integer b) {
        if (b == 0) throw new ArithmeticException("No se puede dividir por cero.");
        return a / b;
    }

    @Override
    public Integer potencia(Integer base, Integer exponente) {
        return (int) Math.pow(base, exponente);
    }

    @Override
    public Integer raizCuadrada(Integer a) {
        return (int) Math.sqrt(a);
    }

    @Override
    public Integer raizCubica(Integer a) {
        return (int) Math.cbrt(a);
    }
}
