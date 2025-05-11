package ejercicio_3;

import actividad_3.PriorityQueue;
import ejercicio_1.StackLink; 
import actividad_1.ExceptionIsEmpty;

import java.util.ArrayList;
public class PriorityQueueLinked<T> implements PriorityQueue<T> {
 private ArrayList<QueueLink<T>> colas;
 private int nivelesDePrioridad;
 public PriorityQueueLinked(int nivelesDePrioridad) {
 if (nivelesDePrioridad <= 0) {
 throw new IllegalArgumentException("La cantidad de prioridades debe ser
mayor a cero.");
 }
 this.nivelesDePrioridad = nivelesDePrioridad;
 colas = new ArrayList<>();
 for (int i = 0; i < nivelesDePrioridad; i++) {
 colas.add(new QueueLink<>());
 }
 }
 @Override
 public void enqueue(T elemento, int prioridad) throws
IllegalArgumentException {
 if (prioridad < 0 || prioridad >= nivelesDePrioridad) {
 throw new IllegalArgumentException("Prioridad fuera de rango.");
 }
 colas.get(prioridad).enqueue(elemento);
 }
 @Override
 public T dequeue() throws ExcepcionIsEmpty {
for (int i = 0; i < nivelesDePrioridad; i++) {
 if (!colas.get(i).isEmpty()) {
 return colas.get(i).dequeue();
 }
 }
 throw new ExcepcionIsEmpty("Todas las colas están vacías.");
 }
 @Override
 public boolean isEmpty() {
 for (QueueLink<T> cola : colas) {
 if (!cola.isEmpty()) {
 return false;
 }
 }
 return true;
 }
}