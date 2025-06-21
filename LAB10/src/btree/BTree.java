package btree;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(this.root, cl);
        if (up) {
            pnew = new BNode<>(orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        int[] pos = new int[1];
        E mediana;
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean found = current.searchNode(cl, pos);
            if (found) {
                System.out.println("Item duplicado");
                up = false;
                return null;
            }

            mediana = push(current.childs.get(pos[0]), cl);
            if (up) {
                if (current.nodeFull(orden - 1)) {
                    mediana = dividedNode(current, mediana, pos[0]);
                } else {
                    putNode(current, mediana, nDes, pos[0]);
                    up = false;
                }
            }
            return mediana;
        }
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;
        posMdna = (k <= orden / 2) ? orden / 2 : orden / 2 + 1;

        nDes = new BNode<>(orden);

        for (i = posMdna; i < orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }

        nDes.count = (orden - 1) - posMdna;
        current.count = posMdna;

        if (k <= orden / 2) {
            putNode(current, cl, rd, k);
        } else {
            putNode(nDes, cl, rd, k - posMdna);
        }

        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;

        return median;
    }
    public boolean search(E cl) {
        return searchRec(this.root, cl);
    }

    private boolean searchRec(BNode<E> current, E cl) {
        if (current == null) return false;

        int[] pos = new int[1];
        boolean found = current.searchNode(cl, pos);

        if (found) {
            System.out.println(cl + " se encuentra en el nodo " + current.idNode + " en la posición " + pos[0]);
            return true;
        } else {
            return searchRec(current.childs.get(pos[0]), cl);
        }
    }
    public void remove(E cl) {
        if (isEmpty()) {
            System.out.println("El árbol está vacío.");
            return;
        }

        boolean underflow = removeRec(this.root, cl);

        // Si después de borrar, la raíz queda vacía y tiene hijos, cambia de raíz.
        if (root.count == 0 && root.childs.get(0) != null) {
            root = root.childs.get(0);
        }

        if (!underflow) {
            System.out.println("Clave no encontrada: " + cl);
        }
    }
    private boolean removeRec(BNode<E> current, E cl) {
        int[] pos = new int[1];
        boolean found = current.searchNode(cl, pos);

        if (found) {
            if (current.childs.get(0) == null) {
                // Está en hoja
                removeFromLeaf(current, pos[0]);
            } else {
                // Está en nodo interno, buscar sucesor
                E succ = getSuccessor(current.childs.get(pos[0] + 1));
                current.keys.set(pos[0], succ);
                removeRec(current.childs.get(pos[0] + 1), succ);
            }
            return true;
        } else {
        	if (current.childs.get(0) == null) return false;

        	boolean result = removeRec(current.childs.get(pos[0]), cl);

        	// Post-eliminación: validar subdesbordamiento
        	BNode<E> child = current.childs.get(pos[0]);

        	if (child.count < (orden - 1) / 2) {
        	    if (pos[0] > 0 && canBorrowFromLeft(current, pos[0])) {
        	        // Redistribución desde izquierda
        	    } else if (pos[0] < current.count && canBorrowFromRight(current, pos[0])) {
        	        // Redistribución desde derecha
        	    } else if (pos[0] < current.count) {
        	        // Fusión con derecha
        	        fuseWithSibling(current, pos[0]);
        	    } else {
        	        // Fusión con izquierda
        	        fuseWithSibling(current, pos[0] - 1);
        	    }
        	}

        	return result;
        }
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
    private boolean canBorrowFromRight(BNode<E> parent, int pos) {
        BNode<E> child = parent.childs.get(pos);
        BNode<E> right = parent.childs.get(pos + 1);

        if (right != null && right.count > (orden - 1) / 2) {
            child.keys.set(child.count, parent.keys.get(pos));
            child.childs.set(child.count + 1, right.childs.get(0));
            child.count++;

            parent.keys.set(pos, right.keys.get(0));

            for (int i = 0; i < right.count - 1; i++) {
                right.keys.set(i, right.keys.get(i + 1));
                right.childs.set(i, right.childs.get(i + 1));
            }
            right.childs.set(right.count - 1, right.childs.get(right.count));
            right.childs.set(right.count, null);
            right.keys.set(right.count - 1, null);
            right.count--;

            return true;
        }
        return false;
    }
    private boolean canBorrowFromLeft(BNode<E> parent, int pos) {
        BNode<E> child = parent.childs.get(pos);
        BNode<E> left = parent.childs.get(pos - 1);

        if (left != null && left.count > (orden - 1) / 2) {
            for (int i = child.count; i > 0; i--) {
                child.keys.set(i, child.keys.get(i - 1));
                child.childs.set(i + 1, child.childs.get(i));
            }
            child.childs.set(1, child.childs.get(0));

            child.keys.set(0, parent.keys.get(pos - 1));
            child.childs.set(0, left.childs.get(left.count));
            child.count++;

            parent.keys.set(pos - 1, left.keys.get(left.count - 1));
            left.keys.set(left.count - 1, null);
            left.childs.set(left.count, null);
            left.count--;

            return true;
        }
        return false;
    }
    private void fuseWithSibling(BNode<E> parent, int pos) {
        BNode<E> left = parent.childs.get(pos);
        BNode<E> right = parent.childs.get(pos + 1);

        left.keys.set(left.count, parent.keys.get(pos));
        left.count++;

        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count + i, right.keys.get(i));
            left.childs.set(left.count + i + 1, right.childs.get(i + 1));
        }

        left.count += right.count;

        for (int i = pos; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }

        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }

    public String toString() {
        if (isEmpty()) return "BTree is empty...";
        return writeTree(this.root);
    }

    private String writeTree(BNode<E> current) {
        if (current == null) return "";
        StringBuilder sb = new StringBuilder(current.toString() + "\n");
        for (int i = 0; i <= current.count; i++) {
            sb.append(writeTree(current.childs.get(i)));
        }
        return sb.toString();
    }
    
    public String buscarNombre(int codigo) {
        RegistroEstudiante temp = new RegistroEstudiante(codigo, "");
        return buscarNombreRec(this.root, temp);
    }

    private String buscarNombreRec(BNode<E> current, RegistroEstudiante target) {
        if (current == null) return "No encontrado";

        int[] pos = new int[1];
        boolean found = current.searchNode((E) target, pos);

        if (found) {
            RegistroEstudiante est = (RegistroEstudiante) current.keys.get(pos[0]);
            return est.getNombre();
        } else {
            return buscarNombreRec(current.childs.get(pos[0]), target);
        }
    }

}
