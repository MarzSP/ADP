package app.searching;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Boom leeg? Dan maak eerst de root
 * Met vergelijken: kleiner naar links, groter naar rechts
 * Geen duplicaten mogelijk: als de waarde al bestaat kan de vergelijking de boom scheef maken. Vaste orde houden.
 * @param <T> Generic type die comparable is
 */
public class BinarySearchTree<T extends Comparable<T>> {

   private static class TreeNode<T> {
        T value;
        TreeNode<T> left;
        TreeNode<T> right;

        TreeNode(T value) {
            this.value = value;
        }
    }

    private TreeNode<T> root;
    private int size;

    /**
     * Voegt waarde toe aan de boom
     * TC: Best O(log n) bij gebalanceerde boom, worst O(n) bij scheve boom
     * SC: O(1)
     * @param value toe te voegen waarde
     * @return true als waarde is toegevoegd
     */
    public boolean insert(T value) {
        Objects.requireNonNull(value, "waarde mag geen null zijn");

        // Boom leeg? dan wordt 1ste waarde root
        if (root == null) {
            root = new TreeNode<>(value);
            size = 1;
            return true;
        }

        TreeNode<T> current = root;
        while (true) {
            int comparable = value.compareTo(current.value);

            if (comparable == 0) {
                return false;
            } else if (comparable < 0) {
                if (current.left == null) {
                    current.left = new TreeNode<>(value);
                    size++;
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new TreeNode<>(value);
                    size++;
                    return true;
                }
                current = current.right;
            }
        }
    }

    /**
     * Zoekt of waarde in de boom zit
     * TC: Best O(log n) bij gebalanceerde boom, worst O(n) bij scheve boom
     * SC: O(1)
     * @param value te zoeken waarde
     * @return true als waarde is gevonden
     */
    public boolean find(T value) {
        Objects.requireNonNull(value, "waarde mag geen null zijn");

        TreeNode<T> current = root;
        while (current != null) {
            int comparable = value.compareTo(current.value);
            if (comparable == 0) return true;
            current = (comparable < 0) ? current.left : current.right;
        }
        return false;
    }

    /**
     * In-order traversal voor een gesorteerde lijst (recursief met inOrderFill)
     * TC: O(n)
     * SC: O(n)
     */
    public List<T> TreeToSortedList() {
        List<T> result = new ArrayList<>(Math.max(0, size));
        inorderFill(root, result);
        return result;
    }

    /**
     * Recursief met TreeToSortedList - voor in-order traversal
     * @param node
     * @param out
     */
    private void inorderFill(TreeNode<T> node, List<T> out) {
        if (node == null) return;
        inorderFill(node.left, out);
        out.add(node.value);
        inorderFill(node.right, out);
    }

    /**
     * Vindt minimum waarde in de boom TC: O(h) hoogte van de boom
     * @return kleinste waarde
     * @throws NoSuchElementException lege boom
     */
    public T findMin() {
        if (root == null) throw new NoSuchElementException("Tree is empty");
        TreeNode<T> current = root;
        while (current.left != null) current = current.left;
        return current.value;
    }

    /**
     * Vindt maximum waarde in de boom TC: O(h) hoogte van de boom
     * @return grootste waarde
     * @throws NoSuchElementException lege boom
     */
    public T findMax() {
        if (root == null) throw new NoSuchElementException("Tree is empty");
        TreeNode<T> current = root;
        while (current.right != null) current = current.right;
        return current.value;
    }

    /**
     * Verwijdert waarde uit de boom en behoudt de BST structuur door de juiste child nodes weer te koppelen
     * TC: O(h) hoogte van de boom
     * SC: O(1)
     * @param value te removen waarde
     * @return true als waarde is verwijderd
     */
    public boolean remove(T value) {
        Objects.requireNonNull(value, "value cant be null");

        TreeNode<T> parentNode = null;
        TreeNode<T> currentNode = root;

        //vind node om te verwijderen
        while (currentNode != null) {
            int comparison = value.compareTo(currentNode.value);
            if (comparison == 0) break;
            parentNode = currentNode;
            currentNode = (comparison < 0) ? currentNode.left : currentNode.right;
        }
        if (currentNode == null) return false;

        // als 2 children: vind inorder successor
        // kopieer successor waarde naar currentNode, verwijder successor node
        if (currentNode.left != null && currentNode.right != null) {
            TreeNode<T> nextHigherParent = currentNode;
            TreeNode<T> nextHigherNode = currentNode.right;
            while (nextHigherNode.left != null) {
                nextHigherParent = nextHigherNode;
                nextHigherNode = nextHigherNode.left;
            }
            currentNode.value = nextHigherNode.value;
            parentNode = nextHigherParent;
            currentNode = nextHigherNode;
        }

        // currentNode heeft nu max 1 child
        TreeNode<T> child = (currentNode.left != null) ? currentNode.left : currentNode.right;
        if (parentNode == null) {
            root = child;
        } else if (parentNode.left == currentNode) {
            parentNode.left = child;
        } else {
            parentNode.right = child;
        }

        size--;
        return true;
    }
}
