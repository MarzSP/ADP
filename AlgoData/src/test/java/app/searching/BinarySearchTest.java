package app.searching;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void insertAndContainsWork() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.insert(8);
        bst.insert(3);
        bst.insert(10);

        assertTrue(bst.contains(8));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(10));
        assertFalse(bst.contains(999));
    }

    @Test
    void sizeStartsAtZeroAndIncreasesOnUniqueInsert() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        assertEquals(0, bst.size());

        bst.insert(5);
        bst.insert(2);
        bst.insert(8);

        assertEquals(3, bst.size());
    }

    @Test
    void duplicateValuesAreIgnored() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.insert(5);
        bst.insert(5);
        bst.insert(5);

        assertEquals(1, bst.size());
        assertTrue(bst.contains(5));
    }

    @Test
    void findMinAndFindMaxReturnNullOnEmptyTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        assertNull(bst.findMin());
        assertNull(bst.findMax());
    }

    @Test
    void findMinAndFindMaxWork() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.insert(8);
        bst.insert(3);
        bst.insert(10);
        bst.insert(1);
        bst.insert(14);

        assertEquals(1, bst.findMin());
        assertEquals(14, bst.findMax());
    }

    @Test
    void inOrderReturnsSortedValues() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] values = {8, 3, 10, 1, 6, 14, 4, 7, 13};

        for (int v : values) bst.insert(v);

        List<Integer> inOrder = bst.inOrder();

        List<Integer> expected = Arrays.stream(values)
                .boxed()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        assertEquals(expected, inOrder);
    }

    @Test
    void worstCaseShapeStillBehavesCorrectly_sortedInsert() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        for (int i = 1; i <= 20; i++) {
            bst.insert(i); // worst-case (scheef) bij simpele BST
        }

        assertEquals(20, bst.size());
        assertTrue(bst.contains(1));
        assertTrue(bst.contains(20));
        assertFalse(bst.contains(999));

        // inOrder moet precies 1..20 zijn
        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i <= 20; i++) expected.add(i);

        assertEquals(expected, bst.inOrder());
        assertEquals(1, bst.findMin());
        assertEquals(20, bst.findMax());
    }

    @Test
    void largeTree_randomInsert_containsAndInOrderAreCorrect() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        int n = 10_000;
        Random rnd = new Random(12345);

        // use a Set so we know how many uniques we inserted
        Set<Integer> inserted = new HashSet<>(n);
        while (inserted.size() < n) {
            inserted.add(rnd.nextInt(200_000));
        }

        for (int v : inserted) bst.insert(v);

        assertEquals(inserted.size(), bst.size());

        // spot-check: all inserted values should be found
        int checks = 200;
        Iterator<Integer> it = inserted.iterator();
        for (int i = 0; i < checks && it.hasNext(); i++) {
            assertTrue(bst.contains(it.next()));
        }

        // inOrder should be sorted and contain exactly all inserted values
        List<Integer> inOrder = bst.inOrder();
        List<Integer> expected = inserted.stream().sorted().collect(Collectors.toList());

        assertEquals(expected, inOrder);
    }
}
