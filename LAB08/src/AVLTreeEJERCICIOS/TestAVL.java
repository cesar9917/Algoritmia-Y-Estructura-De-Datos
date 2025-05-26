package AVLTree;

import java.util.InputMismatchException;
import java.util.Scanner;
import AVLTree.BSTree.ItemDuplicated; // Asegúrate de que estas sean accesibles
import AVLTree.BSTree.ItemNotFound;   // O usa las clases separadas

public class TestAVL {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = getUserChoice();

            try {
                switch (choice) {
                    case 1: runOriginalInsertTest(); break;
                    case 2: runBstVsAvlTest(); break;
                    case 3: runBfsTest(); break;
                    case 4: runPreOrderTest(); break;
                    case 5: runRotationTest(); break;
                    case 0: System.out.println("\nSaliendo del programa. ¡Hasta luego!"); break;
                    default: System.out.println("Opción no válida. Por favor, intente de nuevo."); break;
                }
            } catch (ItemDuplicated | ItemNotFound e) {
                System.err.println("\n!!! Ocurrió un error durante la prueba: " + e.getMessage() + " !!!\n");
            } catch (Exception e) {
                 System.err.println("\n!!! Ocurrió un error inesperado: " + e.getMessage() + " !!!\n");
                 e.printStackTrace();
            }

