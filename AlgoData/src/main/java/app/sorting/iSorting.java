package app.sorting;

/**
 * Interface voor het sorteren van arrays. Generieke type T moet Comparable zijn.
 * Generic Comparable<T> is nodig om elementen te kunnen vergelijken
 * @param <T>
 */
public interface iSorting<T extends Comparable<T>> {
    /**
     * Sorteert de gegeven array in plaats. Deze is generiek en vereist dat T Comparable is.
     * @param array De array die gesorteerd moet worden.
     */
    void sort(T[] array);

    /**
     * geeft de naam van het sorteer algoritme
     * @return naam van t sorteer algoritme
     */
    String getName();
}
