package app.hashtable;

/** Verbetering:
 * De hashtable verkleint nu bij een lage load factor, waardoor probe-routes korter blijven. Dit levert in de demo ongeveer 15–20% performancewinst op.
 *
 * De hashtable krijgt 1000 elementen (dan heeft de tabel een grootte van 2048) en verwijder bijna alles tot er nog 10 over zijn
 * Meet daarna get(), zodat het effect van verkleinen bij een lage load factor te zien is
 *
 * Resultaten 1ste run:
 * Baseline (no shrink):   38.1508 ms
 * Improved (with shrink): 36.605 ms
 *
 * Resultaten 2de run:
 * Baseline (no shrink):   36.2719 ms
 * Improved (with shrink): 29.4209 ms
 *
 * Resultaten 3de run:
 * Baseline (no shrink):   34.4934 ms
 * Improved (with shrink): 29.5727 ms
 *
 * Resultaten 4de run:
 * Baseline (no shrink):   35.5768 ms
 * Improved (with shrink): 28.5202 ms
 *
 */
public class VerbeteringPerformanceDemoHashtable {

    public static void main(String[] args) {
        int n = 1000;

        double baselineMs = measureGetAfterManyRemoves(new HashTable<>(), n);
        double improvedMs = measureGetAfterManyRemoves(new VerbeteringHashtableShrink<>(), n);

        System.out.println("Baseline (no shrink):   " + baselineMs + " ms");
        System.out.println("Improved (with shrink): " + improvedMs + " ms");
    }

    private static double measureGetAfterManyRemoves(iHashable<Integer, Integer> table, int n) {

        // Vul de tabel
        for (int i = 0; i < n; i++) {
            table.put(i, i);
        }

        // Haal bijna alles eruit
        for (int i = 0; i < n - 10; i++) {
            table.remove(i);
        }

        // Meet get() na veel removes (veel herhalen zodat het meetbaar is)
        int repeats = 1_000_000;

        long start = System.nanoTime();
        for (int r = 0; r < repeats; r++) {
            for (int i = n - 10; i < n; i++) {
                table.get(i);
            }
        }
        long durationNs = System.nanoTime() - start;

        return durationNs / 1_000_000.0;
    }
}
