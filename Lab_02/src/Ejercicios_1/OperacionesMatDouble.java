package Ejercicios_1;

//implementacion de la interfaz Operable en la clase OperacionesMatDouble
public class OperacionesMatDouble implements Operable<Double>{
	
	//sobreescritura de los metodos
	@Override
	public Double sumar(Double operando1, Double operando2) {
		return operando1 + operando2;
	}
	@Override
	public Double resta(Double operando1, Double operando2) {
		return operando1 - operando2;
	}
	@Override
	public Double producto(Double operando1, Double operando2) {
		return operando1 * operando2;
	}
	@Override
	public Double division(Double operando1, Double operando2) {
		return operando1 / operando2;
	}
}