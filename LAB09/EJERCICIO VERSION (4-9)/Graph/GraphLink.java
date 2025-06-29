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
import java.util.List;
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
    public int gradoNodo(E data) {
        Vertex<E> v = searchVertex(data);
        if (v == null) return -1; 
        int grado = 0;
        ListLinked.Nodo<Edge<E>> actual = v.listAdj.getCabeza();
        while (actual != null) {
            grado++;
            actual = actual.siguiente;
        }
        return grado;
    }
    public boolean esCamino() {
        if (listVertex.esVacia()) return false;
        int gradoUno = 0;
        int gradoDos = 0;
        int total = listVertex.getSize();
        ListLinked.Nodo<Vertex<E>> actual = listVertex.getCabeza();
        while (actual != null) {
            int grado = gradoNodo(actual.dato.getData());

            if (grado == 1) gradoUno++;
            else if (grado == 2) gradoDos++;
            else return false;
            actual = actual.siguiente;
        }
        boolean estructuraCorrecta = (gradoUno == 2 && gradoDos == total - 2);
        return estructuraCorrecta && isConexo();
    }
    public boolean esCiclo() {
        if (listVertex.esVacia()) return false;

        ListLinked.Nodo<Vertex<E>> actual = listVertex.getCabeza();
        while (actual != null) {
            if (gradoNodo(actual.dato.getData()) != 2) return false;
            actual = actual.siguiente;
        }
        return isConexo();
    }
    public boolean esRueda() {
        int n = listVertex.getSize();
        if (n < 4) return false;
        Vertex<E> nodoCentro = null;
        int countCentro = 0;
        ListLinked.Nodo<Vertex<E>> actual = listVertex.getCabeza();
        while (actual != null) {
            int grado = gradoNodo(actual.dato.getData());
            if (grado == n - 1) {
                nodoCentro = actual.dato;
                countCentro++;
            } else if (grado != 3) {
                return false;
            }
            actual = actual.siguiente;
        }
        if (countCentro != 1) return false;
        GraphLink<E> subgrafo = new GraphLink<>();
        actual = listVertex.getCabeza();
        while (actual != null) {
            if (!actual.dato.equals(nodoCentro)) {
                subgrafo.insertVertex(actual.dato.getData());
            }
            actual = actual.siguiente;
        }
        actual = listVertex.getCabeza();
        while (actual != null) {
            if (!actual.dato.equals(nodoCentro)) {
                ListLinked.Nodo<Edge<E>> adyacentes = actual.dato.listAdj.getCabeza();
                while (adyacentes != null) {
                    if (!adyacentes.dato.getRefDest().equals(nodoCentro)) {
                        subgrafo.insertEdge(actual.dato.getData(), adyacentes.dato.getRefDest().getData());
                    }
                    adyacentes = adyacentes.siguiente;
                }
            }
            actual = actual.siguiente;
        }
        return subgrafo.esCiclo();
    }
    public boolean esCompleto() {
        int n = listVertex.getSize();
        if (n == 0) return false;
        ListLinked.Nodo<Vertex<E>> actual = listVertex.getCabeza();
        while (actual != null) {
            if (gradoNodo(actual.dato.getData()) != n - 1) {
                return false;
            }
            actual = actual.siguiente;
        }
        return true;
    }
    public void mostrarFormal() {
        System.out.println("Representación Formal del Grafo:");
        ListLinked.Nodo<Vertex<E>> actual = listVertex.getCabeza();
        while (actual != null) {
            System.out.print("Vértice " + actual.dato.getData() + " -> ");
            ListLinked.Nodo<Edge<E>> adyacente = actual.dato.listAdj.getCabeza();
            while (adyacente != null) {
                System.out.print(adyacente.dato.getRefDest().getData() + " ");
                adyacente = adyacente.siguiente;
            }
            System.out.println();
            actual = actual.siguiente;
        }
    }
    public void mostrarListaAdyacencia() {
        System.out.println("Lista de Adyacencias:");
        ListLinked.Nodo<Vertex<E>> actual = listVertex.getCabeza();
        while (actual != null) {
            System.out.print(actual.dato.getData() + ": ");
            ListLinked.Nodo<Edge<E>> adyacente = actual.dato.listAdj.getCabeza();
            while (adyacente != null) {
                System.out.print(adyacente.dato.getRefDest().getData() + " ");
                adyacente = adyacente.siguiente;
            }
            System.out.println();
            actual = actual.siguiente;
        }
    }
    public void mostrarMatrizAdyacencia() {
        System.out.println("Matriz de Adyacencia:");

        ArrayList<Vertex<E>> vertices = new ArrayList<>();
        ListLinked.Nodo<Vertex<E>> actual = listVertex.getCabeza();
        while (actual != null) {
            vertices.add(actual.dato);
            actual = actual.siguiente;
        }
        int n = vertices.size();
        int[][] matriz = new int[n][n];
        for (int i = 0; i < n; i++) {
            Vertex<E> vOrigen = vertices.get(i);
            ListLinked.Nodo<Edge<E>> adyacente = vOrigen.listAdj.getCabeza();
            while (adyacente != null) {
                Vertex<E> vDestino = adyacente.dato.getRefDest();
                int j = vertices.indexOf(vDestino);
                if (j != -1) {
                    matriz[i][j] = 1;
                }
                adyacente = adyacente.siguiente;
            }
        }

        System.out.print("     ");
        for (Vertex<E> v : vertices) {
            System.out.printf("%5s", v.getData());
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.printf("%5s", vertices.get(i).getData());
            for (int j = 0; j < n; j++) {
                System.out.printf("%5d", matriz[i][j]);
            }
            System.out.println();
        }
    }
    
    public int gradoEntrada(E data) {
        Vertex<E> v = searchVertex(data);
        if (v == null) return -1;

        int gradoEntrada = 0;
        ListLinked.Nodo<Vertex<E>> actual = listVertex.getCabeza();
        while (actual != null) {
            ListLinked.Nodo<Edge<E>> ady = actual.dato.listAdj.getCabeza();
            while (ady != null) {
                if (ady.dato.getRefDest().equals(v)) {
                    gradoEntrada++;
                }
                ady = ady.siguiente;
            }
            actual = actual.siguiente;
        }
        return gradoEntrada;
    }

    public int gradoSalida(E data) {
        Vertex<E> v = searchVertex(data);
        if (v == null) return -1;
        int gradoSalida = 0;
        ListLinked.Nodo<Edge<E>> actual = v.listAdj.getCabeza();
        while (actual != null) {
            gradoSalida++;
            actual = actual.siguiente;
        }
        return gradoSalida;
    }

    public boolean esCaminoDirigido() {
        if (listVertex.esVacia()) return false;
        
        int inicio = 0;   // nodos con gradoSalida=1 y gradoEntrada=0
        int fin = 0;      // nodos con gradoEntrada=1 y gradoSalida=0
        int intermedios = 0;
        int total = listVertex.getSize();

        ListLinked.Nodo<Vertex<E>> actual = listVertex.getCabeza();
        while (actual != null) {
            int in = gradoEntrada(actual.dato.getData());
            int out = gradoSalida(actual.dato.getData());

            if (in == 0 && out == 1) inicio++;
            else if (in == 1 && out == 0) fin++;
            else if (in == 1 && out == 1) intermedios++;
            else return false;  // No cumple con la estructura de camino dirigido

            actual = actual.siguiente;
        }

        return (inicio == 1 && fin == 1 && intermedios == total - 2);
    }

    public boolean esCicloDirigido() {
        if (listVertex.esVacia()) return false;

        ListLinked.Nodo<Vertex<E>> actual = listVertex.getCabeza();
        while (actual != null) {
            int in = gradoEntrada(actual.dato.getData());
            int out = gradoSalida(actual.dato.getData());

            if (in != 1 || out != 1) {
                return false;
            }
            actual = actual.siguiente;
        }
        return true;
    }

    public boolean esRuedaDirigida() {
        int n = listVertex.getSize();
        if (n < 4) return false;

        Vertex<E> centro = null;
        ListLinked.Nodo<Vertex<E>> actual = listVertex.getCabeza();
        while (actual != null) {
            int out = gradoSalida(actual.dato.getData());
            if (out == n - 1) {
                centro = actual.dato;
                break;
            }
            actual = actual.siguiente;
        }

        if (centro == null) return false;

        // Verificamos que los demás nodos formen un ciclo dirigido sin el centro
        int count = 0;
        actual = listVertex.getCabeza();
        while (actual != null) {
            if (!actual.dato.equals(centro)) {
                int in = gradoEntrada(actual.dato.getData());
                int out = gradoSalida(actual.dato.getData());

                if (in != 1 || out != 1) return false;
                count++;
            }
            actual = actual.siguiente;
        }

        return count == n - 1;
    }
    public boolean isIsomorphic(GraphLink<E> otherGraph) {
        if (this.listVertex.getSize() != otherGraph.listVertex.getSize()) {
            return false;
        }

        int thisEdgeCount = 0;
        ListLinked.Nodo<Vertex<E>> currentThisVertex = this.listVertex.getCabeza();
        while (currentThisVertex != null) {
            thisEdgeCount += currentThisVertex.dato.listAdj.getSize();
            currentThisVertex = currentThisVertex.siguiente;
        }

        int otherEdgeCount = 0;
        ListLinked.Nodo<Vertex<E>> currentOtherVertex = otherGraph.listVertex.getCabeza();
        while (currentOtherVertex != null) {
            otherEdgeCount += currentOtherVertex.dato.listAdj.getSize();
            currentOtherVertex = currentOtherVertex.siguiente;
        }

        if (thisEdgeCount != otherEdgeCount) {
            return false;
        }

        List<Integer> thisInDegrees = new ArrayList<>();
        currentThisVertex = this.listVertex.getCabeza();
        while (currentThisVertex != null) {
            thisInDegrees.add(this.gradoEntrada(currentThisVertex.dato.getData()));
            currentThisVertex = currentThisVertex.siguiente;
        }
        Collections.sort(thisInDegrees);

        List<Integer> otherInDegrees = new ArrayList<>();
        currentOtherVertex = otherGraph.listVertex.getCabeza();
        while (currentOtherVertex != null) {
            otherInDegrees.add(otherGraph.gradoEntrada(currentOtherVertex.dato.getData()));
            currentOtherVertex = currentOtherVertex.siguiente;
        }
        Collections.sort(otherInDegrees);

        if (!thisInDegrees.equals(otherInDegrees)) {
            return false;
        }

        List<Integer> thisOutDegrees = new ArrayList<>();
        currentThisVertex = this.listVertex.getCabeza();
        while (currentThisVertex != null) {
            thisOutDegrees.add(this.gradoSalida(currentThisVertex.dato.getData()));
            currentThisVertex = currentThisVertex.siguiente;
        }
        Collections.sort(thisOutDegrees);

        List<Integer> otherOutDegrees = new ArrayList<>();
        currentOtherVertex = otherGraph.listVertex.getCabeza();
        while (currentOtherVertex != null) {
            otherOutDegrees.add(otherGraph.gradoSalida(currentOtherVertex.dato.getData()));
            currentOtherVertex = currentOtherVertex.siguiente;
        }
        Collections.sort(otherOutDegrees);

        if (!thisOutDegrees.equals(otherOutDegrees)) {
            return false;
        }
        return true;
    }

    public boolean isPlanar() {
        if (this.listVertex.getSize() <= 4) {
             return true;
        }
        System.out.println("ADVERTENCIA: La verificación de planaridad general es un problema complejo. Esta función solo proporciona una verificación básica para grafos muy pequeños o una explicación conceptual.");
        return false;
    }

    private Set<Vertex<E>> getAllNeighborsForWeakCheck(Vertex<E> currentVertex) {
        Set<Vertex<E>> neighbors = new HashSet<>();

        ListLinked.Nodo<Edge<E>> outgoing = currentVertex.listAdj.getCabeza();
        while (outgoing != null) {
            neighbors.add(outgoing.dato.getRefDest());
            outgoing = outgoing.siguiente;
        }

        ListLinked.Nodo<Vertex<E>> vNode = listVertex.getCabeza();
        while (vNode != null) {
            Vertex<E> v = vNode.dato;
            if (!v.equals(currentVertex)) {
                ListLinked.Nodo<Edge<E>> edgeNode = v.listAdj.getCabeza();
                while (edgeNode != null) {
                    if (edgeNode.dato.getRefDest().equals(currentVertex)) {
                        neighbors.add(v);
                        break;
                    }
                    edgeNode = edgeNode.siguiente;
                }
            }
            vNode = vNode.siguiente;
        }
        return neighbors;
    }

    private void dfsWeaklyConnected(Vertex<E> vertex, Set<Vertex<E>> visited) {
        visited.add(vertex);
        for (Vertex<E> neighbor : getAllNeighborsForWeakCheck(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsWeaklyConnected(neighbor, visited);
            }
        }
    }

    public boolean isWeaklyConnected() {
        if (listVertex.esVacia()) {
            return true;
        }

        Vertex<E> startVertex = listVertex.getCabeza().dato;
        Set<Vertex<E>> visited = new HashSet<>();
        dfsWeaklyConnected(startVertex, visited);

        return visited.size() == listVertex.getSize();
    }

    public GraphLink<E> getComplement() {
        GraphLink<E> complementGraph = new GraphLink<>();

        ListLinked.Nodo<Vertex<E>> currentVertexNode = this.listVertex.getCabeza();
        while (currentVertexNode != null) {
            complementGraph.insertVertex(currentVertexNode.dato.getData());
            currentVertexNode = currentVertexNode.siguiente;
        }

        ListLinked.Nodo<Vertex<E>> uNode = this.listVertex.getCabeza();
        while (uNode != null) {
            Vertex<E> u = uNode.dato;
            ListLinked.Nodo<Vertex<E>> vNode = this.listVertex.getCabeza();
            while (vNode != null) {
                Vertex<E> v = vNode.dato;

                if (!u.equals(v)) {
                    if (!this.searchEdge(u.getData(), v.getData())) {
                        complementGraph.insertEdge(u.getData(), v.getData());
                    }
                }
                vNode = vNode.siguiente;
            }
            uNode = uNode.siguiente;
        }
        return complementGraph;
    }

    public boolean isSelfComplementary() {
        int numVertices = this.listVertex.getSize();
        if (numVertices == 0) return true;

        int totalPossibleEdges = numVertices * (numVertices - 1);
        if (totalPossibleEdges % 2 != 0) {
            return false;
        }

        GraphLink<E> complementGraph = this.getComplement();

        return this.isIsomorphic(complementGraph);
    }
    
    
    
    @Override
    public String toString() {
        return listVertex.toString();
    }
}