package btree;

public class Main2 {
    public static void main(String[] args) {
        BTree<RegistroEstudiante> arbol = new BTree<>(4);

        System.out.println("===== REGISTRO DE ESTUDIANTES =====");
        arbol.insert(new RegistroEstudiante(103, "Ana"));
        arbol.insert(new RegistroEstudiante(110, "Luis"));
        arbol.insert(new RegistroEstudiante(101, "Carlos"));
        arbol.insert(new RegistroEstudiante(120, "Lucía"));
        arbol.insert(new RegistroEstudiante(115, "David"));
        arbol.insert(new RegistroEstudiante(125, "Jorge"));
        arbol.insert(new RegistroEstudiante(140, "Camila"));
        arbol.insert(new RegistroEstudiante(108, "Rosa"));
        arbol.insert(new RegistroEstudiante(132, "Ernesto"));
        arbol.insert(new RegistroEstudiante(128, "Denis"));
        arbol.insert(new RegistroEstudiante(145, "Enrique"));
        arbol.insert(new RegistroEstudiante(122, "Karina"));
        arbol.insert(new RegistroEstudiante(108, "Juan")); // Duplicado

        System.out.println("\n===== BÚSQUEDAS =====");
        System.out.println("Buscando estudiante con código 115: " + arbol.buscarNombre(115)); // David
        System.out.println("Buscando estudiante con código 132: " + arbol.buscarNombre(132)); // Ernesto
        System.out.println("Buscando estudiante con código 999: " + arbol.buscarNombre(999)); // No encontrado

        System.out.println("\n===== ELIMINACIÓN =====");
        System.out.println("Eliminando estudiante con código 101...");
        arbol.remove(new RegistroEstudiante(101, "")); // Eliminar Carlos

        System.out.println("\n===== INSERCIÓN DE NUEVO ESTUDIANTE =====");
        System.out.println("Insertando estudiante con código 106: Sara");
        arbol.insert(new RegistroEstudiante(106, "Sara"));

        System.out.println("Buscando estudiante con código 106: " + arbol.buscarNombre(106)); // Sara

        System.out.println("\n===== ESTADO ACTUAL DEL ÁRBOL =====");
        System.out.println(arbol.toString());
    }
}
