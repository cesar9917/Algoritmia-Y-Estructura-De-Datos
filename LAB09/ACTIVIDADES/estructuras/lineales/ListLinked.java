package estructuras.lineales;

public class ListLinked<T> {
	public static class Nodo<T> {
	    public T dato;      
	    public Nodo<T> siguiente;

	    Nodo(T dato) {
	        this.dato = dato;
	        this.siguiente = null;
	    }
	}

    private Nodo<T> cabeza;

    public ListLinked() {
        cabeza = null;
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void insertarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    public void insertarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public boolean eliminar(T dato) {
        if (estaVacia()) return false;

        if (cabeza.dato.equals(dato)) {
            cabeza = cabeza.siguiente;
            return true;
        }

        Nodo<T> actual = cabeza;
        while (actual.siguiente != null && !actual.siguiente.dato.equals(dato)) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            return true;
        }

        return false;
    }

    public T buscar(T dato) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public void mostrar() {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = cabeza;
        while (actual != null) {
            sb.append(actual.dato).append(" -> ");
            actual = actual.siguiente;
        }
        sb.append("null");
        return sb.toString();
    }
}


