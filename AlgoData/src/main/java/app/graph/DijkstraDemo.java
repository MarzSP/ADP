package app.graph;

import java.util.Arrays;

/**
 * Demo voor Graph + Dijkstra
 * - Bouwt een kleine gerichte, gewogen graaf
 * - Draait Dijkstra
 * - Voegt een edge toe
 * - Verwijdert een vertex
 * - Draait Dijkstra opnieuw
 * Output:
 * Dijkstra vanaf A (index 0):
 * A: 0.0
 * B: 1.0
 * C: 3.0
 * D: 6.0
 * Dijkstra runtime: 800 ns (0,001 ms)
 *
 * Na toevoegen van edge B -> D (1):
 * A: 0.0
 * B: 1.0
 * C: 3.0
 * D: 2.0
 * Dijkstra runtime: 600 ns (0,001 ms)
 *
 * Na verwijderen van vertex C:
 * Afstanden vanaf index 0 (A): [0.0, 1.0, 2.0]
 * Dijkstra runtime: 300 ns (0,001 ms)
 */
public class DijkstraDemo {

    public static void main(String[] args) {
        Graph<String> g = new Graph<>();

        Vertex<String> A = new Vertex<>("A");
        Vertex<String> B = new Vertex<>("B");
        Vertex<String> C = new Vertex<>("C");
        Vertex<String> D = new Vertex<>("D");

        // Omdat dijkstraFromIndex(int) werkt met indices, voeg vertices in een vaste volgorde toe:
        // index 0 = A, 1 = B, 2 = C, 3 = D
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);

        /*
         * AdjacencyList (na alleen vertices):
         * 0: A -> []
         * 1: B -> []
         * 2: C -> []
         * 3: D -> []
         */

        //Edges toevoegen (gericht + gewogen)
        g.addEdge(A, B, 1);   // A -> B (1)
        g.addEdge(A, C, 10);  // A -> C (10)
        g.addEdge(B, C, 2);   // B -> C (2)
        g.addEdge(C, D, 3);   // C -> D (3)

        /*
         * AdjacencyList (na edges):
         * 0: A -> (B,1), (C,10)
         * 1: B -> (C,2)
         * 2: C -> (D,3)
         * 3: D -> []
         */

        //Dijkstra vanaf A (index 0)
        System.out.println("Dijkstra vanaf A (index 0):");

        // PERFORMANCE METING START
        // Warm-up
        for (int i = 0; i < 5_000; i++) {
            g.dijkstraFromIndex(0);
        }

        long startTime = System.nanoTime();
        double[] distances = g.dijkstraFromIndex(0);
        long endTime = System.nanoTime();

        long durationNs = endTime - startTime;
        //PERFORMANCE METING EIND

        printDistances(new String[]{"A", "B", "C", "D"}, distances);
        System.out.printf("Dijkstra runtime: %,d ns (%.3f ms)%n",
                durationNs, durationNs / 1_000_000.0);

        // Verwacht:
        // A: 0
        // B: 1
        // C: 3  (via A->B->C = 1+2, korter dan 10)
        // D: 6  (via A->B->C->D = 1+2+3)

        // Iets toevoegen: extra edge die een kortere route kan maken
        g.addEdge(B, D, 1); // B -> D (1)

        /*
         * AdjacencyList (na extra edge):
         * 0: A -> (B,1), (C,10)
         * 1: B -> (C,2), (D,1)
         * 2: C -> (D,3)
         * 3: D -> []
         */

        System.out.println("\nNa toevoegen van edge B -> D (1):");

        //PERFORMANCE METING START
        for (int i = 0; i < 5_000; i++) {
            g.dijkstraFromIndex(0);
        }

        startTime = System.nanoTime();
        distances = g.dijkstraFromIndex(0);
        endTime = System.nanoTime();
        durationNs = endTime - startTime;
        // PERFORMANCE METING EIND

        printDistances(new String[]{"A", "B", "C", "D"}, distances);
        System.out.printf("Dijkstra runtime: %,d ns (%.3f ms)%n",
                durationNs, durationNs / 1_000_000.0);

        // Verwacht:
        // D wordt nu 2 (A->B->D = 1+1) i.p.v. 6

        // Iets verwijderen: verwijder vertex C
        g.removeVertex(C);

        /*
         * Let op: removeVertex(C) verwijdert:
         * - Vertex C zelf (entry verdwijnt uit adjacencyList)
         * - Alle edges die naar C wijzen (A->C en B->C verdwijnen)
         *
         * De indices kunnen hierdoor verschuiven. Het zijn posities op dat moment.
         * Oorspronkelijk: 0 A, 1 B, 2 C, 3 D
         * Na verwijderen van C: adjacencyList bevat alleen A, B, D (in die volgorde)
         *
         * Nieuwe adjacency list:
         * 0: A -> (B,1)          (A->C is weg)
         * 1: B -> (D,1)          (B->C is weg)
         * 2: D -> []
         */

        System.out.println("\nNa verwijderen van vertex C:");

        //PERFORMANCE METING START
        for (int i = 0; i < 5_000; i++) {
            g.dijkstraFromIndex(0);
        }

        startTime = System.nanoTime();
        double[] distAfterRemove = g.dijkstraFromIndex(0);
        endTime = System.nanoTime();
        durationNs = endTime - startTime;
        //PERFORMANCE METING EIND

        System.out.println("Afstanden vanaf index 0 (A): " + Arrays.toString(distAfterRemove));
        System.out.printf("Dijkstra runtime: %,d ns (%.3f ms)%n",
                durationNs, durationNs / 1_000_000.0);

        // Verwacht:
        // A: 0
        // B: 1
        // D: 2 (A->B->D)
        // In array-vorm: [0.0, 1.0, 2.0]
    }

    private static void printDistances(String[] labels, double[] dist) {
        for (int i = 0; i < dist.length; i++) {
            String name = (i < labels.length) ? labels[i] : ("index " + i);
            if (Double.isInfinite(dist[i])) {
                System.out.println(name + ": Infinity");
            } else {
                System.out.println(name + ": " + dist[i]);
            }
        }
    }
}
