package app.hashtable;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Demo vergelijken HashTable implementatie met Java Hashtable
 Benchmarks met N=100000
 HashTable put: 28,633 ms
 Java Hashtable put: 7,137 ms
 HashTable get: 8,558 ms
 Java Hashtable get: 2,339 ms
 HashTable remove: 28,886 ms
 Java Hashtable remove: 3,277 ms
 *
 *   Java gebruikt probing ipv chaining,
 *   Java gebruikt bitmasking ipv modulo
 *   Java gebruikt optimalisaties in hashcode en equals
 */
public class CompareWithHashTable {

    public static void main(String[] args) {
        System.out.println("Vergelijk HashTable vs Java Hashtable\n");

        //Benchmarks (put/get/remove)
        int N = 100_000; // entries
        System.out.println("Benchmarks met N=" + N);
        List<String> keys = new ArrayList<>(N);
        for (int i = 0; i < N; i++) keys.add("k" + i);

        microbenchmarkPutGetRemove(keys);
    }


    private static void microbenchmarkPutGetRemove(List<String> keys) {
        HashTable<String, Integer> my = new HashTable<>();
        Map<String, Integer> jtable = new Hashtable<>();

        // PUT benchmark
        long t0 = System.nanoTime();
        for (int i = 0; i < keys.size(); i++) my.put(keys.get(i), i);
        long t1 = System.nanoTime();
        printTime("HashTable put", t1 - t0);

        long t2 = System.nanoTime();
        for (int i = 0; i < keys.size(); i++) jtable.put(keys.get(i), i);
        long t3 = System.nanoTime();
        printTime("Java Hashtable put", t3 - t2);

        // GET benchmark
        long t4 = System.nanoTime();
        for (int i = 0; i < keys.size(); i++) {
            Integer v = my.get(keys.get(i));
            if (v == null) System.err.println("Missing in my table: " + keys.get(i));
        }
        long t5 = System.nanoTime();
        printTime("HashTable get", t5 - t4);

        long t6 = System.nanoTime();
        for (int i = 0; i < keys.size(); i++) {
            Integer v = jtable.get(keys.get(i));
            if (v == null) System.err.println("Missing in jtable: " + keys.get(i));
        }
        long t7 = System.nanoTime();
        printTime("Java Hashtable get", t7 - t6);

        // REMOVE benchmark
        long t8 = System.nanoTime();
        for (int i = 0; i < keys.size(); i++) my.remove(keys.get(i));
        long t9 = System.nanoTime();
        printTime("HashTable remove", t9 - t8);

        long t10 = System.nanoTime();
        for (int i = 0; i < keys.size(); i++) jtable.remove(keys.get(i));
        long t11 = System.nanoTime();
        printTime("Java Hashtable remove", t11 - t10);

        System.out.println();
    }

    private static void printCheck(String label, Object a, Object b) {
        String ok = (a == null ? b == null : a.equals(b)) ? "OK" : "MISMATCH";
        System.out.println("  " + label + ": my=" + a + ", java=" + b + " -> " + ok);
    }

    private static void printTime(String label, long nanos) {
        double ms = nanos / 1_000_000.0;
        System.out.printf("  %s: %d ns (%.3f ms)%n", label, nanos, ms);
    }
}
