package Actividades;


public class MergeSort {  
   // Método principal que ordena el arreglo
   public static void mergeSort(int[] array) {
       if (array.length < 2) {
           return; // Un solo elemento ya está ordenado
       }
       // Encontrar el punto medio
       int mid = array.length / 2;  
       // Dividir el arreglo en dos subarreglos
       int[] left = new int[mid];
       int[] right = new int[array.length - mid];
       // Copiar los elementos a los subarreglos
       System.arraycopy(array, 0, left, 0, mid);
       System.arraycopy(array, mid, right, 0, array.length - mid);
       // Ordenar recursivamente los dos subarreglos
       mergeSort(left);
       mergeSort(right);  
       // Fusionar los dos subarreglos ordenados
       merge(array, left, right);
   }
   // Método para fusionar dos subarreglos ordenados
   public static void merge(int[] array, int[] left, int[] right) {
       int i = 0, j = 0, k = 0;
       // Fusionar los elementos de los dos subarreglos en el arreglo original
       while (i < left.length && j < right.length) {
           if (left[i] <= right[j]) {
               array[k++] = left[i++];
           } else {
               array[k++] = right[j++];
           }
       }
       // Copiar los elementos restantes del subarreglo izquierdo
       while (i < left.length) {
           array[k++] = left[i++];
       }  
       // Copiar los elementos restantes del subarreglo derecho
       while (j < right.length) {
           array[k++] = right[j++];
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
       mergeSort(array);
       System.out.println("Arreglo ordenado:");
       printArray(array);
   }
}