package app.searching;

public class BinarySearch {

    /**
     * Zoekt target in een gesorteerde array (oplopend).
     * @return index van target, of -1 als niet gevonden.
     */
    public static int binarySearch(int[] sorted, int target) {
        int left = 0;
        int right = sorted.length - 1;

        while (left <= right) {
            // voorkom overflow t.o.v. (left + right) / 2
            int mid = left + (right - left) / 2;

            if (sorted[mid] == target) {
                return mid;
            } else if (sorted[mid] < target) {
                left = mid + 1;      // zoek rechts
            } else {
                right = mid - 1;     // zoek links
            }
        }

        return -1;
    }
}
