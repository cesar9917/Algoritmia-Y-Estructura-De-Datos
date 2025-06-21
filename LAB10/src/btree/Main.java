package btree;

public class Main {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(5);

        int[] claves = {100, 50, 20, 70, 10, 30, 80, 90, 200, 25, 15, 5, 65, 35, 60, 18, 93, 94};
        for (int clave : claves) {
            tree.insert(clave);
        }

        System.out.println(tree.toString());

        // Búsquedas
        System.out.println("\nBuscar clave 65:");
        boolean existe1 = tree.search(65);
        System.out.println("Resultado: " + existe1);

        System.out.println("\nBuscar clave 999:");
        boolean existe2 = tree.search(999);
        System.out.println("Resultado: " + existe2);
        System.out.println("\n--- Árbol antes de eliminar ---");
        System.out.println(tree.toString());

        System.out.println("\nEliminando clave 65...");
        tree.remove(65);

        System.out.println("\n--- Árbol después de eliminar ---");
        System.out.println(tree.toString());
        System.out.println("\n--- Árbol antes de eliminar ---");
        System.out.println(tree.toString());

        System.out.println("\nEliminando clave 65...");
        tree.remove(65);

        System.out.println("\n--- Árbol después de eliminar ---");
        System.out.println(tree.toString());
        System.out.println("\nEliminando claves: 25, 30, 35, 5, 10");
        tree.remove(25);
        tree.remove(30);
        tree.remove(35);
        tree.remove(5);
        tree.remove(10);

        System.out.println("\n--- Árbol después de eliminar ---");
        System.out.println(tree.toString());
    }
    
}
