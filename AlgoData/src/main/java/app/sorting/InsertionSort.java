package app.sorting;

/**
 * Implementatie van het Insertion Sort algoritme
 * @param <T> type van de elements in de array
 */
public class InsertionSort<T extends Comparable<T>> implements iSorting<T> {
    /**
     * Sorteert een gegeven array
     * TC: O(n^2) in het slechtste geval, O(n) in het beste geval
     * SC: O(1) in place sorting
     * @param array De array die gesorteerd moet worden.
     */
    @Override
    public void sort(T[] array) {
        int n = array.length;

        for (int i = 1; i < n; ++i) {
            T key = array[i];
            int j = i - 1;

            // Verplaats elementen van array[0..i-1], die groter zijn dan key naar een positie verderop in de array
            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
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
}
