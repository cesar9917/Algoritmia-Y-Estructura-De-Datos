package Actividades;

import Graph.GraphLink;
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

        System.out.println("--- Grafo Principal (Ponderado) ---");
        System.out.println(grafo);

        // Ejercicio 8
        System.out.println("\n--- Análisis de Propiedades del Grafo Principal ---");
        System.out.println("Grado de Entrada de A: " + grafo.gradoEntrada("A"));
        System.out.println("Grado de Salida de F: " + grafo.gradoSalida("F"));

        System.out.println("Es camino dirigido? " + grafo.esCaminoDirigido());
        System.out.println("Es ciclo dirigido? " + grafo.esCicloDirigido());
        System.out.println("Es rueda dirigida? " + grafo.esRuedaDirigida());

        probarPropiedades(grafo);

        System.out.print("\nBFS recorrido desde A: ");
        grafo.bfs("A");
        System.out.println("Camino BFS de A - F: " + grafo.bfsPath("A", "F"));

        System.out.println("\n--- Prueba de Conectividad ---");
        System.out.println("¿Es el grafo débilmente conexo? " + grafo.isWeaklyConnected());

        System.out.println("\n--- Cálculo de Ruta Más Corta (Dijkstra) ---");
        ArrayList<String> shortestPathAF = grafo.shortPath("A", "F");
        if (!shortestPathAF.isEmpty()) {
            System.out.println("Ruta más corta de A a F: " + shortestPathAF);
        } else {
            System.out.println("No se encontró ruta de A a F.");
        }

        System.out.println("Ruta más corta de B a F: " + grafo.shortPath("B", "F"));
        System.out.println("Ruta más corta de A a A: " + grafo.shortPath("A", "A"));
        System.out.println("Camino de A a Z: " + grafo.bfsPath("A", "Z"));

        System.out.println("\n--- Prueba directa de Dijkstra (retorna Stack, F a A) ---");
        Stack<String> dijkstraStackAF = grafo.Dijkstra("A", "F");
        if (!dijkstraStackAF.isEmpty()) {
            System.out.print("Ruta de A a F (en Stack, de F a A): ");
            while (!dijkstraStackAF.isEmpty()) {
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
        System.out.println("Ruta más corta de A a F: " + grafo.shortPath("A", "F"));

        System.out.println("Eliminando vértice C: " + grafo.removeVertex("C"));
        System.out.println("Grafo después de eliminar C:");
        System.out.println(grafo);

        System.out.print("BFS recorrido desde A después de eliminar C: ");
        grafo.bfs("A");

        System.out.println("Ruta más corta de A a F después de eliminar C:");
        System.out.println("Ruta más corta de A a F: " + grafo.shortPath("A", "F"));

        // Ejercicio 9
        System.out.println("\n--- EJERCICIO 9: Propiedades Avanzadas de Grafos ---");

        System.out.println("\n--- 9a) Isomorfismo ---");
        GraphLink<String> grafoA = new GraphLink<>();
        grafoA.insertVertex("1");
        grafoA.insertVertex("2");
        grafoA.insertVertex("3");
        grafoA.insertEdge("1", "2");
        grafoA.insertEdge("2", "3");
        grafoA.insertEdge("3", "1");

        GraphLink<String> grafoB = new GraphLink<>();
        grafoB.insertVertex("X");
        grafoB.insertVertex("Y");
        grafoB.insertVertex("Z");
        grafoB.insertEdge("X", "Y");
        grafoB.insertEdge("Y", "Z");
        grafoB.insertEdge("Z", "X");

        GraphLink<String> grafoC = new GraphLink<>();
        grafoC.insertVertex("P");
        grafoC.insertVertex("Q");
        grafoC.insertVertex("R");
        grafoC.insertVertex("S");

        GraphLink<String> grafoD = new GraphLink<>();
        grafoD.insertVertex("M");
        grafoD.insertVertex("N");
        grafoD.insertVertex("O");
        grafoD.insertEdge("M", "N");
        grafoD.insertEdge("N", "O");

        System.out.println("Grafo A (Ciclo 1-2-3): " + grafoA);
        System.out.println("Grafo B (Ciclo X-Y-Z): " + grafoB);
        System.out.println("Grafo C (4 Vértices): " + grafoC);
        System.out.println("Grafo D (Camino M-N-O): " + grafoD);

        System.out.println("¿Grafo A es isomorfo a Grafo B? " + grafoA.isIsomorphic(grafoB));
        System.out.println("¿Grafo A es isomorfo a Grafo C? " + grafoA.isIsomorphic(grafoC));
        System.out.println("¿Grafo A es isomorfo a Grafo D? " + grafoA.isIsomorphic(grafoD));

        System.out.println("\n--- 9b) Planaridad ---");
        System.out.println("¿Grafo principal es plano? " + grafo.isPlanar());
        System.out.println("¿Grafo A es plano? " + grafoA.isPlanar());
        GraphLink<String> k5 = new GraphLink<>();
        k5.insertVertex("v1"); k5.insertVertex("v2"); k5.insertVertex("v3"); k5.insertVertex("v4"); k5.insertVertex("v5");
        k5.insertEdge("v1", "v2"); k5.insertEdge("v1", "v3"); k5.insertEdge("v1", "v4"); k5.insertEdge("v1", "v5");
        k5.insertEdge("v2", "v1"); k5.insertEdge("v2", "v3"); k5.insertEdge("v2", "v4"); k5.insertEdge("v2", "v5");
        k5.insertEdge("v3", "v1"); k5.insertEdge("v3", "v2"); k5.insertEdge("v3", "v4"); k5.insertEdge("v3", "v5");
        k5.insertEdge("v4", "v1"); k5.insertEdge("v4", "v2"); k5.insertEdge("v4", "v3"); k5.insertEdge("v4", "v5");
        k5.insertEdge("v5", "v1"); k5.insertEdge("v5", "v2"); k5.insertEdge("v5", "v3"); k5.insertEdge("v5", "v4");
        System.out.println("¿Grafo K5 (simulado dirigido) es plano? " + k5.isPlanar());

        System.out.println("\n--- 9d) Auto-Complementario ---");
        GraphLink<String> scGraph = new GraphLink<>();
        scGraph.insertVertex("N1");
        scGraph.insertVertex("N2");
        scGraph.insertVertex("N3");
        scGraph.insertVertex("N4");
        scGraph.insertEdge("N1", "N2");
        scGraph.insertEdge("N2", "N3");
        scGraph.insertEdge("N3", "N4");
        scGraph.insertEdge("N4", "N1");
        scGraph.insertEdge("N1", "N3");

        System.out.println("Grafo SC (4 vértices): " + scGraph);
        GraphLink<String> complementSC = scGraph.getComplement();
        System.out.println("Complemento de SC: " + complementSC);
        System.out.println("¿Grafo SC es auto-complementario? " + scGraph.isSelfComplementary());

        GraphLink<String> notScGraph = new GraphLink<>();
        notScGraph.insertVertex("P1");
        notScGraph.insertVertex("P2");
        notScGraph.insertVertex("P3");
        notScGraph.insertEdge("P1", "P2");
        System.out.println("\nGrafo No-SC (3 vértices, 1 arista): " + notScGraph);
        GraphLink<String> complementNotSC = notScGraph.getComplement();
        System.out.println("Complemento de No-SC: " + complementNotSC);
        System.out.println("¿Grafo No-SC es auto-complementario? " + notScGraph.isSelfComplementary());
    }

    public static void probarPropiedades(GraphLink<String> grafo) {
        String nodo = "A";
        int grado = grafo.gradoNodo(nodo);
        System.out.println("Grado (total) del nodo " + nodo + ": " + grado);

        boolean hayCamino = grafo.esCamino();
        System.out.println("¿Es camino (no dirigido)? " + hayCamino);

        boolean tieneCiclo = grafo.esCiclo();
        System.out.println("¿El grafo tiene ciclo (no dirigido)? " + tieneCiclo);

        boolean esRueda = grafo.esRueda();
        System.out.println("¿El grafo es rueda (no dirigido)? " + esRueda);

        boolean esCompleto = grafo.esCompleto();
        System.out.println("¿El grafo es completo (no dirigido)? " + esCompleto);
    }
}