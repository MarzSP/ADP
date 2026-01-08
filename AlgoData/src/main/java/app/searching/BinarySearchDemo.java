package app.searching;

import java.util.Arrays;

/**
 * Binary Search Demo
 *
 * Array: [2, 4, 6, 8, 10, 12, 14]
 * Zoekwaarde 10 gevonden op index 4
 * Zoekwaarde 9 niet gevonden
 *
 * Best case scenario:
 * Zoekwaarde 8 gevonden op index 3
 *
 * Worst case scenario:
 * Zoekwaarde 15 niet gevonden
 *
 * Grotere dataset:
 * Zoekwaarde 999999 gevonden op index 999999 (tijd: 1600 ns)
 *
 * Negative number search:
 * Zoekwaarde -1 niet gevonden (tijd: 2800 ns)
 */
public class BinarySearchDemo {

    public static void main(String[] args) {

        System.out.println("=== Binary Search Demo ===\n");

        // 1. Basisvoorbeeld
        Integer[] smallArray = {2, 4, 6, 8, 10, 12, 14};
        System.out.println("Array: " + Arrays.toString(smallArray));
        demoSearch(smallArray, 10); // bestaat
        demoSearch(smallArray, 9);  // bestaat niet

        // 2. Best case: middelste element
        System.out.println("\n--- Best case scenario ---");
        demoSearch(smallArray, 8);

        // 3. Worst case: niet aanwezig
        System.out.println("\n--- Worst case scenario ---");
        demoSearch(smallArray, 15);

        // 4. Grotere dataset: performance
        System.out.println("\n--- Grotere dataset ---");
        Integer[] largeArray = createSortedArray(1_000_000);
        demoTimedSearch(largeArray, 999_999);
        demoTimedSearch(largeArray, -1);
    }

    /**
     * Voert binary search uit en toont resultaat
     */
    private static <T extends Comparable<T>> void demoSearch(T[] array, T target) {
        int index = BinarySearch.binarySearch(array, target);

        if (index != -1) {
            System.out.println("Zoekwaarde " + target + " gevonden op index " + index);
        } else {
            System.out.println("Zoekwaarde " + target + " niet gevonden");
        }
    }

    /**
     * Voert binary search uit met execution time meting
     */
    private static <T extends Comparable<T>> void demoTimedSearch(T[] array, T target) {
        long start = System.nanoTime();
        int index = BinarySearch.binarySearch(array, target);
        long end = System.nanoTime();

        long duration = end - start;

        if (index != -1) {
            System.out.println(
                    "Zoekwaarde " + target +
                            " gevonden op index " + index +
                            " (tijd: " + duration + " ns)"
            );
        } else {
            System.out.println(
                    "Zoekwaarde " + target +
                            " niet gevonden (tijd: " + duration + " ns)"
            );
        }
    }

    /**
     * Maakt een gesorteerde Integer-array van 0 t/m size-1
     */
    private static Integer[] createSortedArray(int size) {
        Integer[] result = new Integer[size];
        for (int i = 0; i < size; i++) {
            result[i] = i;
        }
        return result;
    }
}