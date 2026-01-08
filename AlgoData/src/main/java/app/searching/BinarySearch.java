package app.searching;

public class BinarySearch {

    /**
     * Zoekt target in een gesorteerde array (oplopend).
     * @return index van target, of -1 als niet gevonden.
     */
    public static <T extends Comparable<T>> int binarySearch(T[] sorted, T target) {
        int left = 0;
        int right = sorted.length - 1;

        while (left <= right) {
            // voorkom overflow t.o.v. (left + right) / 2
            int middle = left + (right - left) / 2;

            int comparisonResult = sorted[middle].compareTo(target);

            if (comparisonResult == 0) {
                return middle;
            }

            if (comparisonResult > 0) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
}

