package busquedaBinaria;

public class busquedaBinaria {

    // Función binaria recursiva para encontrar el índice de un elemento
    int busqueda(int arr[], int lo, int hi, int x) {
        // Verificar si el intervalo es válido
        if (lo <= hi) {
            int mid = lo + (hi - lo) / 2; // Calcular el punto medio

            // Si encontramos el elemento
            if (arr[mid] == x)
                return mid;

            // Si el valor en el medio es mayor que x, buscamos en la mitad izquierda
            if (arr[mid] > x)
                return busqueda(arr, lo, mid - 1, x);

            // Si el valor en el medio es menor que x, buscamos en la mitad derecha
            return busqueda(arr, mid + 1, hi, x);
        }

        // Si no encontramos el elemento
        return -1;
    }

    public static void main(String[] args) {
        busquedaBinaria ob = new busquedaBinaria();
        int arr[] = {1, 2, 3, 4, 5};  // Arreglo ordenado
        int n = arr.length;           // Longitud del arreglo
        int x = 4;                    // Elemento a buscar
        int position = ob.busqueda(arr, 0, n - 1, x);  // Llamada a la búsqueda binaria

        if (position == -1)
            System.out.println("Elemento no existe");  // Si no se encuentra el elemento
        else
            System.out.println("Elemento encontrado en la posicion: " + (position+1));  // Si se encuentra, mostrar el índice
    }
}
