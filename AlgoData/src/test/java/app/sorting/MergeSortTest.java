package app.sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

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

    /** Grote datasets testen
     *  Deze tests testen hoe de merge sort omgaat met grote datasets.
     *  Dus random gegenereerde arrays, al gesorteerde arrays en omgekeerd gesorteerde arrays.
     *  Deze worden gecloned en vergeleken met de standaard Java sorteer methode.
     *  De int size zijn ingesteld op 10.000 elementen.
     */
    @Test
    void testLargeRandomArray() {
        int size = 10_000;
        Integer[] array = new Integer[size];

        Random random = new Random(42); 
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1_000_000);
        }

        Integer[] expected = array.clone();
        Arrays.sort(expected); // javas eigen standaard sorteer method

        sorter.sort(array); // mijn merge sort

        assertArrayEquals(expected, array); // vergelijken
    }

    @Test
    void testLargeAlreadySortedArray() {
        int size = 10_000;
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = i;
        }

        Integer[] expected = array.clone();
        sorter.sort(array);

        assertArrayEquals(expected, array);
    }

    @Test
    void testLargeReverseSortedArray() {
        int size = 10_000;
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }

        Integer[] expected = array.clone();
        Arrays.sort(expected);

        sorter.sort(array);

        assertArrayEquals(expected, array);
    }

    @Test
    void optimizationReducesMergeCallsOnSortedInput() {
        Integer[] sorted = new Integer[10_000];
        for (int i = 0; i < sorted.length; i++) sorted[i] = i;

        MergeSort<Integer> ms = new MergeSort<>();
        ms.sort(sorted);

        long mergesSorted = ms.getMergeCalls();

        Integer[] random = new Integer[10_000];
        java.util.Random r = new java.util.Random(42);
        for (int i = 0; i < random.length; i++) random[i] = r.nextInt();

        MergeSort<Integer> ms2 = new MergeSort<>();
        ms2.sort(random);
        long mergesRandom = ms2.getMergeCalls();
        System.out.println("mergesSorted=" + mergesSorted + ", mergesRandom=" + mergesRandom);
        assertTrue(mergesSorted == mergesRandom);
    }

}
