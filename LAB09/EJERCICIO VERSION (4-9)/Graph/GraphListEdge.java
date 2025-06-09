package Graph;

import java.util.*;

public class GraphListEdge<V, E> {
    ArrayList<VertexObj<V, E>> secVertex;
    ArrayList<EdgeObj<V, E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    public void insertVertex(V v) {
        if (!searchVertex(v)) {
            secVertex.add(new VertexObj<>(v, secVertex.size()));
        }
    }

    public void insertDirectedEdge(V source, V destination, E weight) {
        VertexObj<V, E> vSource = getVertexObj(source);
        VertexObj<V, E> vDest = getVertexObj(destination);

        if (vSource != null && vDest != null) {
            if (!searchDirectedEdge(source, destination)) {
                secEdge.add(new EdgeObj<>(vSource, vDest, weight, secEdge.size(), true));
            }
        }
    }

    public void insertEdge(V v, V z) {
        insertDirectedEdge(v, z, null);
    }

    public boolean searchVertex(V v) {
        return getVertexObj(v) != null;
    }

    public boolean searchDirectedEdge(V source, V destination) {
        VertexObj<V, E> vSource = getVertexObj(source);
        VertexObj<V, E> vDest = getVertexObj(destination);
        if (vSource == null || vDest == null) return false;

        for (EdgeObj<V, E> edge : secEdge) {
            if (edge.isDirected() && edge.getSource().equals(vSource) && edge.getDestination().equals(vDest)) {
                return true;
            }
        }
        return false;
    }

    public boolean searchEdge(V v, V z) {
        return searchDirectedEdge(v, z);
    }

    public void bfs(V v) {
        VertexObj<V, E> start = getVertexObj(v);
        if (start == null) {
            System.out.println("El vértice inicial no existe.");
            return;
        }

        Set<VertexObj<V, E>> visited = new HashSet<>();
        Queue<VertexObj<V, E>> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.print("BFS recorrido: ");
        while (!queue.isEmpty()) {
            VertexObj<V, E> current = queue.poll();
            System.out.print(current.getInfo() + " ");

            for (EdgeObj<V, E> edge : secEdge) {
                if (edge.getSource().equals(current) && edge.isDirected()) {
                    VertexObj<V, E> neighbor = edge.getDestination();
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }

    private VertexObj<V, E> getVertexObj(V v) {
        for (VertexObj<V, E> vertex : secVertex) {
            if (vertex.getInfo().equals(v)) {
                return vertex;
            }
        }
        return null;
    }

    public void displayFormalDefinition() {
        System.out.println("Definición Formal del Grafo Dirigido:");
        System.out.print("Vértices (V): {");
        for (int i = 0; i < secVertex.size(); i++) {
            System.out.print(secVertex.get(i).getInfo());
            if (i < secVertex.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");

        System.out.print("Aristas (E): {");
        boolean first = true;
        for (EdgeObj<V, E> edge : secEdge) {
            if (edge.isDirected()) {
                if (!first) {
                    System.out.print(", ");
                }
                System.out.print("(" + edge.getSource().getInfo() + " -> " + edge.getDestination().getInfo() + ")");
                first = false;
            }
        }
        System.out.println("}");
    }

    public void displayAdjacencyList() {
        System.out.println("Lista de Adyacencias del Grafo Dirigido:");
        Map<V, List<V>> adjList = new LinkedHashMap<>();

        for (VertexObj<V, E> vertex : secVertex) {
            adjList.put(vertex.getInfo(), new ArrayList<>());
        }

        for (EdgeObj<V, E> edge : secEdge) {
            if (edge.isDirected()) {
                adjList.get(edge.getSource().getInfo()).add(edge.getDestination().getInfo());
            }
        }

        for (Map.Entry<V, List<V>> entry : adjList.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            if (entry.getValue().isEmpty()) {
                System.out.println("[]");
            } else {
                System.out.println(entry.getValue());
            }
        }
    }

    public void displayAdjacencyMatrix() {
        System.out.println("Matriz de Adyacencia del Grafo Dirigido:");
        int n = secVertex.size();
        int[][] matrix = new int[n][n];

        Map<V, Integer> vertexToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            vertexToIndex.put(secVertex.get(i).getInfo(), i);
        }

        for (EdgeObj<V, E> edge : secEdge) {
            if (edge.isDirected()) {
                int sourceIndex = vertexToIndex.get(edge.getSource().getInfo());
                int destIndex = vertexToIndex.get(edge.getDestination().getInfo());
                matrix[sourceIndex][destIndex] = 1;
            }
        }

        System.out.print("     ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%4s", secVertex.get(i).getInfo());
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.printf("%4s |", secVertex.get(i).getInfo());
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}