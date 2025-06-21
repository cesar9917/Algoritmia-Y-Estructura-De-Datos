package btree;

import java.util.ArrayList;

public class BNode<E> {
    private static int ID_GEN = 0;
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;
    protected int idNode;

    public BNode(int n) {
        keys = new ArrayList<>(n);
        childs = new ArrayList<>(n + 1);
        count = 0;
        idNode = ID_GEN++;

        for (int i = 0; i < n; i++) {
            keys.add(null);
            childs.add(null);
        }
        childs.add(null); // Uno más que las claves
    }

    public boolean nodeFull(int maxKeys) {
        return count == maxKeys;
    }
    private void removeFromLeaf(BNode<E> node, int pos) {
        for (int i = pos; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
        }
        node.keys.set(node.count - 1, null);
        node.count--;
    }
    private E getSuccessor(BNode<E> node) {
        while (node.childs.get(0) != null) {
            node = node.childs.get(0);
        }
        return node.keys.get(0);
    }
    public boolean nodeEmpty() {
        return count == 0;
    }
    public boolean searchNode(E cl, int[] pos) {
        int i = 0;
        while (i < count && ((Comparable<E>) cl).compareTo(keys.get(i)) > 0) {
            i++;
        }
        pos[0] = i;
        return i < count && ((Comparable<E>) cl).compareTo(keys.get(i)) == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Nodo " + idNode + ": ");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i)).append(" ");
        }
        return sb.toString().trim();
    }
}
