package app.priorityQueue;

import org.junit.jupiter.api.Test;

/**
 * Eerste run: 12635 ms
 * Tweede run: 9708 ms
 * Derde run: 10005 ms
 * Vierde run: 9122ms
 * In vergelijking met de originele EnqueueManyDifferentPrioritiesCanBeSlow-test van PriorityQueue is deze aanzienlijk sneller.
 * De originele test liep tussen de 13600 - 16000 ms (op dezelfde machine onder dezelfde omstandigheden)
 */
public class VerbeteringPerformanceDemoTest {
    @Test
    void enqueueManyDifferentPriorities_mapIndexIsFaster() {
        int n = 100_000;

        var baseline = new PriorityQueue<String>();
        VerbeteringPerformanceDemo<String> improved = new VerbeteringPerformanceDemo<>();

        long tBaseline = timeEnqueue(baseline, n);
        long tImproved = timeEnqueue(improved, n);

        System.out.println("Baseline enqueue (LinkedList bucket search): " + tBaseline + " ms");
        System.out.println("Improved enqueue (LinkedList + HashMap index): " + tImproved + " ms");
    }

    private long timeEnqueue(app.priorityQueue.iPriorityQueue<String> pq, int n) {
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            pq.enqueue("E" + i, i); // veel verschillende prioriteiten
        }
        return (System.nanoTime() - start) / 1_000_000;
    }
}
