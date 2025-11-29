package app.searching;

public interface iBinarySearch<T extends Comparable<T> > {
    /**
     * Voert een binaire zoekopdracht uit op een gesorteerde array
     * @param array gesorteerde array waarop gezocht moet worden
     * @param target te zoeken element
     * @return index van het element als gevonden, anders -1. -1 omdat het element niet in de array zit
     */
    int binarySearch(T[] array, T target);
}
