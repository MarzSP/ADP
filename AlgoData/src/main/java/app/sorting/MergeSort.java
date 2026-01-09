package app.sorting;

/**
 * Merge Sort is een divide-and-conquer algoritme.
 * Time complexity: O(n log n) in alle gevallen omdat de array steeds in tweeen wordt gedeeld (het log n gedeelte) en en in elke laag wordt de hele array 1 keer
 * doorlopen tijdens het mergen (n).
 * Space complexity: O(n) want extra arrays tijdens het splitsen/sorteren
 */
public class MergeSort<T extends Comparable<T>> implements iSorting<T> {
    long mergeAttempts = 0;
    long mergeSkips = 0;
    long mergeActual = 0;

    public long getMergeAttempts() { return mergeAttempts; }
    public long getMergeSkips() { return mergeSkips; }
    public long getMergeActual() { return mergeActual; }

    /**
     * TC: O(n log n)
     * @param array De array die gesorteerd moet worden.
     */
    @Override
    public void sort(T[] array) {
        if (array.length < 2) return;

        int mid = array.length / 2;

        // copyOfRange: maakt nieuwe arrays aan voor de linker en rechter helft
        T[] left = java.util.Arrays.copyOfRange(array, 0, mid);
        T[] right = java.util.Arrays.copyOfRange(array, mid, array.length);

        // Recursief sorteren
        sort(left);
        sort(right);

        mergeAttempts++;

        // VERBETERING: als linkerhelft al volledig <= rechterhelft, skip merge
        // Zonder verbetering: attempts=9999, actual=9999, skips=0
        // Met verbetering: attempts=9999, actual=0, skips=9999
        //if (left[left.length - 1].compareTo(right[0]) <= 0) {
         //   mergeSkips++;
           // System.arraycopy(left, 0, array, 0, left.length);
          //  System.arraycopy(right, 0, array, left.length, right.length);
          //  return;
       // }

        mergeActual++;
        merge(array, left, right);
    }

    /**
     * Merge de twee gesorteerde arrays terug naar de originele array
     * @param array originele array (waar de gesorteerde elementen in komen)
     * @param left linker helft van de array
     * @param right rechter helft van de array
     */
    private void merge(T[] array, T[] left, T[] right) {
        int i = 0;
        int j = 0;
        int k = 0;

        // Compare + voeg elements van beide helften samen in de originele array
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

         //1 van de 2 helften raakt altijd eerder leeg tijdens het samenvoegen
        // Gesorteerde elements moeten nog naar de eindarray
       // Kopieer de resterende elementen van de linker helft
        while (i < left.length) {
            array[k++] = left[i++];
        }

        // Kopieer de resterende elementen van de rechter helft
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    @Override
    public String getName() {
        return "Merge Sort Algoritme";
    }
}
