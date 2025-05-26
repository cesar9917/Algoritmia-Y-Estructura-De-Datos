package AVLTree;

public class AVLTree<E extends Comparable<E>> extends BSTree<E> {

    class NodeAVL extends Node {
        protected int bf; 

        public NodeAVL(E data) {
            super(data);
            this.bf = 0;
        }

        @Override
        public String toString() {
            return data + " (bf=" + bf + ")";
        }
    }

    private boolean height;     
    private boolean height_del; 

    public AVLTree() {
        super();
    }

    @Override
    public void insert(E x) throws ItemDuplicated {
        this.height = false;
        this.root = insert(x, (NodeAVL) this.root);
    }
    @Override
    protected Node insert(E x, Node node) throws ItemDuplicated {
       return insert(x, (NodeAVL) node); 
    }

    protected NodeAVL insert(E x, NodeAVL node) throws ItemDuplicated {
        NodeAVL fat = node;

        if (node == null) {
            this.height = true;
            fat = new NodeAVL(x);
        } else {
            int resC = node.data.compareTo(x);
            if (resC == 0) {
                throw new ItemDuplicated(x + " ya se encuentra en el árbol");
            }

            if (resC < 0) { // x > node.data → insertar en subárbol derecho
                fat.right = insert(x, (NodeAVL) node.right);
                if (this.height) {
                    switch (fat.bf) {
                        case -1: fat.bf = 0; this.height = false; break;
                        case 0:  fat.bf = 1; this.height = true; break;
                        case 1:  fat = balanceToLeft(fat); this.height = false; break;
                    }
                }
            } else { 
                fat.left = insert(x, (NodeAVL) node.left);
                if (this.height) {
                    switch (fat.bf) {
                        case 1:  fat.bf = 0; this.height = false; break;
                        case 0:  fat.bf = -1; this.height = true; break;
                        case -1: fat = balanceToRight(fat); this.height = false; break;
                    }
                }
            }
        }
        updateHeight(fat); 
        return fat;
    }

    // --- Rotaciones y Balanceo (como estaban) ---
    protected NodeAVL rotateSR(NodeAVL node) {
        NodeAVL child = (NodeAVL) node.left;
        node.left = child.right;
        child.right = node;
        updateHeight(node);
        updateHeight(child);
        return child;
    }

    protected NodeAVL rotateSL(NodeAVL node) {
        NodeAVL child = (NodeAVL) node.right;
        node.right = child.left;
        child.left = node;
        updateHeight(node);
        updateHeight(child);
        return child;
    }

    protected NodeAVL doubleRotateLeft(NodeAVL node) {
        node.right = rotateSR((NodeAVL) node.right);
        return rotateSL(node);
    }

    protected NodeAVL doubleRotateRight(NodeAVL node) {
        node.left = rotateSL((NodeAVL) node.left);
        return rotateSR(node);
    }

