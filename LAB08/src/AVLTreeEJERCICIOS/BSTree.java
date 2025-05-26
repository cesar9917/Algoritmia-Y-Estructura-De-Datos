package AVLTree;

public class BSTree<E extends Comparable<E>> {

    // Clase Node ahora protegida y con 'height'
    protected class Node {
        protected E data;
        protected Node left, right;
        protected int height; // Altura del subárbol rooted en este nodo

        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1; // Un nodo nuevo tiene altura 1
        }

        public String toString() {
            return data.toString();
        }
    }

    protected Node root;

    public BSTree() {
        root = null;
    }

    // --- Altura ---
    protected int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    public int height() {
        return height(this.root);
    }

    protected void updateHeight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    // --- Búsqueda ---
    public E search(E x) throws ItemNotFound {
        Node res = search(x, this.root);
        if (res == null) {
            throw new ItemNotFound("Elemento " + x + " no encontrado.");
        }
        return res.data;
    }

    protected Node search(E x, Node node) {
        if (node == null) {
            return null;
        }
        int resC = node.data.compareTo(x);
        if (resC == 0) {
            return node;
        } else if (resC < 0) {
            return search(x, node.right);
        } else {
            return search(x, node.left);
        }
    }

    // --- Inserción (BST Básico) ---
    public void insert(E x) throws ItemDuplicated {
        this.root = insert(x, this.root);
    }

    protected Node insert(E x, Node node) throws ItemDuplicated {
        if (node == null) {
            return new Node(x); // Crea un nodo BST simple
        }
        int resC = node.data.compareTo(x);
        if (resC == 0) {
            throw new ItemDuplicated(x + " ya existe en el árbol.");
        }
        if (resC < 0) {
            node.right = insert(x, node.right);
        } else {
            node.left = insert(x, node.left);
        }
        updateHeight(node); // Actualiza la altura
        return node;
    }

    // --- Eliminación (BST Básico) ---
    public void delete(E x) throws ItemNotFound {
        this.root = delete(x, this.root);
    }

    protected Node delete(E x, Node node) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("Elemento " + x + " no encontrado para eliminar.");
        }
        int resC = node.data.compareTo(x);
        if (resC < 0) {
            node.right = delete(x, node.right);
        } else if (resC > 0) {
            node.left = delete(x, node.left);
        } else { // Nodo encontrado
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else { // Nodo con dos hijos
                node.data = findMin(node.right).data;
                node.right = delete(node.data, node.right);
            }
        }
        updateHeight(node); // Actualiza la altura
        return node;
    }

    protected Node findMin(Node node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    // --- Impresión (para visualización) ---
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(this.root, sb, "", true);
        return sb.toString();
    }

    // Imprime el árbol de forma que la raíz queda a la izquierda
    private void toString(Node node, StringBuilder sb, String prefix, boolean isTail) {
         if (node == null) return;
         sb.append(prefix).append(isTail ? "└── " : "├── ").append(node.toString()).append(" (h=").append(node.height).append(")").append("\n");
         String newPrefix = prefix + (isTail ? "    " : "│   ");
         // Imprimir hijos (ajustar si se quiere un layout diferente)
         if (node.right != null || node.left != null) {
             toString(node.right, sb, newPrefix, node.left == null);
             toString(node.left, sb, newPrefix, true);
         }
    }

    // --- Excepciones ---
    public static class ItemDuplicated extends Exception {
        public ItemDuplicated(String msg) { super(msg); }
    }

    public static class ItemNotFound extends Exception {
        public ItemNotFound(String msg) { super(msg); }
    }
    public void printLevelOrderRecursive() {
        int h = height();
        System.out.println("Iniciando Recorrido por Amplitud (Nivel por Nivel):");
        for (int i = 1; i <= h; i++) {
            System.out.print("Nivel " + (i - 1) + ": ");
            printGivenLevel(this.root, i);
            System.out.println(); 
        }
        }
        public String getLevelOrderString() {
            StringBuilder sb = new StringBuilder();
            int h = height();
            for (int i = 1; i <= h; i++) {
                appendGivenLevel(this.root, i, sb);
            }
            // Elimina el último espacio si existe
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
        private void printGivenLevel(Node node, int level) {
            if (node == null) {
                return; // No hay nada que imprimir si el nodo es nulo
            }
            if (level == 1) {
                System.out.print(node.data + " "); // Imprime el dato si estamos en el nivel correcto
            } else if (level > 1) {
                // Llama recursivamente para el nivel anterior en los subárboles izquierdo y derecho
                printGivenLevel(node.left, level - 1);
                printGivenLevel(node.right, level - 1);
            }
        }
        private void appendGivenLevel(Node node, int level, StringBuilder sb) {
            if (node == null) {
                return; // No hay nada que agregar si el nodo es nulo
            }
            if (level == 1) {
                sb.append(node.data).append(" "); // Agrega el dato si estamos en el nivel correcto
            } else if (level > 1) {
                // Llama recursivamente para el nivel anterior en los subárboles izquierdo y derecho
                appendGivenLevel(node.left, level - 1, sb);
                appendGivenLevel(node.right, level - 1, sb);
            }
            }
            public void printPreOrderRecursive() {
                System.out.println("Iniciando Recorrido en Preorden:");
                printPreOrder(this.root);
                System.out.println(); // Salto de línea final
            }
            public String getPreOrderString() {
                StringBuilder sb = new StringBuilder();
                buildPreOrderString(this.root, sb);
                // Elimina el último espacio si existe
                if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString();
            }
            private void printPreOrder(Node node) {
                if (node == null) {
                    return;
                }
                System.out.print(node.data + " "); // 1. Raíz
                printPreOrder(node.left);           // 2. Izquierda
                printPreOrder(node.right);          // 3. Derecha
            }
            private void buildPreOrderString(Node node, StringBuilder sb) {
                if (node == null) {
                    return;
                }
                sb.append(node.data).append(" "); // 1. Raíz
                buildPreOrderString(node.left, sb);           // 2. Izquierda
                buildPreOrderString(node.right, sb);          // 3. Derecha
            
    }
}

