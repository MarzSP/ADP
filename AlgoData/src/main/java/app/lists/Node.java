package app.lists;

/**
 * Node class voor LinkedList
 * Dit is het knooppunt die data en een verwijzing naar de volgende node bevat
 * @param <T> generic type
 */
public class Node<T> {
    T data;
    Node<T> next;      // package-private so existing LinkedList code in the same package keeps working
    Node<T> prev;      // optional: useful if you later implement a doubly-linked list

    public Node(T data) {
        this(data, null);
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
        this.prev = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}

