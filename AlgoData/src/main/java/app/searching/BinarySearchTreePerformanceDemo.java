package app.searching;

/**
 * Meet de performance van de BinarySearchTree bij verschillende invoer
 * Vaste input: gebalanceerde (150 values) en gesorteerd (150 values)
 * Runs: 1000
 * Resultaat:
 * Add balanced:  560800 ns (0 ms)
 * Add sorted:    598400 ns (0 ms)
 * Contains balanced: 7475800 ns (7 ms)
 * Contains sorted:   19684800 ns (19 ms)
 * ToSortedList balanced: 587500 ns (0 ms)
 * ToSortedList sorted:   288000 ns (0 ms)
 *
 */
public class BinarySearchTreePerformanceDemo {

    public static void main(String[] args) {
        int[] balancedValues = createBalancedValues();
        int[] sortedValues = createSortedValues();

        long balancedAddNs = timeAdd(balancedValues);
        long sortedAddNs = timeAdd(sortedValues);

        long balancedContainsNs = timeContains(balancedValues);
        long sortedContainsNs = timeContains(sortedValues);

        long balancedListNs = timeToSortedList(balancedValues);
        long sortedListNs = timeToSortedList(sortedValues);

        System.out.println("Add balanced:  " + balancedAddNs + " ns (" + (balancedAddNs / 1_000_000) + " ms)");
        System.out.println("Add sorted:    " + sortedAddNs + " ns (" + (sortedAddNs / 1_000_000) + " ms)");
        System.out.println("Contains balanced: " + balancedContainsNs + " ns (" + (balancedContainsNs / 1_000_000) + " ms)");
        System.out.println("Contains sorted:   " + sortedContainsNs + " ns (" + (sortedContainsNs / 1_000_000) + " ms)");
        System.out.println("ToSortedList balanced: " + balancedListNs + " ns (" + (balancedListNs / 1_000_000) + " ms)");
        System.out.println("ToSortedList sorted:   " + sortedListNs + " ns (" + (sortedListNs / 1_000_000) + " ms)");
    }

    private static long timeAdd(int[] values) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        long start = System.nanoTime();
        for (int value : values) {
            bst.add(value);
        }
        return System.nanoTime() - start;
    }

    private static long timeContains(int[] values) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int value : values) {
            bst.add(value);
        }

        long start = System.nanoTime();
        for (int r = 0; r < 1000; r++) {
            for (int value : values) {
                bst.contains(value);
            }
        }
        return System.nanoTime() - start;
    }

    private static long timeToSortedList(int[] values) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int value : values) {
            bst.add(value);
        }

        long start = System.nanoTime();
        bst.TreeToSortedList();
        return System.nanoTime() - start;
    }

    private static int[] createBalancedValues() {
        return new int[] {
                75, 37, 18, 9, 4, 2, 1, 3, 6, 5, 7, 13,
                11, 10, 12, 15, 14, 16, 27, 22, 20, 19,
                21, 24, 23, 25, 32, 29, 28, 30, 34, 33,
                35, 56, 47, 42, 39, 38, 40, 41, 44, 43,
                45, 52, 49, 48, 50, 54, 53, 55, 66, 61,
                59, 58, 60, 63, 62, 64, 70, 68, 67, 69,
                72, 71, 73, 113, 94, 84, 80, 77, 76, 78,
                79, 82, 81, 83, 89, 87, 86, 88, 91, 90,
                92, 103, 98, 96, 95, 97, 100, 99, 101, 108,
                105, 104, 106, 110, 109, 111, 132, 122, 118,
                116, 115, 117, 120, 119, 121, 127, 125, 124,
                126, 129, 128, 130, 141, 136, 134, 133, 135,
                138, 137, 139, 146, 144, 143, 145, 148, 147,
                149, 150
        };
    }

    private static int[] createSortedValues() {
        return new int[] {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
                61, 62, 63, 64, 65, 66, 67, 68, 69, 70,
                71, 72, 73, 74, 75, 76, 77, 78, 79, 80,
                81, 82, 83, 84, 85, 86, 87, 88, 89, 90,
                91, 92, 93, 94, 95, 96, 97, 98, 99, 100,
                101, 102, 103, 104, 105, 106, 107, 108, 109,
                110, 111, 112, 113, 114, 115, 116, 117, 118,
                119, 120, 121, 122, 123, 124, 125, 126, 127,
                128, 129, 130, 131, 132, 133, 134, 135, 136,
                137, 138, 139, 140, 141, 142, 143, 144, 145,
                146, 147, 148, 149, 150
        };
    }
}