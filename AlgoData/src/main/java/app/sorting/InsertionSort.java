package app.sorting;

/**
 * Implementatie van het Insertion Sort algoritme
 * @param <T> type van de elements in de array
 */
public class InsertionSort<T extends Comparable<T>> implements iSorting<T> {
    private long comparisons = 0;
    private long shifts = 0;
    public long whileCount = 0;

    public long getComparisons() { return comparisons; }
    public long getShifts() { return shifts; }
    /**
     * Sorteert een gegeven array
     * TC: O(n^2) in het slechtste geval, O(n) in het beste geval
     * SC: O(1) in place sorting
     * @param array De array die gesorteerd moet worden.
     */
    @Override
    public void sort(T[] array) {
        comparisons = 0;
        shifts = 0;
        int n = array.length;

        for (int i = 1; i < n; ++i) {
            T key = array[i];
            //VERBETERING: EARLY CONTINUE
            // BEFORE -> comparisons=143623, shifts=123624, whileChecks=19999
            // AFTER -> comparisons=143623, shifts=123624, whileChecks=16586
            //
            comparisons++;
            if (array[i - 1].compareTo(key) <= 0) {
              continue;
            }
            int j = i - 1;

            // Verplaats elementen van array[0..i-1], die groter zijn dan key naar een positie verderop in de array
            whileCount++;
            while (j >= 0 && array[j].compareTo(key) > 0) {
                comparisons++;
                array[j + 1] = array[j];
                shifts++;
                j--;
            }
            array[j + 1] = key;
        }
    }

    /**
     * Geeft de naam van het sorteer algoritme
     * TC: O(1)
     * @return
     */
    @Override
    public String getName() {
        return "Insertion Sort Algoritme";
    }

    public T getWhileCount() {
        return  (T) Long.valueOf(whileCount);
    }
}
