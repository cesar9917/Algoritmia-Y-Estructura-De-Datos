package Actividades;

import java.util.ArrayList;
import java.util.List;

public class GestorDeTareas<T> {
	private Nodo<T> cabeza;
	private List<T> tareasCompletadas = new ArrayList<>();
	
	public void agregarTarea(T tarea) {
		Nodo<T> nuevo = new Nodo<>(tarea);
		if (cabeza == null) {
			cabeza = nuevo;
		} else {
			Nodo<T> aux = cabeza;
			while (aux.siguiente != null) {
				aux = aux.siguiente;
			}
			aux.siguiente = nuevo;
		}
	}
	
	public boolean eliminarTarea(T tarea) {
		Nodo<T> actual = cabeza;
		Nodo<T> anterior = null;

		while (actual != null) {
			if (actual.dato.equals(tarea)) {
				if (anterior == null) {
					cabeza = actual.siguiente;
				} else {
					anterior.siguiente = actual.siguiente;
				}
				tareasCompletadas.add(tarea);
				return true;
			}
			anterior = actual;
			actual = actual.siguiente;
		}
		return false;
	}

	
	public boolean contieneTarea(T tarea) {
		if (cabeza == null) {
			return false;
		}
		Nodo<T> aux = cabeza;
	    while (aux != null) {
	        if (aux.dato.equals(tarea)) {
	            return true;
	        }
	        aux = aux.siguiente;
	    }
	    return false;
	}
	
	public void imprimirTareas() {
		Nodo<T> aux = cabeza;
		if(aux == null) {
			System.out.println("No existen tareas en nuestra lista enlazada.");
		}
		while (aux != null) {
			System.out.println(aux.dato.toString());
			aux = aux.siguiente;
		}
	}
	
	public int contarTarea() {
		int contador = 0;
		Nodo<T> aux = cabeza;
		if (aux == null) {
			return contador;
		}
		while (aux != null) {
			contador = contador + 1;
			aux = aux.siguiente;
		}
		return contador;
	}
	
	public T obtenerTareaMasPrioritaria() {
		Nodo<T> aux = cabeza;
		if (aux == null) {
			return null;
		}
		T mayor = aux.dato;
		aux = aux.siguiente;
		while (aux != null) {
			Tarea actual = (Tarea) aux.dato;
			Tarea tareaMayor = (Tarea) mayor;
			if (actual.getPrioridad() > tareaMayor.getPrioridad()) {
				mayor = aux.dato;
			}
			aux = aux.siguiente;
		}
		return mayor;
	}
	
	public void invertirTareas() {
	    Nodo<T> actual = cabeza;
	    Nodo<T> anterior = null;
	    Nodo<T> siguiente = null;

	    if (actual == null) {
	        System.out.println("La lista está vacia");
	        return;
	    }

	    while (actual != null) {
	        siguiente = actual.siguiente;   
	        actual.siguiente = anterior;    
	        anterior = actual;              
	        actual = siguiente;             
	    }
	    cabeza = anterior;
	}
	
	public void mostrarTareasCompletadas() {
		if (tareasCompletadas.isEmpty()) {
			System.out.println("No hay tareas completadas.");
		} else {
			System.out.println("Tareas completadas:");
			for (T tarea : tareasCompletadas) {
				System.out.println(tarea.toString());
			}
		}
	}
}







