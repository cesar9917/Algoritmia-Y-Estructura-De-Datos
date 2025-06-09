package Graph;
import estructuras.lineales.*;
import java.util.HashSet;
import java.util.Set;

public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<>();
    }

    public void insertVertex(E data) {
        if (searchVertex(data) == null) {
            listVertex.insertarFinal(new Vertex<>(data));
        }
    }

    public void insertEdge(E verOri, E verDes) {
        Vertex<E> vOri = searchVertex(verOri);
        Vertex<E> vDes = searchVertex(verDes);

        if (vOri != null && vDes != null) {
            vOri.listAdj.insertarFinal(new Edge<>(vDes));
        }
    }

    public Vertex<E> searchVertex(E data) {
        ListLinked.Nodo<Vertex<E>> actual = listVertex.getCabeza();
        while (actual != null) {
            if (actual.dato.getData().equals(data)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean searchEdge(E verOri, E verDes) {
        Vertex<E> vOri = searchVertex(verOri);
        Vertex<E> vDes = searchVertex(verDes);
        if (vOri == null || vDes == null) return false;

        ListLinked.Nodo<Edge<E>> actual = vOri.listAdj.getCabeza();
        while (actual != null) {
            if (actual.dato.getRefDest().equals(vDes)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean removeVertex(E data) {
        Vertex<E> toRemove = searchVertex(data);
        if (toRemove == null) return false;

        ListLinked.Nodo<Vertex<E>> actual = listVertex.getCabeza();
        while (actual != null) {
            actual.dato.listAdj.eliminar(new Edge<>(toRemove));
            actual = actual.siguiente;
        }

        return listVertex.eliminar(toRemove);
    }

    public boolean removeEdge(E verOri, E verDes) {
        Vertex<E> vOri = searchVertex(verOri);
        Vertex<E> vDes = searchVertex(verDes);

        if (vOri == null || vDes == null) return false;

        return vOri.listAdj.eliminar(new Edge<>(vDes));
    }

    public void dfs(E startData) {
        Vertex<E> startVertex = searchVertex(startData);
        if (startVertex == null) {
            System.out.println("El vértice inicial no existe.");
            return;
        }

        Set<Vertex<E>> visitados = new HashSet<>();
        System.out.print("DFS recorrido: ");
        dfsRecursivo(startVertex, visitados);
        System.out.println();
    }

    private void dfsRecursivo(Vertex<E> vertex, Set<Vertex<E>> visitados) {
        visitados.add(vertex);
        System.out.print(vertex.getData() + " ");

        ListLinked.Nodo<Edge<E>> actual = vertex.listAdj.getCabeza();
        while (actual != null) {
            Vertex<E> adyacente = actual.dato.getRefDest();
            if (!visitados.contains(adyacente)) {
                dfsRecursivo(adyacente, visitados);
            }
            actual = actual.siguiente;
        }
    }

    @Override
    public String toString() {
        return listVertex.toString();
    }
}
