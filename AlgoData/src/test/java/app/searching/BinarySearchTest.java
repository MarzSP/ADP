package app.searching;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void bstWithIntegers() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);

        assertTrue(bst.find(40));
        assertFalse(bst.find(99));

        assertEquals(List.of(20, 30, 40, 50, 70), bst.TreeToSortedList());
    }

    @Test
    void bstWithStrings() {
        BinarySearchTree<String> bst = new BinarySearchTree<>();

        bst.insert("M");
        bst.insert("C");
        bst.insert("T");
        bst.insert("A");
        bst.insert("E");

        assertTrue(bst.find("E"));
        assertFalse(bst.find("B"));

        assertEquals(List.of("A", "C", "E", "M", "T"), bst.TreeToSortedList());
    }

    @Test
    void emptyTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        assertFalse(bst.find(1));
        assertEquals(List.of(), bst.TreeToSortedList());
    }

    @Test
    void duplicateIgnored() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        assertTrue(bst.insert(10));
        assertFalse(bst.insert(10));

        assertEquals(List.of(10), bst.TreeToSortedList());
    }

    @Test
    void singleNodeTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.insert(42);

        assertTrue(bst.find(42));
        assertEquals(List.of(42), bst.TreeToSortedList());
    }

    @Test
    void worstCaseScenarioTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.insert(1);
        bst.insert(2);
        bst.insert(3);
        bst.insert(4);
        bst.insert(5);

        assertTrue(bst.find(5));
        assertEquals(List.of(1,2,3,4,5), bst.TreeToSortedList());
    }




}
