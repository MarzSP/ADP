package app.searching;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void bstWithIntegers() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);

        assertTrue(bst.contains(40));
        assertFalse(bst.contains(99));

        assertEquals(List.of(20, 30, 40, 50, 70), bst.TreeToSortedList());
    }

    @Test
    void bstWithStrings() {
        BinarySearchTree<String> bst = new BinarySearchTree<>();

        bst.add("M");
        bst.add("C");
        bst.add("T");
        bst.add("A");
        bst.add("E");

        assertTrue(bst.contains("E"));
        assertFalse(bst.contains("B"));

        assertEquals(List.of("A", "C", "E", "M", "T"), bst.TreeToSortedList());
    }

    @Test
    void emptyTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        assertFalse(bst.contains(1));
        assertEquals(List.of(), bst.TreeToSortedList());
    }

    @Test
    void duplicateIgnored() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        assertTrue(bst.add(10));
        assertFalse(bst.add(10));

        assertEquals(List.of(10), bst.TreeToSortedList());
    }

    @Test
    void singleNodeTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(42);

        assertTrue(bst.contains(42));
        assertEquals(List.of(42), bst.TreeToSortedList());
    }

    @Test
    void worstCaseScenarioTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(4);
        bst.add(5);

        assertTrue(bst.contains(5));
        assertEquals(List.of(1,2,3,4,5), bst.TreeToSortedList());
    }




}
