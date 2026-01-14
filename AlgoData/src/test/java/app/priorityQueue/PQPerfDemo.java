package app.priorityQueue;

import java.util.Random;

/**
 * PriorityQueue performance
 * Lower priority number = higher priority
 * n = aantal elementen in de priority queue
 * n=100
 *   insert:    236 ns/op
 *   findMin:   13,772 ns/op
 *   removeMin: 72 ns/op
 *
 * n=500
 *   insert:    215 ns/op
 *   findMin:   10,250 ns/op
 *   removeMin: 39 ns/op
 *
 * n=1000
 *   insert:    371 ns/op
 *   findMin:   0,002 ns/op
 *   removeMin: 36 ns/op
 *
 * n=2000
 *   insert:    1081 ns/op
 *   findMin:   0,007 ns/op
 *   removeMin: 45 ns/op
 *
 * n=5000
 *   insert:    3412 ns/op
 *   findMin:   0,021 ns/op
 *   removeMin: 7 ns/op
 */
public class PQPerfDemo {

    private static final long SEED = 42L;

    private static final int WARMUP_RUNS = 5;
    private static final int MEASURE_RUNS = 10;
    private static final int[] SIZES = {100, 500, 1000, 2000, 5000}; //aantal elementen in de priority queue
// Start de performance-demo en meet insert, findMin en removeMin voor verschillende groottes
    public static void main(String[] args) {

        System.out.println("PriorityQueue performance");
        System.out.println("Lower priority number = higher priority");
        System.out.println();

        for (int n : SIZES) {
            long insertNsPerOp = measureInsert(n);
            double findMinNsPerOp = measureFindMin(n);
            long removeMinNsPerOp = measureRemoveMin(n);

            System.out.printf("n=%d%n", n);
            System.out.printf("  insert:    %d ns/op%n", insertNsPerOp);
            System.out.printf("  findMin:   %.3f ns/op%n", findMinNsPerOp);
            System.out.printf("  removeMin: %d ns/op%n", removeMinNsPerOp);
            System.out.println();
        }
    }
// Meet de gemiddelde tijd per insert-operatie voor een priority queue met n elementen
    private static long measureInsert(int n) {
        for (int i = 0; i < WARMUP_RUNS; i++) {
            runInsertOnce(n, SEED + i);
        }

        long totalNs = 0;
        for (int i = 0; i < MEASURE_RUNS; i++) {
            totalNs += runInsertOnce(n, SEED + 1000L + i);
        }
        return totalNs / (long) (MEASURE_RUNS * n);
    }
// Voert n insert-operaties uit en meet de totale uitvoeringstijd
    private static long runInsertOnce(int n, long seed) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Random r = new Random(seed);

        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            pq.insert(i, r.nextInt());
        }
        long end = System.nanoTime();

        pq.findMin();

        return end - start;
    }
// Meet de gemiddelde tijd per findMin-operatie door findMin meerdere keren aan te roepen
    private static double measureFindMin(int n) {
        final int reps = 10_000;

        for (int i = 0; i < WARMUP_RUNS; i++) {
            runFindMinOnce(n, reps, SEED + i);
        }

        long totalNs = 0;
        for (int i = 0; i < MEASURE_RUNS; i++) {
            totalNs += runFindMinOnce(n, reps, SEED + 2000L + i);
        }

        return (double) totalNs / (MEASURE_RUNS * (double) reps);
    }
// Roept findMin reps keer aan op een gevulde priority queue en meet de totale tijd
    private static long runFindMinOnce(int n, int reps, long seed) {
        PriorityQueue<Integer> pq = buildPQ(n, seed);

        long start = System.nanoTime();
        int sink = 0;
        for (int i = 0; i < reps; i++) {
            sink ^= pq.findMin();
        }
        long end = System.nanoTime();

        if (sink == 42) System.out.print("");
        return end - start;
    }
// Meet de gemiddelde tijd per removeMin-operatie door alle elementen uit de queue te verwijderen
    private static long measureRemoveMin(int n) {
        for (int i = 0; i < WARMUP_RUNS; i++) {
            runRemoveMinOnce(n, SEED + i);
        }

        long totalNs = 0;
        for (int i = 0; i < MEASURE_RUNS; i++) {
            totalNs += runRemoveMinOnce(n, SEED + 3000L + i);
        }
        return totalNs / (long) (MEASURE_RUNS * n);
    }
// Verwijdert n keer het element met de hoogste prioriteit en meet de totale tijd
    private static long runRemoveMinOnce(int n, long seed) {
        PriorityQueue<Integer> pq = buildPQ(n, seed);

        long start = System.nanoTime();
        int sink = 0;
        for (int i = 0; i < n; i++) {
            sink ^= pq.removeMin();
        }
        long end = System.nanoTime();

        if (sink == 123) System.out.print("");
        return end - start;
    }
// Bouwt een priority queue met n elementen en willekeurige prioriteiten
    private static PriorityQueue<Integer> buildPQ(int n, long seed) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Random r = new Random(seed);
        for (int i = 0; i < n; i++) {
            pq.insert(i, r.nextInt());
        }
        return pq;
    }
}