            if (choice != 0) {
                System.out.println("\nPresione Enter para volver al menú...");
                scanner.nextLine(); // Consumir el newline pendiente (si lo hay)
                scanner.nextLine(); // Esperar a que el usuario presione Enter
            }

        } while (choice != 0);

        scanner.close();
    }

    /**
     * Imprime el menú de opciones en la consola.
     */
    private static void printMenu() {
        System.out.println("\n======================================");
        System.out.println("      MENÚ DE PRUEBAS ÁRBOL AVL      ");
        System.out.println("======================================");
        System.out.println("1. Test Inserción Original (8 Casos)");
        System.out.println("2. Comparar BST vs. AVL");
        System.out.println("3. Test Recorrido por Amplitud (BFS)");
        System.out.println("4. Test Recorrido Preorden");
        System.out.println("5. Test Rotaciones (Inserción y Eliminación)");
        System.out.println("0. Salir");
        System.out.println("======================================");
        System.out.print("Ingrese su opción: ");
    }

    /**
     * Lee y valida la opción ingresada por el usuario.
     * @return La opción elegida como un entero.
     */
    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor ingrese un número.");
            scanner.next(); // Consume la entrada inválida
            choice = -1; // Asigna un valor inválido para repetir
        }
        return choice;
    }

    /**
     * Imprime el estado actual de un árbol AVL para facilitar la visualización.
     * @param tree El árbol a imprimir.
     * @param message Un mensaje descriptivo.
     */
    private static void printState(AVLTree<Integer> tree, String message) {
        System.out.println("\n--- " + message + " ---");
        System.out.println("Árbol (Estructura):");
        System.out.println(tree.toString().isEmpty() ? "(Árbol vacío)" : tree.toString());
        System.out.println("Preorden: " + tree.getPreOrderString());
        System.out.println("Amplitud (BFS): " + tree.getLevelOrderString());
        System.out.println("Altura: " + tree.height());
        System.out.println("----------------------------------------");
    }

    // --- MÉTODOS DE PRUEBA ---

    private static void runOriginalInsertTest() throws ItemDuplicated {
        System.out.println("\n===== 1. Ejecutando Test Inserción Original =====");
        AVLTree<Integer> tree = new AVLTree<>();

        System.out.println("Caso 1 - RSR: 30, 20, 10");
        tree.insert(30); tree.insert(20); tree.insert(10);
        printState(tree, "Después del Caso 1");

        System.out.println("Caso 2 - RSL: 40, 50");
        tree.insert(40); tree.insert(50);
        printState(tree, "Después del Caso 2");

        System.out.println("Caso 3 - RDR: 5, 8");
        tree.insert(5); tree.insert(8);
        printState(tree, "Después del Caso 3");

        System.out.println("Caso 4 - RDL: 60, 55");
        tree.insert(60); tree.insert(55);
        printState(tree, "Después del Caso 4");

        System.out.println("Caso 5 - Otra RSR: 2");
        tree.insert(2);
        printState(tree, "Después del Caso 5");

        System.out.println("Caso 6 - Otra RSL: 70");
        tree.insert(70);
        printState(tree, "Después del Caso 6");

        System.out.println("Caso 7 - Otra RDR: 1, 3");
        tree.insert(1); tree.insert(3);
        printState(tree, "Después del Caso 7");

        System.out.println("Caso 8 - Otra RDL: 65, 67");
        tree.insert(65); tree.insert(67);
        printState(tree, "Después del Caso 8 (Final)");
    }

    private static void runBstVsAvlTest() throws ItemDuplicated {
        System.out.println("\n===== 2. Ejecutando Comparación BST vs. AVL =====");

        System.out.println("\n--- Caso 1: Inserción Secuencial (10 a 50) ---");
        BSTree<Integer> bst1 = new BSTree<>();
        AVLTree<Integer> avl1 = new AVLTree<>();
        int[] data1 = {10, 20, 30, 40, 50};
        for (int d : data1) { bst1.insert(d); avl1.insert(d); }
        System.out.println("\nÁrbol BST 1 (Altura: " + bst1.height() + "):\n" + bst1.toString());
        System.out.println("\nÁrbol AVL 1 (Altura: " + avl1.height() + "):\n" + avl1.toString());

        System.out.println("\n--- Caso 2: Inserción Variada ---");
        BSTree<Integer> bst2 = new BSTree<>();
        AVLTree<Integer> avl2 = new AVLTree<>();
        int[] data2 = {50, 20, 70, 10, 30, 60, 5};
        for (int d : data2) { bst2.insert(d); avl2.insert(d); }
        System.out.println("\nÁrbol BST 2 (Altura: " + bst2.height() + "):\n" + bst2.toString());
        System.out.println("\nÁrbol AVL 2 (Altura: " + avl2.height() + "):\n" + avl2.toString());
    }

    private static void runBfsTest() throws ItemDuplicated {
        System.out.println("\n===== 3. Ejecutando Test Recorrido por Amplitud (BFS) =====");
        AVLTree<Integer> bfsTree = new AVLTree<>();
        int[] bfsData = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
        for (int d : bfsData) { bfsTree.insert(d); }
        printState(bfsTree, "Árbol para BFS");
        System.out.println("Recorrido BFS (Nivel por Nivel):");
        bfsTree.printLevelOrderRecursive();
    }

    private static void runPreOrderTest() throws ItemDuplicated {
        System.out.println("\n===== 4. Ejecutando Test Recorrido Preorden =====");
        AVLTree<Integer> preTree = new AVLTree<>();
        int[] preData = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
        for (int d : preData) { preTree.insert(d); }
        printState(preTree, "Árbol para Preorden");
        System.out.println("Recorrido Preorden:");
        preTree.printPreOrderRecursive();
    }

    private static void runRotationTest() throws ItemDuplicated, ItemNotFound {
        System.out.println("\n===== 5. Ejecutando Test Rotaciones =====");
        AVLTree<Integer> tree;

        // --- Inserciones ---
        System.out.println("\n*** Pruebas de Inserción ***");
        tree = new AVLTree<>(); tree.insert(30); tree.insert(20);
        printState(tree, "Antes de RSR (Insertando 10)"); tree.insert(10); printState(tree, "Después de RSR");

        tree = new AVLTree<>(); tree.insert(10); tree.insert(20);
        printState(tree, "Antes de RSL (Insertando 30)"); tree.insert(30); printState(tree, "Después de RSL");

        tree = new AVLTree<>(); tree.insert(30); tree.insert(10);
        printState(tree, "Antes de RDR (Insertando 20)"); tree.insert(20); printState(tree, "Después de RDR");

        tree = new AVLTree<>(); tree.insert(10); tree.insert(30);
        printState(tree, "Antes de RDL (Insertando 20)"); tree.insert(20); printState(tree, "Después de RDL");

        // --- Eliminaciones ---
        System.out.println("\n*** Pruebas de Eliminación ***");
        tree = new AVLTree<>();
        int[] dataDel1 = {40, 20, 60, 10, 50, 70, 80};
        for(int d : dataDel1) tree.insert(d);
        printState(tree, "Antes de Eliminar 10 (Causa RSL)"); tree.delete(10); printState(tree, "Después de Eliminar 10 (RSL)");

        tree = new AVLTree<>();
        int[] dataDel2 = {40, 20, 60, 10, 30, 5, 15};
        for(int d : dataDel2) tree.insert(d);
        printState(tree, "Antes de Eliminar 60 (Causa RSR)"); tree.delete(60); printState(tree, "Después de Eliminar 60 (RSR)");
    }
}