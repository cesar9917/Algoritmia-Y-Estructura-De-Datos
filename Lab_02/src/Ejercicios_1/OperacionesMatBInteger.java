package Ejercicios_1;

//implementacion de la interfaz Operable en la clase OperacionesMatBInteger
public class OperacionesMatBInteger implements Operable<Integer>{
	
	//sobreescritura de los metodos
	@Override
	public Integer sumar(Integer operando1, Integer operando2) {
		return operando1 + operando2;
	}
	@Override
	public Integer resta(Integer operando1, Integer operando2) {
		return operando1 - operando2;
	}
	@Override
	public Integer producto(Integer operando1, Integer operando2) {
		return operando1 * operando2;
	}
	@Override
	public Integer division(Integer operando1, Integer operando2) {
		return operando1 / operando2;
	}
}
