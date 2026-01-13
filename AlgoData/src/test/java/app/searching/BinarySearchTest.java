package app.searching;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void insertAndFind() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        assertTrue(bst.insert(10));
        assertTrue(bst.insert(5));
        assertTrue(bst.insert(15));

        assertTrue(bst.find(10));
        assertTrue(bst.find(5));
        assertTrue(bst.find(15));
        assertFalse(bst.find(999));
    }

    @Test
    void insertDuplicateNotPossible() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        assertTrue(bst.insert(10));
        assertFalse(bst.insert(10));
    }

    @Test
    void toSortedListReturnsInorder() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(7);
        bst.insert(1);

        assertEquals(List.of(1, 5, 7, 10, 15), bst.TreeToSortedList());
    }

    @Test
    void findMinAndFindMaxWork() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(1);
        bst.insert(20);

        assertEquals(1, bst.findMin());
        assertEquals(20, bst.findMax());
    }

    @Test
    void removeReturnsFalseNotFound() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);

        assertFalse(bst.remove(999));
        assertEquals(List.of(5, 10, 15), bst.TreeToSortedList());
    }

    @Test
    void removeLeafNode() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);

        assertTrue(bst.remove(5));
        assertFalse(bst.find(5));
        assertEquals(List.of(10, 15), bst.TreeToSortedList());
    }

    @Test
    void removeNodeOneChild() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(2); // 5 heeft 1 left child

        assertTrue(bst.remove(5));
        assertEquals(List.of(2, 10), bst.TreeToSortedList());
    }

    @Test
    void removeNodeWithTwoChildrenUsingSuccessor() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(12);
        bst.insert(18);

        assertTrue(bst.remove(15)); // 15 heeft 2 children
        assertFalse(bst.find(15));
        assertEquals(List.of(5, 10, 12, 18), bst.TreeToSortedList());
    }

    @Test
    void removeRootCases() {
        // root als leaf
        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        bst1.insert(10);
        assertTrue(bst1.remove(10));
        assertEquals(List.of(), bst1.TreeToSortedList());

        // root met 1 child
        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
        bst2.insert(10);
        bst2.insert(20);
        assertTrue(bst2.remove(10));
        assertEquals(List.of(20), bst2.TreeToSortedList());

        // root met 2 children
        BinarySearchTree<Integer> bst3 = new BinarySearchTree<>();
        bst3.insert(10);
        bst3.insert(5);
        bst3.insert(15);
        bst3.insert(12);
        assertTrue(bst3.remove(10));
        assertEquals(List.of(5, 12, 15), bst3.TreeToSortedList());
    }
}