package app.sorting;

/**
 * Merge Sort is een divide-and-conquer algoritme. De array wordt steeds opgesplits in twee helften totdat de stukken klein genoeg zijn (1 of 2 elementen) om vergelijkt te worden.
 * Daarna worden de stukken weer samengevoegd(merging) in gesorteerde volgorde.
 * Time complexity: O(n log n) in alle gevallen omdat de array steeds in tweeen wordt gedeeld (het log n gedeelte) en en in elke laag wordt de hele array 1 keer
 * doorlopen tijdens het mergen (n).
 * Space complexity: O(n) omdat tijdens het mergen tijdelijke arrays nodig zijn voor de linker en rechterhelft.
 */
public class MergeSort<T extends Comparable<T>> implements iSorting<T> {

    /**
     * Sorteert de gegeven array, deze is generics en heeft Comparable nodig om elementen te vergelijken.
     * @param array De array die gesorteerd moet worden.
     */
    @Override
    public void sort(T[] array) {
        if (array.length < 2) {
            return; // array al gesorteerd
        }
        int mid = array.length / 2;

        // copyOfRange maakt nieuwe arrays aan voor de linker en rechter helft
        T[] left = java.util.Arrays.copyOfRange(array, 0, mid);
        T[] right = java.util.Arrays.copyOfRange(array, mid, array.length);

        // Recursief sorteren van beide helften
        sort(left);
        sort(right);

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

        // Compare elementen van beide helften en voeg de kleinste toe aan de originele array
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        //1 van de 2 helften raakt altijd eerder leeg tijdens het samenvoegen
        //De overgebleven elementen in de andere helft zijn al gesorteerd maar staan nog niet in de eindarray.
       // Dus: kopieer de resterende elementen van de linker helft
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
