package Actividades_1;

//libreria para listas dinamicas
import java.util.ArrayList;

//libreria para interaccion con elementos de bolsa
import java.util.Iterator; 

public class Bolsa <T> implements Iterable <T> {
	
	//lista para almacenar los elementos de tipo T
	private ArrayList <T> lista = new ArrayList <T> (); 
	//limite maximo de elementos
	private int tope; 
	
	//constructor
	public Bolsa(int tope) {
		super();
		this.tope = tope;
	}
	
	//metodo para agregar un elemento a la bolsa
	public void add(T objeto) {
		//con size() se mide el arreglo
		if (lista.size() < tope) {
			lista.add(objeto);
		} else {
			//lanza la excepcion cuando llega al tope
			throw new RuntimeException("No caben mas");
		}
	}
	
	//metodo para retornar un iterator para recorrer la lista
	public Iterator <T> iterator(){
		return lista.iterator();
	}
}
