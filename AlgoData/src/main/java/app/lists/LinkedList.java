package app.lists;

import java.util.Objects;

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
     * TC: O(1)
     * @param element toe te voegen element
     */
    @Override
    public void add(T element) {
        add(size, element);
    }

    /**
     * Voegt een element toe op de die index plek
     * Maakt Head en Tail nodes aan als nodig
     * TC: best case O(1), worst case O(n)
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
     * TC: Best case O(1), worst case O(n)
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
     * TC: Best case O(1), worst case O(n)
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

    /**
     * Verwijdert het element op de opgegeven index
     * TC: Best case O(1), worst case O(n)
     * @param index plek van te verwijderen element
     * @return verwijderd element
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> removeNode;

        // verwijder head
        if (index == 0) {
            removeNode = head;
            head = head.next;

            if (size == 1) {
                tail = null;
            }

        } else {
            Node<T> current = head;

            // zet node naar voor diegene die we willen verwijderen
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            removeNode = current.next;
            current.next = removeNode.next;

            if (removeNode == tail) {
                tail = current;
            }
        }

        size--;
        return removeNode.data;
    }

    /**
     * Geeft de grootte van de lijst terug
     * TC: O(1)
     * @return grootte
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Controleert of het element in de lijst zit
     * Object.equals nodig voor null waarde vergelijking, anders indexOutOfBoundsException
     * TC: Best case O(1), worst case O(n)
     * @param element te controleren element
     * @return true if element in de lijst
     */
    @Override
    public boolean contains(T element) {
        Node<T> current = head;
        while (current != null) {
            if (Objects.equals(current.data, element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Vind de index van de eerste keer dat het opgegeven element in de lijst is
     * De else clause is nodig anders krijg je een NullPointerException bij het zoeken naar null
     * TC: Best case O(1), worst case O(n)
     * @param element te zoeken element
     * @return index van het element (-1 not found)
     */
    @Override
    public int indexOf(T element) {
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            if (Objects.equals(current.data, element)) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    /**
     * Leeg de lijst
     * TC: O(1)
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Controleerd of de lijst leeg is
     * TC: O(1)
     * @return boolean true als leeg
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
