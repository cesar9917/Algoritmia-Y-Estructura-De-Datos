package busquedaBinaria;

public class busquedaBinariaIterativa {

    // Método para realizar búsqueda binaria iterativa
	int busqueda(int arr[], int x) {
        int izquierda = 0, derecha = arr.length - 1;

        // Mientras el índice izquierdo sea menor o igual al índice derecho
        while (izquierda <= derecha) {
            // Encontrar el punto medio
            int medio = izquierda + (derecha - izquierda) / 2;

            // Si encontramos el elemento en el punto medio, retornamos el índice
            if (arr[medio] == x)
                return medio;

            // Si el elemento a buscar es mayor que el valor medio, ajustamos el índice izquierdo
            if (arr[medio] < x)
                izquierda = medio + 1;
            // Si el elemento a buscar es menor que el valor medio, ajustamos el índice derecho
            else
                derecha = medio - 1;
        }

        // Si el elemento no se encuentra en el arreglo, retornamos -1
        return -1;
    }

    // Método main para probar la búsqueda binaria
    public static void main(String[] args) {
        busquedaBinariaIterativa ob = new busquedaBinariaIterativa();
        int arr[] = {1, 2, 3, 4, 5};  // Arreglo ordenado
        int x = 4;  // Elemento que queremos buscar

        // Llamada al método de búsqueda binaria
        int position = ob.busqueda(arr, x);

        // Verificamos si encontramos el elemento o no
        if (position == -1)
            System.out.println("Elemento no encontrado");
        else
            System.out.println("Elemento buscado en la posicion: " + (position+1));  // Muestra el índice donde se encuentra el elemento
    }
}

