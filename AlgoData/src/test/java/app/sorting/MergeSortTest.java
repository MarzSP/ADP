package app.sorting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    private final MergeSort<Integer> sorter = new MergeSort<>();

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
        MergeSort<String> stringSorter = new MergeSort<>();
        String[] array = {"banana", "apple", "cherry"};
        stringSorter.sort(array);
        assertArrayEquals(new String[]{"apple", "banana", "cherry"}, array);
    }
}
