package app.searching;

/**
 * Implementeert binaire zoekalgoritme
 * Een binaire zoekopdracht werkt alleen op gesorteerde arrays. Een binaire zoekopdracht halveert de zoekruimte telkens totdat het doel is gevonden of de zoekruimte leeg is.
 * Dez e implementatie gebruikt generics zodat het op elk vergelijkbaar type kan werken.
 * @param <T> type van de elementen (moet Comparable zijn)
 */
public class BinarySearch<T extends Comparable<T>> implements iBinarySearch<T> {
    /**
     * @param array gesorteerde array waarop gezocht moet worden
     * @param target te zoeken element
     * @return index van het element als gevonden, anders -1. -1 omdat het element niet in de array zit
     */
    @Override
    public int binarySearch(T[] array, T target) {
        int left = 0;
        int right = array.length - 1; // rechter uiteinde van de array

        while (left <= right) {
            int mid = left + (right - left) / 2; // voorkom overflow omdat (left + right) soms (in extremere gevallen) te groot kan zijn voor int

            int comparison = array[mid].compareTo(target); // vergelijk midden element met target
            if (comparison == 0) {
                return mid; // middel is target gevonden
            } else if (comparison < 0) {
                left = mid + 1; // zoek in rechterhelft
            } else {
                right = mid - 1; //zoek in linkerhelft
            }
        }

        return -1; // niet gevonden
    }
}
