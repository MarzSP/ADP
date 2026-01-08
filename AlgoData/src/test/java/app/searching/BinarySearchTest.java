package app.searching;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BinarySearchTest {

    // Normaal zoekscenario. Element bestaat ergens in het midden
    @Test
    void findsExistingElement() {
        Integer[] a = {1, 3, 5, 7, 9, 11};
        assertEquals(3, BinarySearch.binarySearch(a, 7));
    }

    // Best case
    @Test
    void findsMiddleElementBestCase() {
        Integer[] a = {1, 3, 5, 7, 9};
        assertEquals(2, BinarySearch.binarySearch(a, 5));
    }

    // Test linkergrens
    @Test
    void findsFirstElement() {
        Integer[] a = {1, 3, 5, 7, 9};
        assertEquals(0, BinarySearch.binarySearch(a, 1));
    }

    // Test rechtergrens
    @Test
    void findsLastElement() {
        Integer[] a = {1, 3, 5, 7, 9};
        assertEquals(4, BinarySearch.binarySearch(a, 9));
    }

    // Test zoeken naar een waarde die niet bestaat (worst case) maar wel binnen bereik van de array ligt
    @Test
    void notFoundButWithinRangeWorstCase() {
        Integer[] a = {1, 3, 5, 7, 9};
        assertEquals(-1, BinarySearch.binarySearch(a, 6));
    }

    // Zoeken naar een waarde die kleiner is dan alle elementen in de array
    @Test
    void valueSmallerThanAllElements() {
        Integer[] a = {10, 20, 30};
        assertEquals(-1, BinarySearch.binarySearch(a, 5));
    }

    //Zoeken naar een waarde die groter is dan alle elementen in de array
    @Test
    void valueGreaterThanAllElements() {
        Integer[] a = {10, 20, 30};
        assertEquals(-1, BinarySearch.binarySearch(a, 40));
    }

    // Tests of het correct werkt wanneer element niet is gevonden
    @Test
    void returnsMinusOneWhenNotFound() {
        Integer[] a = {1, 3, 5, 7, 9, 11};
        assertEquals(-1, BinarySearch.binarySearch(a, 8));
    }

    // Tests voor edge case lege array
    @Test
    void worksOnEmptyArray() {
        Integer[] a = {};
        assertEquals(-1, BinarySearch.binarySearch(a, 1));
    }

    // Tests voor array met 1 element. Zowel gevonden als niet gevonden
    @Test
    void worksOnSingleElement() {
        Integer[] a = {42};
        assertEquals(0, BinarySearch.binarySearch(a, 42));
        assertEquals(-1, BinarySearch.binarySearch(a, 7));
    }

    // Test op grote getallen even en oneven (Dus in of niet in de array)
    @Test
    void worksOnLargeArray() {
        int size = 100000;
        Integer[] a = new Integer[size];
        for (int i = 0; i < size; i++) {
            a[i] = i * 2; // even getallen
        }

        assertEquals(2500, BinarySearch.binarySearch(a, 5000));
        assertEquals(-1, BinarySearch.binarySearch(a, 5001));
    }

    // Test met negatieve getallen moet ook werken
    @Test
    void worksWithNegativeNumbers() {
        Integer[] a = {-10, -5, 0, 5, 10};
        assertEquals(1, BinarySearch.binarySearch(a, -5));
        assertEquals(-1, BinarySearch.binarySearch(a, -3));
    }

    // Test om te laten zien dat binary search ook werkt met String want generic
    @Test
    void worksWithStrings() {
        String[] words = {"apple", "banana", "cherry", "date", "fig"};
        assertEquals(2, BinarySearch.binarySearch(words, "cherry"));
        assertEquals(-1, BinarySearch.binarySearch(words, "grape"));
    }
}
