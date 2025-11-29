package app.sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class InsertionSortTest {

    private final InsertionSort<Integer> sorter = new InsertionSort<>();

    @Test
    void testSortRandomArray() {
        Integer[] array = {5, 2, 9, 1, 3};
        sorter.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 5, 9}, array);
    }

    @Test
    void testSortAlreadySortedArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        sorter.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testSortReverseSortedArray() {
        Integer[] array = {9, 7, 5, 3, 1};
        sorter.sort(array);
        assertArrayEquals(new Integer[]{1, 3, 5, 7, 9}, array);
    }

    @Test
    void testSortArrayWithDuplicates() {
        Integer[] array = {4, 2, 4, 3, 2};
        sorter.sort(array);
        assertArrayEquals(new Integer[]{2, 2, 3, 4, 4}, array);
    }

    @Test
    void testSortSingleElement() {
        Integer[] array = {42};
        sorter.sort(array);
        assertArrayEquals(new Integer[]{42}, array);
    }

    @Test
    void testSortEmptyArray() {
        Integer[] array = {};
        sorter.sort(array);
        assertArrayEquals(new Integer[]{}, array);
    }

    @Test
    void testSortStrings() {
        InsertionSort<String> stringSorter = new InsertionSort<>();
        String[] array = {"banana", "apple", "cherry"};
        stringSorter.sort(array);
        assertArrayEquals(new String[]{"apple", "banana", "cherry"}, array);
    }

    /** Large data set test */
    @Test
    void testLargeRandomArray() {
        int size = 10_000; // of 10_000 als het te langzaam is
        Integer[] array = new Integer[size];

        Random random = new Random(42);
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1_000_000);
        }

        // Maak een kopie voor dezelfde array om te vergelijken
        Integer[] expected = array.clone();

        //Voor referentie/vergelijking: javas eigen sort
        Arrays.sort(expected);

        // Sorteer met mijn insertion sort
        sorter.sort(array);

        // Vergelijk
        assertArrayEquals(expected, array);
    }
}
