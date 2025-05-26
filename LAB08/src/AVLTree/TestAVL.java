package AVLTree;

public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        try {
            System.out.println("Caso 1 - RSR (Rotación Simple a la Derecha):");
            tree.insert(30);
            tree.insert(20);
            tree.insert(10); // Provoca RSR en 30
            System.out.println("Árbol después del Caso 1\n");

            System.out.println("Caso 2 - RSL (Rotación Simple a la Izquierda):");
            tree.insert(40);
            tree.insert(50); // Provoca RSL en 30
            System.out.println("Árbol después del Caso 2\n");

            System.out.println("Caso 3 - RDR (Rotación Doble a la Derecha):");
            tree.insert(5);  // Preparar estructura para RDR
            tree.insert(8);  // Provoca RDR en 10
            System.out.println("Árbol después del Caso 3\n");

            System.out.println("Caso 4 - RDL (Rotación Doble a la Izquierda):");
            tree.insert(60); // Preparar estructura
            tree.insert(55); // Provoca RDL en 50
            System.out.println("Árbol después del Caso 4\n");

            // 4 casos más para robustez y repetición de rotaciones
            System.out.println("Caso 5 - Otra RSR:");
            tree.insert(2);  // Provoca RSR
            System.out.println("Árbol después del Caso 5\n");

            System.out.println("Caso 6 - Otra RSL:");
            tree.insert(70);  // Provoca RSL
            System.out.println("Árbol después del Caso 6\n");

            System.out.println("Caso 7 - Otra RDR:");
            tree.insert(1);
            tree.insert(3); // Provoca RDR
            System.out.println("Árbol después del Caso 7\n");

            System.out.println("Caso 8 - Otra RDL:");
            tree.insert(65);
            tree.insert(67); // Provoca RDL
            System.out.println("Árbol después del Caso 8\n");

        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
