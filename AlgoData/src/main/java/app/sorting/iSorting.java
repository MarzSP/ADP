package app.sorting;

/**
 * Interface voor het sorteren van arrays. Generieke type T moet Comparable zijn.
 * @param <T>
 */
public interface iSorting<T extends Comparable<T>> {
    /**
     * Sorteert de gegeven array in plaats. Deze is generiek en vereist dat T Comparable is.
     * @param array De array die gesorteerd moet worden.
     */
    void sort(T[] array);

    /**
     *
     * @return
     */
    String getName();
}
