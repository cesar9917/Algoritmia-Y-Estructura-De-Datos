package Graph;
import estructuras.lineales.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
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
    public void bfs(E startData) {
        Vertex<E> startVertex = searchVertex(startData);
        if (startVertex == null) {
            System.out.println("El vértice inicial no existe.");
            return;
        }

        ListLinked<Vertex<E>> colaSimulada = new ListLinked<>(); 
        Set<Vertex<E>> visited = new HashSet<>();

        System.out.print("BFS recorrido: ");
        colaSimulada.insertarFinal(startVertex);
        visited.add(startVertex);

        while (!colaSimulada.esVacia()) { 
            Vertex<E> currentVertex = colaSimulada.getCabeza().dato; 
            colaSimulada.eliminar(currentVertex); 

            System.out.print(currentVertex.getData() + " ");

            ListLinked.Nodo<Edge<E>> actual = currentVertex.listAdj.getCabeza();
            while (actual != null) {
                Vertex<E> neighbor = actual.dato.getRefDest();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    colaSimulada.insertarFinal(neighbor);
                }
                actual = actual.siguiente;
            }
        }
        System.out.println();
    }

    public ArrayList<E> bfsPath(E startData, E endData) {
        ArrayList<E> path = new ArrayList<>();
        Vertex<E> startVertex = searchVertex(startData);
        Vertex<E> endVertex = searchVertex(endData);

        if (startVertex == null || endVertex == null) {
            System.out.println("Uno o ambos vértices no existen.");
            return path;
        }

        ListLinked<Vertex<E>> colaSimulada = new ListLinked<>(); 
        Set<Vertex<E>> visited = new HashSet<>();
        Map<Vertex<E>, Vertex<E>> parentMap = new HashMap<>();

        colaSimulada.insertarFinal(startVertex);
        visited.add(startVertex);
        parentMap.put(startVertex, null);

        while (!colaSimulada.esVacia()) { 
            Vertex<E> currentVertex = colaSimulada.getCabeza().dato; 
            colaSimulada.eliminar(currentVertex); 

            if (currentVertex.equals(endVertex)) {
                
                Vertex<E> backtrackVertex = endVertex;
                while (backtrackVertex != null) {
                    path.add(backtrackVertex.getData());
                    backtrackVertex = parentMap.get(backtrackVertex);
                }
                Collections.reverse(path); 
                return path;
            }

            ListLinked.Nodo<Edge<E>> actual = currentVertex.listAdj.getCabeza();
            while (actual != null) {
                Vertex<E> neighbor = actual.dato.getRefDest();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    colaSimulada.insertarFinal(neighbor);
                    parentMap.put(neighbor, currentVertex);
                }
                actual = actual.siguiente;
            }
        }

        return path; 
    }

    private class DijkstraNode implements Comparable<DijkstraNode> {
        Vertex<E> vertex;
        int distance;

        public DijkstraNode(Vertex<E> vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(DijkstraNode other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public void insertEdgeWeight(E verOri, E verDes, int weight) {
        Vertex<E> vOri = searchVertex(verOri);
        Vertex<E> vDes = searchVertex(verDes);

        if (vOri != null && vDes != null) {
            vOri.listAdj.insertarFinal(new Edge<>(vDes, weight));
            vDes.listAdj.insertarFinal(new Edge<>(vOri, weight));
        }
    }

    // c) isConexo()
    public boolean isConexo() {
        if (listVertex.esVacia()) {
            return true;
        }

        Vertex<E> startVertex = listVertex.getCabeza().dato;
        Set<Vertex<E>> visitedCount = new HashSet<>();

        dfsConexo(startVertex, visitedCount);

        return visitedCount.size() == listVertex.getSize();
    }

    private void dfsConexo(Vertex<E> vertex, Set<Vertex<E>> visited) {
        visited.add(vertex);
        ListLinked.Nodo<Edge<E>> actual = vertex.listAdj.getCabeza();
        while (actual != null) {
            Vertex<E> adyacente = actual.dato.getRefDest();
            if (!visited.contains(adyacente)) {
                dfsConexo(adyacente, visited);
            }
            actual = actual.siguiente;
        }
    }

    public Stack<E> Dijkstra(E startData, E endData) {
        Stack<E> path = new Stack<>();
        Vertex<E> startVertex = searchVertex(startData);
        Vertex<E> endVertex = searchVertex(endData);

        if (startVertex == null || endVertex == null) {
            System.out.println("Uno o ambos vértices no existen para Dijkstra.");
            return path;
        }

        Map<Vertex<E>, Integer> distances = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> predecessors = new HashMap<>();
        PriorityQueue<DijkstraNode> pq = new PriorityQueue<>();

        ListLinked.Nodo<Vertex<E>> currentListVertex = listVertex.getCabeza();
        while (currentListVertex != null) {
            distances.put(currentListVertex.dato, Integer.MAX_VALUE);
            predecessors.put(currentListVertex.dato, null);
            currentListVertex = currentListVertex.siguiente;
        }

        distances.put(startVertex, 0);
        pq.add(new DijkstraNode(startVertex, 0));

        while (!pq.isEmpty()) {
            DijkstraNode current = pq.poll();

            if (current.distance > distances.get(current.vertex)) {
                continue;
            }

            if (current.vertex.equals(endVertex)) {
                Vertex<E> backtrackVertex = endVertex;
                while (backtrackVertex != null) {
                    path.push(backtrackVertex.getData());
                    backtrackVertex = predecessors.get(backtrackVertex);
                }
                return path;
            }

            ListLinked.Nodo<Edge<E>> actualEdge = current.vertex.listAdj.getCabeza();
            while (actualEdge != null) {
                Edge<E> edge = actualEdge.dato;
                Vertex<E> neighbor = edge.getRefDest();
                int newDistance = current.distance + edge.getWeight();

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    predecessors.put(neighbor, current.vertex);
                    pq.add(new DijkstraNode(neighbor, newDistance));
                }
                actualEdge = actualEdge.siguiente;
            }
        }
        return path;
    }

    public ArrayList<E> shortPath(E startData, E endData) {
        ArrayList<E> pathList = new ArrayList<>();
        Stack<E> dijkstraPath = Dijkstra(startData, endData);

        if (dijkstraPath.isEmpty()) {
            return pathList;
        }

        while (!dijkstraPath.isEmpty()) {
            pathList.add(dijkstraPath.pop());
        }
        return pathList;
    }

    @Override
    public String toString() {
        return listVertex.toString();
    }
}