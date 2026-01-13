package app.searching;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Implementatie generic BST
 * Gebruikt inner class TreeNode voor nodes omdat deze niet buiten de boom gebruikt hoeft te worden
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
     * Boom leeg? Dan maak eerst de root
     * Met vergelijken: kleiner naar links, groter naar rechts
     * Geen duplicaten mogelijk: als de waarde al bestaat kan de vergelijking de boom scheef maken. Vaste orde houden.
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
     * Start bij root en vergelijk, ga links of rechts afhankelijk van de vergelijking
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
     * TC: O(n) elke node wordt 1x bezocht
     * SC: O(n) lijst met alle waarden
     * @return gesorteerde lijst van waarden in de boom
     */
    public List<T> TreeToSortedList() {
        List<T> result = new ArrayList<>(Math.max(0, size));
        inorderFill(root, result);
        return result;
    }

    /**
     * Recursief helper voor in-order traversal
     * Zoekt eerst links, dan huidige node, dan rechts
     * TC: O(n) elke node wordt 1x bezocht
     * SC:: O(log n) gebruikt stack ruimte voor recursie (Worst O(n) bij scheve boom)
     * @param node huidige node
     * @param out output lijst
     */
    private void inorderFill(TreeNode<T> node, List<T> out) {
        if (node == null) return;
        inorderFill(node.left, out);
        out.add(node.value);
        inorderFill(node.right, out);
    }

    /**
     * Vindt minimum waarde in de boom
     * TC: Best O(1) als root de kleinste is, worst O(h) hoogte van de boom
     * SC: O(1)
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
     * Vindt maximum waarde in de boom
     * TC: Best O(1) als root de grootste is, worst O(h) hoogte van de boom
     * SC: O(1)
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
     * Verwijderd recursief waarde uit boom met behoud van BST structuur
     * Zoek eerst de waarde, dan 3 cases:
     * 1. Node heeft geen children: return null
     * 2. Node heeft 1 child: return die child
     * 3. Node heeft 2 children: vind successor (kleinste in rechter subtree), kopieer waarde, verwijder successor
     * TC:O(log n) bij gebalanceerde boom, worst O(n) bij scheve boom
     * SC:O(log n) gebruikt stack ruimte voor recursie (Worst O(n) bij scheve boom)
     * @param node huidige node
     * @param value te verwijderen
     * @return aangepaste subtree root
     */
    private TreeNode<T> deleteNode(TreeNode<T> node, T value) {
        if (node == null) return null;

        int comparison = value.compareTo(node.value);
        if (comparison < 0) {
            node.left = deleteNode(node.left, value);
        } else if (comparison > 0) {
            node.right = deleteNode(node.right, value);
        } else {
            size--;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            TreeNode<T> successor = node.right;
            while(successor.left != null) {
                successor = successor.left;
            }

            node.value = successor.value;

            size++; // compensatie voor de decrement eerder
            node.right = deleteNode(node.right, successor.value);
        }
        return node;
    }

    /**
     * Verwijdert waarde uit de boom
     * Onthoudt de huidige grootte van de boom -> roept deleteNode aan -> vergelijkt grootte na verwijdering
     * TC: Best case: O(log n) gebalanceerde boom en worst case: O(n) scheve boom
     * SC: O(h) hoogte van de boom vanwege recursie
     * @param value te removen waarde
     * @return true als waarde is verwijderd
     */
    public boolean remove(T value) {
        Objects.requireNonNull(value, "waarde mag geen null zijn");
        int oldSize = size;

        root = deleteNode(root, value);

        return size != oldSize;
    }
}
