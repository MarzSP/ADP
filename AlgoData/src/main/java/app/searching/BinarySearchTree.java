package app.searching;

import java.util.ArrayList;
import java.util.List;
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
    public boolean add(T value) {
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
    public boolean contains(T value) {
        Objects.requireNonNull(value, "waarde mag geen null zijn");

        TreeNode<T> current = root;
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
     * returns lijst met waarden in volgorde
     */
    public List<T> TreeToSortedList() {
        return ListInOrder(root);
    }

    /**
     * TC: Best/worst/average O(n^2). Loop door alle nodes en voeg 1 voor 1 toe aan result, voor iedere TreeNode opnieuw
     * SC: O(N) voor de lijst. (En recursie is de stack maximaal de hoogte van de boom O(N) in het slechtste geval)
     * @param TreeNode root van de (sub)tree
     * @return list met waarden in volgorde
     */
    private List<T> ListInOrder(TreeNode<T> TreeNode) {
        List<T> result = new ArrayList<>();

        if (TreeNode == null) {
            return result;
        }

        //recursief links, wortel, recursief rechts
        result.addAll(ListInOrder(TreeNode.left));
        result.add(TreeNode.value);
        result.addAll(ListInOrder(TreeNode.right));

        return result;
    }

}
