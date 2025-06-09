package Actividades;
import Graph.*;

import java.util.ArrayList;
import java.util.Stack;

public class TADGraph {
    public static void main(String[] args) {
        GraphLink<String> grafo = new GraphLink<>();

        grafo.insertVertex("A");
        grafo.insertVertex("B");
        grafo.insertVertex("C");
        grafo.insertVertex("D");
        grafo.insertVertex("E");
        grafo.insertVertex("F");

        grafo.insertEdgeWeight("A", "B", 4);
        grafo.insertEdgeWeight("A", "C", 2);
        grafo.insertEdgeWeight("B", "E", 3);
        grafo.insertEdgeWeight("C", "D", 2);
        grafo.insertEdgeWeight("D", "E", 3);
        grafo.insertEdgeWeight("E", "F", 1);

        System.out.println("--- Grafo Ponderado ---");
        System.out.println(grafo);
        grafo.bfs("A");
        System.out.print("Grafo de A - F: "+grafo.bfsPath("A", "F"));
        
        System.out.println("\n--- Prueba de Conectividad ---");
        System.out.println("¿Es el grafo conexo? " + grafo.isConexo());

        System.out.println("\n--- Cálculo de Ruta Más Corta (shortPath) ---");

        ArrayList<String> shortestPathAF = grafo.shortPath("A", "F");
        if (!shortestPathAF.isEmpty()) {
            System.out.println("Ruta más corta de A a F: " + shortestPathAF);
        } else {
            System.out.println("No se encontró ruta de A a F.");
        }

        ArrayList<String> shortestPathBF = grafo.shortPath("B", "F");
        if (!shortestPathBF.isEmpty()) {
            System.out.println("Ruta más corta de B a F: " + shortestPathBF);
        } else {
            System.out.println("No se encontró ruta de B a F.");
        }

        ArrayList<String> shortestPathAA = grafo.shortPath("A", "A");
        if (!shortestPathAA.isEmpty()) {
            System.out.println("Ruta más corta de A a A: " + shortestPathAA);
        } else {
            System.out.println("No se encontró ruta de A a A.");
        }

        ArrayList<String> shortestPathAZ = grafo.bfsPath("A", "Z");
        if (!shortestPathAZ.isEmpty()) {
            System.out.println("Camino de A a Z: " + shortestPathAZ);
        } else {
            System.out.println("No se encontró ruta de A a Z.");
        }

        System.out.println("\n--- Prueba directa de Dijkstra (retorna Stack, F a A) ---");
        Stack<String> dijkstraStackAF = grafo.Dijkstra("A", "F");
        if (!dijkstraStackAF.isEmpty()) {
            System.out.print("Ruta de A a F (en Stack, de F a A): ");
            while(!dijkstraStackAF.isEmpty()){
                System.out.print(dijkstraStackAF.pop() + " ");
            }
            System.out.println();
        } else {
            System.out.println("No se encontró ruta de A a F (Stack vacío).");
        }

        System.out.println("\n--- Pruebas de Métodos Anteriores ---");
        System.out.print("DFS recorrido desde A: ");
        grafo.dfs("A");
        System.out.print("BFS recorrido desde A: ");
        grafo.bfs("A");

        System.out.println("Eliminando arista B -> E: " + grafo.removeEdge("B", "E"));
        System.out.println("Grafo después de eliminar B -> E:");
        System.out.println(grafo);

        System.out.println("\n--- Cálculo de Ruta Más Corta (shortPath) después de eliminar B-E ---");
        shortestPathAF = grafo.shortPath("A", "F"); 
        if (!shortestPathAF.isEmpty()) {
            System.out.println("Ruta más corta de A a F: " + shortestPathAF); 
        } else {
            System.out.println("No se encontró ruta de A a F.");
        }

        System.out.println("Eliminando vértice C: " + grafo.removeVertex("C"));
        System.out.println("Grafo después de eliminar C:");
        System.out.println(grafo);

        System.out.print("BFS recorrido desde A después de eliminar C: ");
        grafo.bfs("A");

        System.out.println("Ruta más corta de A a F después de eliminar C:");
        ArrayList<String> shortestPathAF_after_C_removal = grafo.shortPath("A", "F");
        if (!shortestPathAF_after_C_removal.isEmpty()) {
            System.out.println("Ruta más corta de A a F: " + shortestPathAF_after_C_removal);
        } else {
            System.out.println("No se encontró ruta de A a F.");
        }
    }
}