package app.hashtable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Hash 1: Modulo met hashCode()
private int hashKey(K key, int modulo) {
    return Math.floorMod(key.hashCode(), modulo);
}
 Resultaat hash 1:
 PUT time: 146 ms
 GET time: 124 ms

    * Hash 2: Mixen met bitshift
private int hashKey(K key, int modulo) {
int h = Objects.hashCode(key);
int mixed = h ^ (h >>> 16);
return Math.floorMod(mixed, modulo);
}

 Resultaat hash 2:
 PUT time: 112 ms
 GET time: 100 ms

    * Hash 3: Eigen string hash functie
private int hashKey(K key, int modulo) {
String s = String.valueOf(key);
int h = 0;
for (int i = 0; i < s.length(); i++) {
    h = (31 * h + s.charAt(i)) % modulo;
}
return h;
}
 Resultaat hash 3:
 PUT time: 166 ms
 GET time: 140 ms
 */
public class CompareDifferentHashes {
    private static final int N = 200_000;
    private static final int GETS = 500_000;
    private static final long SEED = 42;

    public static void main(String[] args) {

        List<String> keys = generateKeys(N);

        HashTable<String, Integer> table = new HashTable<>();

        // --- PUT benchmark ---
        long t0 = System.nanoTime();
        for (int i = 0; i < keys.size(); i++) {
            table.put(keys.get(i), i);
        }
        long t1 = System.nanoTime();

        // --- GET benchmark ---
        Random rnd = new Random(SEED);
        long checksum = 0;
        long t2 = System.nanoTime();
        for (int i = 0; i < GETS; i++) {
            String key = keys.get(rnd.nextInt(keys.size()));
            Integer v = table.get(key);
            if (v != null) checksum += v;
        }
        long t3 = System.nanoTime();

        System.out.println("PUT time: " + (t1 - t0) / 1_000_000 + " ms");
        System.out.println("GET time: " + (t3 - t2) / 1_000_000 + " ms");
        System.out.println("Checksum (ignore): " + checksum);
    }

    private static List<String> generateKeys(int n) {
        Random rnd = new Random(SEED);
        List<String> keys = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            keys.add("key-" + rnd.nextInt(Integer.MAX_VALUE));
        }
        return keys;
    }
}
