package Ejercicios;

import java.util.Random;

public class KEsimoMenor {

    /**
     * Método principal que encuentra el k-ésimo elemento más pequeño del arreglo.
     * Utiliza la técnica de "divide y vencerás" basada en QuickSelect.
     *
     * @param arr Arreglo de enteros.
     * @param k   Posición del elemento más pequeño que se desea encontrar (1-indexado).
     * @return    El valor del k-ésimo elemento más pequeño.
     */
    public static int quickSelect(int[] arr, int k) {
        // Llamada al método auxiliar con índices de inicio y fin del arreglo
        // k-1 porque en programación los índices inician en 0
        return quickSelectHelper(arr, 0, arr.length - 1, k - 1);
    }

    /**
     * Método auxiliar recursivo que aplica la técnica de QuickSelect.
     *
     * @param arr   Arreglo de enteros.
     * @param left  Índice inicial del subarreglo.
     * @param right Índice final del subarreglo.
     * @param k     Índice (0-indexado) del elemento que se desea encontrar.
     * @return      El valor del k-ésimo elemento más pequeño.
     */
    private static int quickSelectHelper(int[] arr, int left, int right, int k) {
        // Si el subarreglo tiene un solo elemento, se devuelve directamente
        if (left == right) {
            return arr[left];
        }

        // Elegir un índice pivote aleatorio y particionar el arreglo alrededor de él
        int pivotIndex = partition(arr, left, right);

        // Comparar la posición del pivote con k
        if (k == pivotIndex) {
            // Si el pivote está en la posición k, ya se encontró el valor deseado
            return arr[k];
        } else if (k < pivotIndex) {
            // Si k está a la izquierda del pivote, buscar en la mitad izquierda
            return quickSelectHelper(arr, left, pivotIndex - 1, k);
        } else {
            // Si k está a la derecha del pivote, buscar en la mitad derecha
            return quickSelectHelper(arr, pivotIndex + 1, right, k);
        }
    }

    /**
     * Función que particiona el arreglo en torno a un pivote.
     * Los elementos menores al pivote quedan a la izquierda, los mayores a la derecha.
     *
     * @param arr   Arreglo a particionar.
     * @param left  Índice inicial del subarreglo.
     * @param right Índice final del subarreglo.
     * @return      La posición final del pivote después de la partición.
     */
    private static int partition(int[] arr, int left, int right) {
        // Elegir un índice aleatorio como pivote entre left y right
        int pivotIndex = left + new Random().nextInt(right - left + 1);
        int pivotValue = arr[pivotIndex];

        // Mover el pivote temporalmente al final del subarreglo
        swap(arr, pivotIndex, right);

        int storeIndex = left;

        // Mover todos los elementos menores que el pivote hacia la izquierda
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }

        // Colocar el pivote en su posición final
        swap(arr, storeIndex, right);

        return storeIndex; // Índice donde quedó el pivote
    }

    /**
     * Función que intercambia dos elementos del arreglo.
     *
     * @param arr Arreglo donde se realizará el intercambio.
     * @param i   Índice del primer elemento.
     * @param j   Índice del segundo elemento.
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Método main para probar el algoritmo con distintos casos.
     */
    public static void main(String[] args) {
        int[] arr1 = {4, 2, 7, 10, 4, 17};
        int[] arr2 = {4, 2, 7, 10, 4, 1, 6};
        int[] arr3 = {4, 2, 7, 1, 4, 6};
        int[] arr4 = {9, 2, 7, 1, 7};

        System.out.println(quickSelect(arr1, 3)); // Salida esperada: 4
        System.out.println(quickSelect(arr2, 5)); // Salida esperada: 6
        System.out.println(quickSelect(arr3, 1)); // Salida esperada: 1
        System.out.println(quickSelect(arr4, 4)); // Salida esperada: 7
    }
}
