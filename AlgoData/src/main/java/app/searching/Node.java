package app.searching;

/**
 * Node/Knoop voor BST
 * Dit is enkel een hulpclass met een constructor en velden.
 * De velden bevatten Node<T> links en rechts voor de linker en rechter children
 * @param <T>
 */
public class Node<T extends Comparable<T>> {
    T value;
    Node<T> left;
    Node<T> right;

    Node(T value) {
        this.value = value;
    }
}
