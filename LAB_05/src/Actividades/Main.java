package Actividades;

public class Main {
    public static void main(String[] args) {
        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();

        Tarea t1 = new Tarea("Estudiar para el examen", 3);
        Tarea t2 = new Tarea("Hacer ejercicio", 2);
        Tarea t3 = new Tarea("Preparar presentación", 5);

        gestor.agregarTarea(t1);
        gestor.agregarTarea(t2);
        gestor.agregarTarea(t3);

        System.out.println("Tareas pendientes:");
        gestor.imprimirTareas();

        gestor.eliminarTarea(t2);

        System.out.println("\nTareas pendientes después de eliminar una:");
        gestor.imprimirTareas();

        System.out.println("\nTareas completadas:");
        gestor.mostrarTareasCompletadas();

        Tarea masPrioritaria = (Tarea) gestor.obtenerTareaMasPrioritaria();
        System.out.println("\nTarea con mayor prioridad:");
        System.out.println(masPrioritaria);

        gestor.invertirTareas();
        System.out.println("\nTareas después de invertir:");
        gestor.imprimirTareas();
    }
}