    protected NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL child = (NodeAVL) node.right;
        if (child.bf == 1) { // RSL
            node.bf = 0;
            child.bf = 0;
            node = rotateSL(node);
        } else { // RDL (child.bf == -1)
            NodeAVL grandchild = (NodeAVL) child.left;
            switch (grandchild.bf) {
                case 1:  node.bf = -1; child.bf = 0; break;
                case 0:  node.bf = 0;  child.bf = 0; break;
                case -1: node.bf = 0;  child.bf = 1; break;
            }
            grandchild.bf = 0;
            node = doubleRotateLeft(node);
        }
        return node;
    }

    protected NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL child = (NodeAVL) node.left;
        if (child.bf == -1) { // RSR
            node.bf = 0;
            child.bf = 0;
            node = rotateSR(node);
        } else { // RDR (child.bf == 1)
            NodeAVL grandchild = (NodeAVL) child.right;
            switch (grandchild.bf) {
                case -1: node.bf = 1;  child.bf = 0; break;
                case 0:  node.bf = 0;  child.bf = 0; break;
                case 1:  node.bf = 0;  child.bf = -1; break;
            }
            grandchild.bf = 0;
            node = doubleRotateRight(node);
        }
        return node;
    }

    // --- Redefinición de Delete para AVL ---
    @Override
    public void delete(E x) throws ItemNotFound {
        this.height_del = false; // Flag para controlar cambios de altura en delete
        this.root = delete(x, (NodeAVL) this.root);
    }

    protected NodeAVL delete(E x, NodeAVL node) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("Elemento " + x + " no encontrado para eliminar.");
        }

        NodeAVL fat = node;
        int resC = node.data.compareTo(x);

        if (resC < 0) { // Eliminar de la derecha
            fat.right = delete(x, (NodeAVL) node.right);
            if (this.height_del) {
                fat = balanceAfterDeleteRight(fat);
            }
        } else if (resC > 0) { // Eliminar de la izquierda
            fat.left = delete(x, (NodeAVL) node.left);
            if (this.height_del) {
                fat = balanceAfterDeleteLeft(fat);
            }
        } else { // Nodo encontrado
            if (node.left == null) {
                this.height_del = true;
                return (NodeAVL) node.right;
            } else if (node.right == null) {
                this.height_del = true;
                return (NodeAVL) node.left;
            } else { // Nodo con dos hijos
                NodeAVL successor = (NodeAVL) findMin(node.right);
                fat.data = successor.data;
                fat.right = delete(successor.data, (NodeAVL) node.right);
                if (this.height_del) {
                    fat = balanceAfterDeleteRight(fat);
                }
            }
        }
        updateHeight(fat);
        // El balanceo actualiza bf, pero podemos recalcular si es necesario
        // updateBf(fat
        return fat;
    }

    // Busca el mínimo y devuelve NodeAVL
    @Override
    protected NodeAVL findMin(Node node) {
        NodeAVL current = (NodeAVL) node;
        while (current != null && current.left != null) {
            current = (NodeAVL) current.left;
        }
        return current;
    }


    // Balanceo después de eliminar del subárbol IZQUIERDO (el árbol se acorta por la izquierda)
    private NodeAVL balanceAfterDeleteLeft(NodeAVL node) {
        switch (node.bf) {
            case 0: // Era balanceado, ahora bf=1, la altura disminuyó
                node.bf = 1;
                this.height_del = false; // La altura total no cambia más
                break;
            case -1: // Era -1, ahora bf=0, la altura disminuyó
                node.bf = 0;
                this.height_del = true; // La altura sigue disminuyendo
                break;
            case 1: // Era 1, ahora bf=2 -> ¡Desbalance!
                NodeAVL child = (NodeAVL) node.right;
                if (child.bf >= 0) { // RSL (o RDL si bf=0)
                    node = rotateSL(node);
                    if (child.bf == 0) {
                        node.bf = -1; ((NodeAVL)node.left).bf = 1;
                        this.height_del = false; // Altura no cambia más
                    } else { // child.bf == 1
                        node.bf = 0; ((NodeAVL)node.left).bf = 0;
                        this.height_del = true; // Altura sigue cambiando
                    }
                } else { // RDL
                    node = doubleRotateLeft(node); // balanceToLeft maneja RDL
                    this.height_del = true; // Altura sigue cambiando
                }
                updateBf((NodeAVL)node.left);
                updateBf((NodeAVL)node.right);
                updateBf(node);
                break;
        }
        return node;
    }

    // Balanceo después de eliminar del subárbol DERECHO (el árbol se acorta por la derecha)
    private NodeAVL balanceAfterDeleteRight(NodeAVL node) {
        switch (node.bf) {
            case 0: // Era balanceado, ahora bf=-1, la altura disminuyó
                node.bf = -1;
                this.height_del = false; // La altura total no cambia más
                break;
            case 1: // Era 1, ahora bf=0, la altura disminuyó
                node.bf = 0;
                this.height_del = true; // La altura sigue disminuyendo
                break;
            case -1: // Era -1, ahora bf=-2 -> ¡Desbalance!
                NodeAVL child = (NodeAVL) node.left;
                if (child.bf <= 0) { // RSR (o RDR si bf=0)
                    node = rotateSR(node);
                    if (child.bf == 0) {
                        node.bf = 1; ((NodeAVL)node.right).bf = -1;
                        this.height_del = false; // Altura no cambia más
                    } else { // child.bf == -1
                        node.bf = 0; ((NodeAVL)node.right).bf = 0;
                        this.height_del = true; // Altura sigue cambiando
                    }
                } else { // RDR
                    node = doubleRotateRight(node); // balanceToRight maneja RDR
                    this.height_del = true; // Altura sigue cambiando
                }
                updateBf((NodeAVL)node.left);
                updateBf((NodeAVL)node.right);
                updateBf(node);
                break;
        }
        return node;
    }

     // Método auxiliar para actualizar bf (puede ser útil)
    private void updateBf(NodeAVL node) {
        if (node != null) {
            node.bf = height(node.right) - height(node.left);
        }
    }

    // --- Sobrescribir toString para mostrar bf ---
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString((NodeAVL) this.root, sb, "", true);
        return sb.toString();
    }

    private void toString(NodeAVL node, StringBuilder sb, String prefix, boolean isTail) {
        if (node == null) return;
        sb.append(prefix).append(isTail ? "└── " : "├── ").append(node.toString()).append("\n");
        String newPrefix = prefix + (isTail ? "    " : "│   ");
        if (node.right != null || node.left != null) {
            toString((NodeAVL) node.right, sb, newPrefix, node.left == null);
            toString((NodeAVL) node.left, sb, newPrefix, true);
        }
    }

}




