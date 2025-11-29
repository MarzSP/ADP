package app.sorting;

/**
 * Implementatie van het Insertion Sort algoritme
 * Generic Comparable<T> is nodig om elementen te kunnen vergelijken
 * iSorting<T> is nodig om de interface te implementeren zodat alle sorteer algoritmes in dit project dezelfde methodes hebben
 * Complexiteit: O(n^2) in het slechtste geval, O(n) in het beste geval
 * Slechtste geval is bijvoorbeeld bij een omgekeerd gesorteerde array. Iedere item moet terug loopen naar het begin van de array, waardoor er veel verplaatsingen zijn.
 * Beste geval is bijvoorbeeld bij een al gesorteerde array. De loop draait maar een keer door de array zonder verplaatsingen.
 * @param <T> type van de elements in de array
 */
public class InsertionSort<T extends Comparable<T>> implements iSorting<T> {
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

    @Override
    public String getName() {
        return "Insertion Sort Algoritme";
    }
}
