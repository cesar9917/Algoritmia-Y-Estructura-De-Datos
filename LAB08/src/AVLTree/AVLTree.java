package AVLTree;

public class AVLTree<E extends Comparable<E>> extends BSTree<E> {

    class NodeAVL extends Node {
        protected int bf; // balance factor (factor de equilibrio)

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

    public void insert(E x) throws ItemDuplicated {
        this.height = false;
        this.root = insert(x, (NodeAVL) this.root);
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
                        case -1:
                            fat.bf = 0;
                            this.height = false;
                            break;
                        case 0:
                            fat.bf = 1;
                            this.height = true;
                            break;
                        case 1:
                            fat = balanceToLeft(fat);
                            this.height = false;
                            break;
                    }
                }
            } else { // x < node.data → insertar en subárbol izquierdo
                fat.left = insert(x, (NodeAVL) node.left);
                if (this.height) {
                    switch (fat.bf) {
                        case 1:
                            fat.bf = 0;
                            this.height = false;
                            break;
                        case 0:
                            fat.bf = -1;
                            this.height = true;
                            break;
                        case -1:
                            fat = balanceToRight(fat);
                            this.height = false;
                            break;
                    }
                }
            }
        }

        return fat;
    }

    protected NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL child = (NodeAVL) node.right;

        if (child.bf == 1) {
            node = rotateSL(node);
            node.bf = 0;
            ((NodeAVL) node.left).bf = 0;
        } else {
            NodeAVL grandchild = (NodeAVL) child.left;
            switch (grandchild.bf) {
                case 1:
                    node.bf = -1;
                    child.bf = 0;
                    break;
                case 0:
                    node.bf = 0;
                    child.bf = 0;
                    break;
                case -1:
                    node.bf = 0;
                    child.bf = 1;
                    break;
            }
            grandchild.bf = 0;
            node = doubleRotateLeft(node);
        }

        return node;
    }

    protected NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL child = (NodeAVL) node.left;

        if (child.bf == -1) {
            node = rotateSR(node);
            node.bf = 0;
            ((NodeAVL) node.right).bf = 0;
        } else {
            NodeAVL grandchild = (NodeAVL) child.right;
            switch (grandchild.bf) {
                case -1:
                    node.bf = 1;
                    child.bf = 0;
                    break;
                case 0:
                    node.bf = 0;
                    child.bf = 0;
                    break;
                case 1:
                    node.bf = 0;
                    child.bf = -1;
                    break;
            }
            grandchild.bf = 0;
            node = doubleRotateRight(node);
        }

        return node;
    }

    // Rotación Simple a la Derecha (RSR)
    protected NodeAVL rotateSR(NodeAVL node) {
        NodeAVL child = (NodeAVL) node.left;
        node.left = child.right;
        child.right = node;
        return child;
    }

    // Rotación Simple a la Izquierda (RSL)
    protected NodeAVL rotateSL(NodeAVL node) {
        NodeAVL child = (NodeAVL) node.right;
        node.right = child.left;
        child.left = node;
        return child;
    }

    // Rotación Doble a la Izquierda: derecha-izquierda
    protected NodeAVL doubleRotateLeft(NodeAVL node) {
        node.right = rotateSR((NodeAVL) node.right); // primero rotación derecha en hijo derecho
        return rotateSL(node);                       // luego rotación izquierda
    }

    // Rotación Doble a la Derecha: izquierda-derecha
    protected NodeAVL doubleRotateRight(NodeAVL node) {
        node.left = rotateSL((NodeAVL) node.left);   // primero rotación izquierda en hijo izquierdo
        return rotateSR(node);                       // luego rotación derecha
    }
}




