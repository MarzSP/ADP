package app.searching;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinarySearchTreeVerbetering<T extends Comparable<T>> {

    private static class TreeNode<T> {
        T value;
        BinarySearchTreeVerbetering.TreeNode<T> left;
        BinarySearchTreeVerbetering.TreeNode<T> right;

        TreeNode(T value) {
            this.value = value;
        }
    }

    private BinarySearchTreeVerbetering.TreeNode<T> root;
    private int size;

    /**
     * Voegt waarde toe aan de boom
     */
    public boolean add(T value) {
        Objects.requireNonNull(value, "waarde mag geen null zijn");

        if (root == null) {
            root = new BinarySearchTreeVerbetering.TreeNode<>(value);
            size = 1;
            return true;
        }

        BinarySearchTreeVerbetering.TreeNode<T> current = root;
        while (true) {
            int comparable = value.compareTo(current.value);

            if (comparable == 0) {
                return false;
            } else if (comparable < 0) {
                if (current.left == null) {
                    current.left = new BinarySearchTreeVerbetering.TreeNode<>(value);
                    size++;
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new BinarySearchTreeVerbetering.TreeNode<>(value);
                    size++;
                    return true;
                }
                current = current.right;
            }
        }
    }

    /**
     * Zoekt of waarde in de boom zit
     */
    public boolean contains(T value) {
        Objects.requireNonNull(value, "waarde mag geen null zijn");

        BinarySearchTreeVerbetering.TreeNode<T> current = root;
        while (current != null) {
            int comparable = value.compareTo(current.value);
            if (comparable == 0) return true;
            current = (comparable < 0) ? current.left : current.right;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * In-order traversal voor een gesorteerde lijst
     * Optimized: vult één lijst via een helper -> O(n) time
     */
    public List<T> TreeToSortedList() {
        List<T> result = new ArrayList<>(Math.max(0, size));
        inorderFill(root, result);
        return result;
    }

    private void inorderFill(BinarySearchTreeVerbetering.TreeNode<T> node, List<T> out) {
        if (node == null) return;
        inorderFill(node.left, out);
        out.add(node.value);
        inorderFill(node.right, out);
    }
}

