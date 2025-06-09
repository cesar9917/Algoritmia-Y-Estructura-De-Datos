package Actividades;
import Graph.*;

import Graph.GraphLink;

public class TADGraph {
    public static void main(String[] args) {
        GraphLink<String> grafo = new GraphLink<>();

        grafo.insertVertex("A");
        grafo.insertVertex("B");
        grafo.insertVertex("C");

        grafo.insertEdge("A", "B");
        grafo.insertEdge("B", "C");

        System.out.println("Grafo:");
        System.out.println(grafo);

        System.out.println("¿Existe vértice A? " + grafo.searchVertex("A"));
        System.out.println("¿Existe vértice D? " + grafo.searchVertex("D"));

        System.out.println("¿Existe arista A -> B? " + grafo.searchEdge("A", "B"));
        System.out.println("¿Existe arista A -> C? " + grafo.searchEdge("A", "C"));

        System.out.print("DFS recorrido: ");
        grafo.dfs("A");
        System.out.println();

        grafo.removeEdge("B", "C");
        System.out.println("Grafo después de eliminar arista B -> C:");
        System.out.println(grafo);

        grafo.removeVertex("B");
        System.out.println("Grafo después de eliminar vértice B:");
        System.out.println(grafo);
    }
}
