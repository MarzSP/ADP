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

    /** Grote datasets testen
     *  Deze tests testen hoe de insertion sort omgaat met grote datasets.
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

        // Maak een kopie voor dezelfde array om te vergelijken
        Integer[] expected = array.clone();

        //Voor referentie/vergelijking: javas eigen sort
        Arrays.sort(expected);

        // Sorteer met mijn insertion sort
        sorter.sort(array);

        // Vergelijk
        assertArrayEquals(expected, array);
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

    static Integer[] nearlySorted(int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) a[i] = i;

        // Maak 'm een beetje rommelig: 10 swaps
        java.util.Random r = new java.util.Random(42);
        for (int s = 0; s < 10; s++) {
            int i = r.nextInt(n);
            int j = r.nextInt(n);
            Integer tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
        return a;
    }

    @Test
    void insertionSortBeforeOptimization() {
        Integer[] a = nearlySorted(20_000);

        InsertionSort<Integer> sort = new InsertionSort<>();
        sort.sort(a);

        System.out.println(
                "AFTER -> comparisons=" + sort.getComparisons() +
                        ", shifts=" + sort.getShifts() +
                        ", whileChecks=" + sort.getWhileCount()
        );
    }

}
