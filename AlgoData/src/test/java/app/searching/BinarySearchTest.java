package app.searching;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BinarySearchTest {

    @Test
    void findsMiddleElementBestCase() {
        int[] a = {1, 3, 5, 7, 9};
        assertEquals(2, BinarySearch.binarySearch(a, 5));
    }

    @Test
    void findsFirstElement() {
        int[] a = {1, 3, 5, 7, 9};
        assertEquals(0, BinarySearch.binarySearch(a, 1));
    }

    @Test
    void notFoundButWithinRangeWorstCase() {
        int[] a = {1, 3, 5, 7, 9};
        assertEquals(-1, BinarySearch.binarySearch(a, 6));
    }

    @Test
    void valueSmallerThanAllElements() {
        int[] a = {10, 20, 30};
        assertEquals(-1, BinarySearch.binarySearch(a, 5));
    }

    @Test
    void valueGreaterThanAllElements() {
        int[] a = {10, 20, 30};
        assertEquals(-1, BinarySearch.binarySearch(a, 40));
    }

    @Test
    void findsLastElement() {
        int[] a = {1, 3, 5, 7, 9};
        assertEquals(4, BinarySearch.binarySearch(a, 9));
    }

    @Test
    void findsExistingElement() {
        int[] a = {1, 3, 5, 7, 9, 11};
        assertEquals(3, BinarySearch.binarySearch(a, 7));
    }

    @Test
    void returnsMinusOneWhenNotFound() {
        int[] a = {1, 3, 5, 7, 9, 11};
        assertEquals(-1, BinarySearch.binarySearch(a, 8));
    }

    @Test
    void worksOnEmptyArray() {
        int[] a = {};
        assertEquals(-1, BinarySearch.binarySearch(a, 1));
    }

    @Test
    void worksOnSingleElement() {
        int[] a = {42};
        assertEquals(0, BinarySearch.binarySearch(a, 42));
        assertEquals(-1, BinarySearch.binarySearch(a, 7));
    }

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

    @Test
    void worksWithNegativeNumbers() {
        int[] a = {-10, -5, 0, 5, 10};
        assertEquals(1, BinarySearch.binarySearch(a, -5));
        assertEquals(-1, BinarySearch.binarySearch(a, -3));
    }


}
