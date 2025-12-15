package app.searching;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void newNodeHasValueAndNullChildren() {
        Node<Integer> node = new Node<>(42);

        assertEquals(42, node.value);
        assertNull(node.left);
        assertNull(node.right);
    }

    @Test
    void canLinkLeftAndRightChildren() {
        Node<Integer> parent = new Node<>(10);
        Node<Integer> left = new Node<>(5);
        Node<Integer> right = new Node<>(15);

        parent.left = left;
        parent.right = right;

        assertSame(left, parent.left);
        assertSame(right, parent.right);
        assertEquals(5, parent.left.value);
        assertEquals(15, parent.right.value);
    }
}
