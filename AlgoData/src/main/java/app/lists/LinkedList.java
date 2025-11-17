package app.lists;

/**
 * LinkedList implementation of iList interface.
 * Een LinkedList is geen arrayachtige, dus elementen hoeven niet te verschuiven. Alleen de verwijzingen van de nodes worden aangepast.
 * @param <T> - generic type
 */
public class LinkedList<T> implements iList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Voeg element aan einde van lijst toe
     * @param element toe te voegen element
     */
    @Override
    public void add(T element) {
        add(size, element);
    }

    /**
     * Voegt een element toe op de die index plek
     * Maakt Head en Tail nodes aan als nodig
     * @param index plek waarop element toegevoegd moet worden
     * @param element toe te voegen element
     */
    @Override
    public void add(int index, T element) {
     if (index < 0 || index > size) {
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
     }
        Node<T> newNode = new Node<>(element);
     //begin
     if (index == 0) {
         newNode.next = head;
            head = newNode;
            if (size == 0) {
                tail = newNode;
            }
        //einde
        } else if (index == size) {
            tail.next = newNode;
            tail = newNode;
        }
        //midden
        else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
     }

        size++;
    }

    /**
     * Haalt het element op de opgegeven index op
     * @param index index van ophalen element
     * @return element op die index
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }
    /**
     * Zet het opgegeven element op de opgegeven index
     * @param index positie waar element gezet moet worden
     * @param element te zetten element
     * @return oude element dat op die plek stond
     */
    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index" + index + ", Size: " + size);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        T oldValue = current.data;
        current.data = element;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public int indexOf(T element) {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
