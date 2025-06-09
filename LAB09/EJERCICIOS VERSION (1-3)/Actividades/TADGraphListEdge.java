package Actividades;

import Graph.GraphListEdge;

public class TADGraphListEdge {
    public static void main(String[] args) {
        GraphListEdge<String, String> grafo = new GraphListEdge<>();

        System.out.println("Insertando vértices A, B, C, D:");
        grafo.insertVertex("A");
        grafo.insertVertex("B");
        grafo.insertVertex("C");
        grafo.insertVertex("D");

        System.out.println("Insertando vértice A nuevamente (no debe insertarse):");
        grafo.insertVertex("A");

        System.out.println("Insertando aristas:");
        grafo.insertEdge("A", "B");
        grafo.insertEdge("A", "C");
        grafo.insertEdge("B", "D");
        grafo.insertEdge("C", "D");

        System.out.println("Insertando arista A-B nuevamente (no debe insertarse):");
        grafo.insertEdge("A", "B");

        System.out.println("¿Existe el vértice A? " + grafo.searchVertex("A"));
        System.out.println("¿Existe el vértice X? " + grafo.searchVertex("X"));

        System.out.println("¿Existe la arista A-C? " + grafo.searchEdge("A", "C"));
        System.out.println("¿Existe la arista B-C? " + grafo.searchEdge("B", "C"));

        System.out.println("Recorrido BFS desde A:");
        grafo.bfs("A");

        System.out.println("Recorrido BFS desde B:");
        grafo.bfs("B");

        System.out.println("Recorrido BFS desde nodo no existente Z:");
        grafo.bfs("Z");
    }
}
