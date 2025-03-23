package Ejercicios_1;

//interfaz o contrado de Operable
public interface Operable<N extends Number> {
	public N sumar(N operando1, N operando2);
	public N resta(N operando1, N operando2);
	public N producto(N operando1, N operando2);
	public N division(N operando1, N operando2);
}
