package Ejercicios_8_10;

public class OperacionesMatDouble implements Operable<Double> {
    @Override
    public Double suma(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double resta(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double producto(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double division(Double a, Double b) {
        if (b == 0) throw new ArithmeticException("No se puede dividir por cero.");
        return a / b;
    }

    @Override
    public Double potencia(Double base, Double exponente) {
        return Math.pow(base, exponente);
    }

    @Override
    public Double raizCuadrada(Double a) {
        return Math.sqrt(a);
    }

    @Override
    public Double raizCubica(Double a) {
        return Math.cbrt(a);
    }
}
