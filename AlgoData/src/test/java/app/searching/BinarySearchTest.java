package app.searching;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BinarySearchTest {

    // Normaal zoekscenario. Element bestaat ergens in het midden
    @Test
    void findsExistingElement() {
        int[] a = {1, 3, 5, 7, 9, 11};
        assertEquals(3, BinarySearch.binarySearch(a, 7));
    }

    // Best case
    @Test
    void findsMiddleElementBestCase() {
        int[] a = {1, 3, 5, 7, 9};
        assertEquals(2, BinarySearch.binarySearch(a, 5));
    }

    // Test linkergrens
    @Test
    void findsFirstElement() {
        int[] a = {1, 3, 5, 7, 9};
        assertEquals(0, BinarySearch.binarySearch(a, 1));
    }

    // Test rechtergrens
    @Test
    void findsLastElement() {
        int[] a = {1, 3, 5, 7, 9};
        assertEquals(4, BinarySearch.binarySearch(a, 9));
    }

    // Test zoeken naar een waarde die niet bestaat (worst case) maar wel binnen bereik van de array ligt
    @Test
    void notFoundButWithinRangeWorstCase() {
        int[] a = {1, 3, 5, 7, 9};
        assertEquals(-1, BinarySearch.binarySearch(a, 6));
    }

    // Zoeken naar een waarde die kleiner is dan alle elementen in de array
    @Test
    void valueSmallerThanAllElements() {
        int[] a = {10, 20, 30};
        assertEquals(-1, BinarySearch.binarySearch(a, 5));
    }

    //Zoeken naar een waarde die groter is dan alle elementen in de array
    @Test
    void valueGreaterThanAllElements() {
        int[] a = {10, 20, 30};
        assertEquals(-1, BinarySearch.binarySearch(a, 40));
    }

    // Tests of het correct werkt wanneer element niet is gevonden
    @Test
    void returnsMinusOneWhenNotFound() {
        int[] a = {1, 3, 5, 7, 9, 11};
        assertEquals(-1, BinarySearch.binarySearch(a, 8));
    }

    // Tests voor edge case lege array
    @Test
    void worksOnEmptyArray() {
        int[] a = {};
        assertEquals(-1, BinarySearch.binarySearch(a, 1));
    }

    // Tests voor array met 1 element. Zowel gevonden als niet gevonden
    @Test
    void worksOnSingleElement() {
        int[] a = {42};
        assertEquals(0, BinarySearch.binarySearch(a, 42));
        assertEquals(-1, BinarySearch.binarySearch(a, 7));
    }

    // Test op grote getallen even en oneven (Dus in of niet in de array)
    @Test
    void worksOnLargeArray() {
        int size = 100000;
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = i * 2; // even getallen
        }

        assertEquals(2500, BinarySearch.binarySearch(a, 5000));
        assertEquals(-1, BinarySearch.binarySearch(a, 5001));
    }

    // Test met negatieve getallen moet ook werken
    @Test
    void worksWithNegativeNumbers() {
        int[] a = {-10, -5, 0, 5, 10};
        assertEquals(1, BinarySearch.binarySearch(a, -5));
        assertEquals(-1, BinarySearch.binarySearch(a, -3));
    }
}
