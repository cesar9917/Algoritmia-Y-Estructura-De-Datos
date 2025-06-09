package JGraphT;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");

        System.out.println("¿Existe el vértice 'A'? " + graph.containsVertex("A"));

        System.out.println("¿Existe la arista entre 'A' y 'D'? " + graph.containsEdge("A", "D"));
        System.out.println("¿Existe la arista entre 'A' y 'B'? " + graph.containsEdge("A", "B"));

        System.out.print("Vecinos de 'D': ");
        for (DefaultEdge e : graph.edgesOf("D")) {
            String source = graph.getEdgeSource(e);
            String target = graph.getEdgeTarget(e);
            String vecino = source.equals("D") ? target : source;
            System.out.print(vecino + " ");
        }
        System.out.println();

        System.out.println("Grado de 'D': " + graph.degreeOf("D"));
        System.out.print("Recorrido BFS desde A: ");
        Iterator<String> bfsIterator = new BreadthFirstIterator<>(graph, "A");
        while (bfsIterator.hasNext()) {
            System.out.print(bfsIterator.next() + " ");
        }
        System.out.println();
    }
}
