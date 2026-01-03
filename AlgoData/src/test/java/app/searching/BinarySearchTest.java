package app.searching;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BinarySearchTest {

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
}
