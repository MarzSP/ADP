package app.searching;

import java.util.Arrays;

/**
 * Binary Search Demo
 *
 * Array: [2, 4, 6, 8, 10, 12, 14]
 * Zoekwaarde 10 gevonden op index 4
 * Zoekwaarde 9 niet gevonden
 *
 * Best case scenario
 * Zoekwaarde 8 gevonden op index 3
 *
 * Worst case scenario
 * Zoekwaarde 15 niet gevonden
 *
 * --- Grotere dataset ---
 * Zoekwaarde 999999 gevonden op index 999999 (tijd: 1600 ns)
 * Zoekwaarde -1 niet gevonden (tijd: 2800 ns)
 */
public class BinarySearchDemo {

    public static void main(String[] args) {

        System.out.println("=== Binary Search Demo ===\n");

        // 1. Basic voorbeeld
        int[] smallArray = {2, 4, 6, 8, 10, 12, 14};
        System.out.println("Array: " + Arrays.toString(smallArray));
        demoSearch(smallArray, 10); // bestaat
        demoSearch(smallArray, 9);  // bestaat niet

        // 2. Best-case scenario: middelste element
        System.out.println("\n--- Best case scenario ---");
        demoSearch(smallArray, 8);

        // 3. Worst-case scenario: niet aanwezig
        System.out.println("\n--- Worst case scenario ---");
        demoSearch(smallArray, 15);

        // 4. Grotere dataset: voor performance
        System.out.println("\n--- Grotere dataset ---");
        int[] largeArray = createSortedArray(1_000_000);
        demoTimedSearch(largeArray, 999_999); // aanwezig
        demoTimedSearch(largeArray, -1);      // niet aanwezig
    }

    /**
     * Voert een binary search uit en print het resultaat.
     */
    private static void demoSearch(int[] array, int target) {
        int index = BinarySearch.binarySearch(array, target);

        if (index != -1) {
            System.out.println("Zoekwaarde " + target +
                    " gevonden op index " + index);
        } else {
            System.out.println("Zoekwaarde " + target +
                    " niet gevonden");
        }
    }

    /**
     * Voert binary search uit en meet de execution time.
     */
    private static void demoTimedSearch(int[] array, int target) {
        long start = System.nanoTime();
        int index = BinarySearch.binarySearch(array, target);
        long end = System.nanoTime();

        long duration = end - start;

        if (index != -1) {
            System.out.println("Zoekwaarde " + target +
                    " gevonden op index " + index +
                    " (tijd: " + duration + " ns)");
        } else {
            System.out.println("Zoekwaarde " + target +
                    " niet gevonden (tijd: " + duration + " ns)");
        }
    }

    /**
     * Maakt een gesorteerde array van 0 t/m size-1.
     */
    private static int[] createSortedArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = i;
        }
        return result;
    }
}
