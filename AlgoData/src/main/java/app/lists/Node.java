package app.lists;

/**
 * Node class voor LinkedList
 * Dit is het knooppunt die data en een verwijzing naar de volgende node bevat
 * @param <T> generic type
 */
public class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}
