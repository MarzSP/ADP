package app.searching;
import java.util.Random;
/**
 * Demo van de BinarySearchTree class
 * Laat zien hoe de binaire zoekboom werkt: invoegen, zoeken, min/max vinden, grootte en in-order traversals
 * Performance wordt gemeten voor verschillende operaties met zowel random als gesorteerde input
 * TC: varieert per operatie, meestal O(h) met h de hoogte van de boom
 * SC: O(1) voor de meeste operaties, O(n) voor in-order traversals
 */
public class BSTDemo {

    private static long timeNanos(Runnable r) {
        long start = System.nanoTime();
        r.run();
        return System.nanoTime() - start;
    }

    private static void printMs(String label, long nanos) {
        System.out.printf("%-30s %8.3f ms%n", label, nanos / 1_000_000.0);
    }

    public static void main(String[] args) {

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] values = {8, 3, 10, 1, 6, 14, 4, 7, 13};

        System.out.println("=== Demo BST ===");

        long insertSmall = timeNanos(() -> {
            for (int v : values) bst.insert(v);
        });
        printMs("Insert (9 values)", insertSmall);

        long sizeSmall = timeNanos(() -> bst.size());
        printMs("size()", sizeSmall);

        long contains7 = timeNanos(() -> bst.contains(7));
        printMs("contains(7)", contains7);

        long contains2 = timeNanos(() -> bst.contains(2));
        printMs("contains(2)", contains2);

        long minSmall = timeNanos(() -> bst.findMin());
        printMs("findMin()", minSmall);

        long maxSmall = timeNanos(() -> bst.findMax());
        printMs("findMax()", maxSmall);

        long inorderSmall = timeNanos(() -> bst.inOrder());
        printMs("inOrder()", inorderSmall);

        System.out.println("\nIn-order traversal:");
        System.out.println(bst.inOrder());

        // ==============================================
        // Execution time demo met grote input
        // ==============================================

        int n = 50_000;
        System.out.println("\n=== Execution time vergelijking (N = " + n + ") ===");

        // Demo met random input (gemiddeld beter)
        BinarySearchTree<Integer> randomBST = new BinarySearchTree<>();
        int[] randomValues = makeRandomArray(n, 0, n * 10);

        // Demo met gesorteerd input (worst-case scenario ongebalanceerde BST)
        BinarySearchTree<Integer> sortedBST = new BinarySearchTree<>();
        int[] sortedValues = makeSortedArray(n);

        // Warm-up ronde
        warmup(randomBST, randomValues);

        System.out.println("\n-- Random input --");
        printMs("Insert random", timeNanos(() -> {
            for (int v : randomValues) randomBST.insert(v);
        }));
        printMs("contains(last)", timeNanos(() -> randomBST.contains(randomValues[n - 1])));
        printMs("findMin()", timeNanos(randomBST::findMin));
        printMs("findMax()", timeNanos(randomBST::findMax));
        printMs("inOrder()", timeNanos(randomBST::inOrder));

        System.out.println("\n-- Sorted input (worst case) --");
        printMs("Insert sorted", timeNanos(() -> {
            for (int v : sortedValues) sortedBST.insert(v);
        }));
        printMs("contains(last)", timeNanos(() -> sortedBST.contains(sortedValues[n - 1])));
        printMs("findMin()", timeNanos(sortedBST::findMin));
        printMs("findMax()", timeNanos(sortedBST::findMax));

        try {
            long inorderSorted = timeNanos(sortedBST::inOrder);
            printMs("inOrder()", inorderSorted);
        } catch (StackOverflowError e) {
            System.out.println("inOrder() -> StackOverflowError (boomhoogte te groot door scheefgroei + recursie)");
        }
    }

    private static int[] makeSortedArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i;
        return a;
    }

    private static int[] makeRandomArray(int n, int min, int maxExclusive) {
        Random rnd = new Random(42); // vast hoeveelheid voor reproduceerbaarheid
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rnd.nextInt(maxExclusive - min) + min;
        return a;
    }

    private static void warmup(BinarySearchTree<Integer> bst, int[] values) {
        // warmup zodat voor echte zodat de compiler zn werk kan doen
        for (int i = 0; i < Math.min(values.length, 5_000); i++) bst.insert(values[i]);
        for (int i = 0; i < 1_000; i++) bst.contains(values[i]);
    }
}
