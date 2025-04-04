package Actividades;

public class MergeSortInPlace {  
   // Método principal que ordena el arreglo
   public static void mergeSort(int[] array, int left, int right) {
       if (left < right) {
           int mid = left + (right - left) / 2;
           // Ordenar recursivamente las dos mitades
           mergeSort(array, left, mid);
           mergeSort(array, mid + 1, right);
           // Fusionar las dos mitades
           merge(array, left, mid, right);
       }
   }  
   // Método para fusionar dos subarreglos en el lugar
   public static void merge(int[] array, int left, int mid, int right) {
       int i = left;
       int j = mid + 1;
       // Recorremos ambas mitades del arreglo
       while (i <= mid && j <= right) {
           if (array[i] <= array[j]) {
               i++;
           } else {
               // Mover el elemento array[j] hacia la izquierda
               int temp = array[j];
               for (int k = j; k > i; k--) {
                   array[k] = array[k - 1];
               }
               array[i] = temp;
               // Avanzamos los índices
               i++;
               mid++;
               j++;
           }
       }
   }
   // Método para imprimir el arreglo
   public static void printArray(int[] array) {
       for (int i : array) {
           System.out.print(i + " ");
       }
       System.out.println();
   }
   // Método principal para probar el algoritmo
   public static void main(String[] args) {
       int[] array = {38, 27, 43, 3, 9, 82, 10};
       System.out.println("Arreglo original:");
       printArray(array);
       mergeSort(array, 0, array.length - 1);  
       System.out.println("Arreglo ordenado:");
       printArray(array);
   }
}