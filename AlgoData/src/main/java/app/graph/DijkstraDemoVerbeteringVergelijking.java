package app.graph;

import java.util.Random;

/**
 * Performance demo:
 * - Builds the same random graph twice (reproducible with seed)
 * - Runs Dijkstra many times
 * - Compares original Graph vs GraphVerbetering (PriorityQueue)
 * Output:
 * Graph built:
 * - vertices = 500
 * - edges per vertex = 2
 * - warmup runs = 100
 * - measure runs = 100
 *
 * Original Graph (lineair closest search)
 * Total: 3236,26 ms
 * Average per run: 32362,63 µs (32362625 ns)
 *
 * GraphVerbetering (PriorityQueue)
 * Total: 3217,73 ms
 * Average per run: 32177,25 µs (32177250 ns)
 */
public class DijkstraDemoVerbeteringVergelijking {

    private static final int VERTICES = 500;
    private static final int EDGES_PER_VERTEX = 2;
    private static final int WARMUP_RUNS = 100;
    private static final int MEASURE_RUNS = 100;
    private static final long SEED = 42L; // L is voor long literal aka big number

    public static void main(String[] args) {

        // Maak Graph
        Graph<Integer> gOriginal = new Graph<>();
        buildSameGraph(gOriginal);

        // Maak GraphVerbetering
        GraphVerbetering<Integer> gImproved = new GraphVerbetering<>();
        buildSameGraph(gImproved);

        System.out.println("Graph built:");
        System.out.println("- vertices = " + VERTICES);
        System.out.println("- edges per vertex = " + EDGES_PER_VERTEX);
        System.out.println("- warmup runs = " + WARMUP_RUNS);
        System.out.println("- measure runs = " + MEASURE_RUNS);
        System.out.println();

        // Warm-up
        warmUpOriginal(gOriginal);
        warmUpImproved(gImproved);

        // Meten is weten
        measureOriginal("Original Graph (lineair closest search)", gOriginal);
        measureImproved("GraphVerbetering (PriorityQueue)", gImproved);
    }

    /**
     * Helper overload for your original Graph class
     */
    private static void buildSameGraph(Graph<Integer> g) {
        Random rnd = new Random(SEED);

        // Voeg vertices toe in vaste volgorde
        for (int i = 0; i < VERTICES; i++) {
            g.addVertex(new Vertex<>(i));
        }

        // Voeg edges toe
        for (int from = 0; from < VERTICES; from++) {
            Vertex<Integer> fromV = new Vertex<>(from);

            for (int e = 0; e < EDGES_PER_VERTEX; e++) {
                int to = rnd.nextInt(VERTICES);
                int weight = rnd.nextInt(10) + 1; // 1..10
                g.addEdge(fromV, new Vertex<>(to), weight);
            }
        }
    }

    /**
     * Helper overload for GraphVerbetering class
     */
    private static void buildSameGraph(GraphVerbetering<Integer> g) {
        Random rnd = new Random(SEED);

        for (int i = 0; i < VERTICES; i++) {
            g.addVertex(new Vertex<>(i));
        }

        for (int from = 0; from < VERTICES; from++) {
            Vertex<Integer> fromV = new Vertex<>(from);

            for (int e = 0; e < EDGES_PER_VERTEX; e++) {
                int to = rnd.nextInt(VERTICES);
                int weight = rnd.nextInt(10) + 1;
                g.addEdge(fromV, new Vertex<>(to), weight);
            }
        }
    }

    private static void warmUpOriginal(Graph<Integer> g) {
        for (int i = 0; i < WARMUP_RUNS; i++) {
            g.dijkstraFromIndex(0);
        }
    }

    private static void warmUpImproved(GraphVerbetering<Integer> g) {
        for (int i = 0; i < WARMUP_RUNS; i++) {
            g.dijkstraFromIndex(0);
        }
    }

    private static void measureOriginal(String label, Graph<Integer> g) {
        long start = System.nanoTime();
        double[] last = null;

        for (int i = 0; i < MEASURE_RUNS; i++) {
            last = g.dijkstraFromIndex(0);
        }

        long end = System.nanoTime();
        printTiming(label, start, end);

        // Gebruikt result 1x om optimalisaties te voorkomen
        if (last != null) System.out.println("Checksum-ish: " + last[0]);
        System.out.println();
    }

    private static void measureImproved(String label, GraphVerbetering<Integer> g) {
        long start = System.nanoTime();
        double[] last = null;

        for (int i = 0; i < MEASURE_RUNS; i++) {
            last = g.dijkstraFromIndex(0);
        }

        long end = System.nanoTime();
        printTiming(label, start, end);

        if (last != null) System.out.println("Checksum-ish: " + last[0]);
        System.out.println();
    }

    private static void printTiming(String label, long start, long end) {
        long totalNs = end - start;
        double avgNs = (double) totalNs / MEASURE_RUNS;
        double avgUs = avgNs / 1_000.0;
        double totalMs = totalNs / 1_000_000.0;

        System.out.println(label);
        System.out.printf("Total: %.2f ms%n", totalMs);
        System.out.printf("Average per run: %.2f µs (%.0f ns)%n", avgUs, avgNs);
    }
}
