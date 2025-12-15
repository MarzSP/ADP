package app.searching;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementeert binaire zoekalgoritme
 * Een binaire zoekopdracht werkt alleen op gesorteerde arrays. Een binaire zoekopdracht halveert de zoekruimte telkens totdat het doel is gevonden of de zoekruimte leeg is.
 * Dez e implementatie gebruikt generics zodat het op elk vergelijkbaar type kan werken.
 * @param <T> type van de elementen (moet Comparable zijn)
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;
    private int size;

    /**
     * TC: O(1)
     * SC: O(1)
     * @param value toe te voegen waarde
     */
    public void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
            size++;
            return;
        }

        Node<T> current = root;

        while (true) {
            int cmp = value.compareTo(current.value);

            if (cmp < 0) {
                if (current.left == null) {
                    current.left = new Node<>(value);
                    size++;
                    return;
                }
                current = current.left;

            } else if (cmp > 0) {
                if (current.right == null) {
                    current.right = new Node<>(value);
                    size++;
                    return;
                }
                current = current.right;

            } else {
                // duplicate: negeren
                return;
            }
        }
    }

    /**
     * TC: O(h) met h de hoogte van de boom
     * SC: O(1)
     * @param value te zoeken waarde
     * @return true als de waarde bestaat
     */
    public boolean contains(T value) {
        Node<T> current = root;

        while (current != null) {
            int cmp = value.compareTo(current.value);

            if (cmp == 0) return true;
            if (cmp < 0) current = current.left;
            else current = current.right;
        }

        return false;
    }

    /**
     * TC: O(1)
     * SC: O(1)
     * @return aantal elementen in de boom
     */
    public int size() {
        return size;
    }

    /**
     * TC: O(h) met h de hoogte van de boom
     * SC: O(1)
     * @return minimum waarde
     */
    public T findMin() {
        if (root == null) return null;

        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    /**
     * TC: O(h) met h de hoogte van de boom
     * SC: O(1)
     * @return maximum waarde
     */
    public T findMax() {
        if (root == null) return null;

        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    /**
     * TC: O(n) omdat elke node bezocht wordt
     * SC: O(n) voor de lijst
     * @return lijst van elementen in inorder volgorde
     */
    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrderRecursive(root, result);
        return result;
    }

    /**
     * TC: O(n) omdat elke node bezocht wordt
     * SC: O(n) voor de lijst
     * @param current huidige node
     * @param result lijst om resultaten in op te slaan
     */
    private void inOrderRecursive(Node<T> current, List<T> result) {
        if (current == null) return;

        inOrderRecursive(current.left, result);
        result.add(current.value);
        inOrderRecursive(current.right, result);
    }
}