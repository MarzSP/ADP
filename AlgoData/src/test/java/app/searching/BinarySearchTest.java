package app.searching;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTest {

    @Test
    void testFindMiddleElement() {
        BinarySearch<Integer> bs = new BinarySearch<>();
        Integer[] array = {1, 3, 5, 7, 9};

        assertEquals(2, bs.binarySearch(array, 5)); // mid
    }

    @Test
    void testFindFirstElementLeft() {
        BinarySearch<Integer> bs = new BinarySearch<>();
        Integer[] array = {2, 4, 6, 8, 10};

        assertEquals(0, bs.binarySearch(array, 2)); // eerste element
    }

    @Test
    void testFindLastElementRight() {
        BinarySearch<Integer> bs = new BinarySearch<>();
        Integer[] array = {2, 4, 6, 8, 10};

        assertEquals(4, bs.binarySearch(array, 10)); // laatste element
    }

    @Test
    void testElementNotFound() {
        BinarySearch<Integer> bs = new BinarySearch<>();
        Integer[] array = {1, 2, 3, 4, 5};

        assertEquals(-1, bs.binarySearch(array, 99)); // bestaat niet
    }

    /**
     * Deze test is nodig om te controleren dat het gedrag correct is met een lege array
     */
    @Test
    void testEmptyArray() {
        BinarySearch<Integer> bs = new BinarySearch<>();
        Integer[] array = {};

        assertEquals(-1, bs.binarySearch(array, 10)); // leeg is -1
    }

    @Test
    void testSingleElementArrayFound() {
        BinarySearch<Integer> bs = new BinarySearch<>();
        Integer[] array = {42};

        assertEquals(0, bs.binarySearch(array, 42)); // vindt t element
    }

    /**
     * Deze test is nodig om te controleren of er wel -1 wordt teruggegeven als het element niet in een enkele element array zit
     */
    @Test
    void testSingleElementArrayNotFound() {
        BinarySearch<Integer> bs = new BinarySearch<>();
        Integer[] array = {42};

        assertEquals(-1, bs.binarySearch(array, 5));
    }

    /**
     * Deze test is nodig om te controleren of de binaire zoekopdracht ook met strings werkt (het is een generieke implementatie immers)
     */
    @Test
    void testBinarySearchWithStrings() {
        BinarySearch<String> bs = new BinarySearch<>();
        String[] array = {"apple", "banana", "cherry", "date"};

        assertEquals(1, bs.binarySearch(array, "banana"));
        assertEquals(-1, bs.binarySearch(array, "orange"));
    }

    /**
     * Deze test is nodig om te controleren of de binaire zoekopdracht ook met negatieve getallen ook werkt
     */
    @Test
    void testBinarySearchNegativeNumbers() {
        BinarySearch<Integer> bs = new BinarySearch<>();
        Integer[] array = {-10, -5, 0, 5, 10};

        assertEquals(0, bs.binarySearch(array, -10));
        assertEquals(1, bs.binarySearch(array, -5));
        assertEquals(3, bs.binarySearch(array, 5));
    }

    /** Grote datasets testen
     *  Deze tests testen hoe de binaire zoekopdracht omgaat met grote datasets.
     */
    @Test
    void testLargeSortedArrayFound() {
        BinarySearch<Integer> bs = new BinarySearch<>();

        int size = 1_000_000; // 1mil elements
        Integer[] array = new Integer[size];

        // sorted waardes
        for (int i = 0; i < size; i++) {
            array[i] = i * 2;
        }

        // 1ste element
        assertEquals(0, bs.binarySearch(array, 0));

        // Mid element
        int middleIndex = size / 2;
        int middleValue = array[middleIndex];
        assertEquals(middleIndex, bs.binarySearch(array, middleValue));

        // Last element
        int lastIndex = size - 1;
        int lastValue = array[lastIndex];
        assertEquals(lastIndex, bs.binarySearch(array, lastValue));

        //Een paar random indices in stappen van 10k om het redelijk performant te houden
        for (int i = 0; i < size; i += 10_000) {
            int value = array[i];
            assertEquals(i, bs.binarySearch(array, value));
        }
    }

    @Test
    void testLargeArrayNotFoundSimple() {
        BinarySearch<Integer> bs = new BinarySearch<>();

        int size = 1_000_000;
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = i * 2;
        }

        assertEquals(-1, bs.binarySearch(array, 1));
        assertEquals(-1, bs.binarySearch(array, 999_999));
        assertEquals(-1, bs.binarySearch(array, 123_457));
    }
}
