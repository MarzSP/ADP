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
    } //TODO: checklist types

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

    private TreeNode<T> delNode(TreeNode<T> node, T value) {
        if (node == null) return null;

        int comparison = value.compareTo(node.value);
        if (comparison < 0) {
            node.left = delNode(node.left, value);
        } else if (comparison > 0) {
            node.right = delNode(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            TreeNode<T> successor = node.right;
            while(successor.left != null) {
                successor = successor.left;
            }

            node.value = successor.value;
            node.right = delNode(node.right, successor.value);
        }
        return node;
    }

    private boolean removeRecursive(T value, TreeNode<T> currentNode, TreeNode<T> parentNode) {
        int comparison = value.compareTo(currentNode.value);
        if (comparison < 0) {
            if (currentNode.left == null) {
                return false;
            }
            return removeRecursive(value, currentNode.left, currentNode);
        }

        if (comparison > 0) {
            if (currentNode.right == null) {
                return false;
            }
            return removeRecursive(value, currentNode.right, currentNode);
        }

        // element gevonden
        if (currentNode.left == null && currentNode.right == null && parentNode != null) {
            // childless, simply remove', ToDo improve
            if (parentNode.left == currentNode) {
                parentNode.left = null;
            } else {
                parentNode.right = null;
            }
            return true;
        }

        if (currentNode.left != null && currentNode.right == null && parentNode != null) {
            if (parentNode.left == currentNode) {
                parentNode.left = currentNode.left;
            } else {
                parentNode.right = currentNode.left;
            }
            return true;
        }

        if (currentNode.right != null && currentNode.left == null && parentNode != null) {
            if (parentNode.right == currentNode) {
                parentNode.right = currentNode.right;
            } else {
                parentNode.left = currentNode.right;
            }
            return true;
        }

        // both currentNode.right and left is set
        if (parentNode.right == currentNode) {
            parentNode = currentNode.right;
        } else {
            parentNode = currentNode.right;
        }
        return true;
    }

    /**
     * Verwijdert waarde uit de boom en behoudt de BST structuur door de juiste child nodes weer te koppelen
     * TC: O(h) hoogte van de boom
     * SC: O(1)
     * @param value te removen waarde
     * @return true als waarde is verwijderd
     */
    public boolean remove(T value) {
        TreeNode<T> result = delNode(root, value);
        return result != null;
    }
    public boolean removeOld(T value) {
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
        if (currentNode == null) return false;//TODO: als ie niet gevonden is

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
    //TODO: recursief zoeken naar de
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
