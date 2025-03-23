package Ejercicios_1;

public class Prueba {
	public static void main(String[] args) {
		
		//objeto operadorInteger para poder realizar pruebas con los metodos Integer
		OperacionesMatBInteger operadorInteger = new OperacionesMatBInteger();
		
		System.out.print("===Operaciones con Integer===\n");
		System.out.println(operadorInteger.sumar(6, 7));
		System.out.println(operadorInteger.resta(6, 7));
		System.out.println(operadorInteger.producto(6, 7));
		System.out.println(operadorInteger.division(6, 7));
		
		//objeto operadorDouble para poder realizar pruebas con los metodos Double
		OperacionesMatDouble operadorDouble = new OperacionesMatDouble();
		
		System.out.print("===Operaciones con Double===\n");
		System.out.println(operadorDouble.sumar(7.8, 9.5));
		System.out.println(operadorDouble.resta(10.9, 9.5));
		System.out.println(operadorDouble.producto(7.8, 9.5));
		System.out.println(operadorDouble.division(2.8, 1.4));
	}
}
