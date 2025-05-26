package AVLTree;

public class BSTree<E extends Comparable<E>> {
    class Node {
        protected E data;
        protected Node left, right;

        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public String toString() {
            return data.toString();
        }
    }

    protected Node root;

    public BSTree() {
        root = null;
    }

    // Aquí podrías agregar métodos como insert, delete, search, etc.
}
