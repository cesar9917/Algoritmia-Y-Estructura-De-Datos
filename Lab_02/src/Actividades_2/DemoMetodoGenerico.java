package Actividades_2;

public class DemoMetodoGenerico {
	//metodo para comparar dos listas genericas y retorne true o false
	public static <T extends Comparable<T>> boolean igualArrays(T[] x, T[] y) {
		//pregunta si la cantidad de elementos son diferentes
		if (x.length != y.length) {
			return false;
		}
		//recorre la lista de "x", mientras va comparando cada elemento en "y"
		for (int i = 0; i < x.length; i++) {
			if (!x[i].equals(y[i])) {
				return false;
			}
		}
		//retorna si true si los elementos son iguales
		return true;
	}
	
	public static void main(String[] args) {
		//creamos arreglos de tipo "Integer" para compararlos
		Integer nums1[] = {1,2,3,4,5};
		Integer nums2[] = {1,2,3,4,5};
		Integer nums3[] = {6,7,8,9,10};
		Integer nums4[] = {6,7,8,9,4};
		
		//imprimimos los resultados si es true o false
		System.out.println(igualArrays(nums1, nums2));
		System.out.println(igualArrays(nums3, nums4));
		
		//imprimimos los resultados para los de tipo "Integer"
		Double nums5[] = {1.5, 5.6, 8.9, 4.5};
		Double nums6[] = {1.5, 5.6, 8.9, 4.5};
		Double nums7[] = {6.7, 8.3, 3.4, 8.9};
		Double nums8[] = {8.9, 3.4, 2.6, 2.3};
		
		//imprimimos los resultados para los de tipo "Double"
		System.out.println(igualArrays(nums5, nums6));
		System.out.println(igualArrays(nums7, nums8));
	}
}


