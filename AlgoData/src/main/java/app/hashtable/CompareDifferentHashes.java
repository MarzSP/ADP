package app.hashtable;

import java.util.*;

/**
 * Hash 1: Modulo met hashCode()
 * private int hashKey(K key, int modulo) {
 * return Math.floorMod(key.hashCode(), modulo);
 * }
 * Resultaat hash 1:
 * put    median=  23,779 ms | min=  17,215 ms | max=  36,855 ms
 * get    median=   8,643 ms | min=   6,718 ms | max=  10,406 ms
 * remove median=   8,936 ms | min=   6,906 ms | max=  12,685 ms
 *
 * Hash 2: Mixen met bitshift
 * private int hashKey(K key, int modulo) {
 * int h = Objects.hashCode(key);
 * int mixed = h ^ (h >>> 16);
 * return Math.floorMod(mixed, modulo);
 * }
 *
 * Resultaat hash 2:
 * put    median=  28,603 ms | min=  25,425 ms | max=  44,649 ms
 * get    median=   9,826 ms | min=   9,414 ms | max=  14,338 ms
 * remove median=  11,673 ms | min=   9,831 ms | max=  15,931 ms
 *
 * Hash 3: Eigen string hash functie
 * private int hashKey(K key, int modulo) {
 * String s = String.valueOf(key);
 * int h = 0;
 * for (int i = 0; i < s.length(); i++) {
 * h = (31 * h + s.charAt(i)) % modulo;
 * }
 * return h;
 * }
 * Resultaat hash 3:
 * put    median=  37,277 ms | min=  30,052 ms | max=  37,504 ms
 * get    median=  13,094 ms | min=  12,151 ms | max=  18,539 ms
 * remove median=  15,478 ms | min=  12,458 ms | max=  19,630 ms
 */
public class CompareDifferentHashes {

    static final int N = 100_000;
    static final int WARMUP_RUNS = 2;
    static final int MEASURE_RUNS = 7;

    public static void main(String[] args) {
        // 1) Dataset: altijd dezelfde keys
        List<String> keys = new ArrayList<>(N);
        for (int i = 0; i < N; i++) keys.add("k" + i);

        // 2) Vaste (maar random) volgorde: iedereen krijgt exact dezelfde workload
        Collections.shuffle(keys, new Random(42));

        System.out.println("Benchmark N=" + N);
        System.out.println("Warmup=" + WARMUP_RUNS + ", measure=" + MEASURE_RUNS);
        System.out.println();

        runVariant(keys, HashTable::new);

    }

    @FunctionalInterface
    interface TableFactory {
        HashTable<String, Integer> create();
    }

    private static void runVariant(List<String> keys, TableFactory factory) {
        // Warmup rounds
        for (int i = 0; i < WARMUP_RUNS; i++) {
            runOnce(factory.create(), keys);
        }

        // Metingen verzamelen
        long[] putNanos = new long[MEASURE_RUNS];
        long[] getNanos = new long[MEASURE_RUNS];
        long[] removeNanos = new long[MEASURE_RUNS];

        for (int i = 0; i < MEASURE_RUNS; i++) {
            Result r = runOnce(factory.create(), keys);
            putNanos[i] = r.putNanos;
            getNanos[i] = r.getNanos;
            removeNanos[i] = r.removeNanos;
        }

        System.out.println("hash=v1 (naive)");
        printStat("put   ", putNanos);
        printStat("get   ", getNanos);
        printStat("remove", removeNanos);
        System.out.println();
    }

    private static Result runOnce(HashTable<String, Integer> table, List<String> keys) {
        // PUT
        long t0 = System.nanoTime();
        for (int i = 0; i < keys.size(); i++) table.put(keys.get(i), i);
        long t1 = System.nanoTime();

        // GET
        long t2 = System.nanoTime();
        for (String s : keys) {
            Integer v = table.get(s);
            if (v == null) throw new IllegalStateException("Missing key " + s);
        }
        long t3 = System.nanoTime();

        // REMOVE
        long t4 = System.nanoTime();
        for (String key : keys) table.remove(key);
        long t5 = System.nanoTime();

        return new Result(t1 - t0, t3 - t2, t5 - t4);
    }

    private static void printStat(String label, long[] nanos) {
        Arrays.sort(nanos);
        long median = nanos[nanos.length / 2];
        long min = nanos[0];
        long max = nanos[nanos.length - 1];

        System.out.printf("  %s median=%8.3f ms | min=%8.3f ms | max=%8.3f ms%n",
                label, toMs(median), toMs(min), toMs(max));
    }

    private static double toMs(long nanos) {
        return nanos / 1_000_000.0;
    }

    private record Result(long putNanos, long getNanos, long removeNanos) {
    }
}