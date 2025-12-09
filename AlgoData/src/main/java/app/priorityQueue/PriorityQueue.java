package app.priorityQueue;

import java.util.Arrays;

public class PriorityQueue<T extends Comparable<T>> implements iPriorityQueue<T> {

    private T[] heap;
    private int size;

    /**
     * Constructor: suppressWarnings omdat Java geen generieke arrays toestaat
     * TC: O(1)
     * SC: O(n)
     */
    @SuppressWarnings("unchecked")
    public PriorityQueue() {
        heap = (T[]) new Comparable[10];
        size = 0;
    }

    /**
     * Voeg element toe aan de priority queue
     * TC: O(log n)
     * @param element toe te voegen element
     */
    @Override
    public void enqueue(T element) {
        ensureCapacity(size + 1);
        heap[size] = element;
        heapifyUp(size);
        size++;
    }

    /**
     * Verwijder en return het element met de hoogste prioriteit (laagste waarde)
     * TC: O(log n)
     * @return verwijderd element
     */
    @Override
    public T dequeue() {
        if (isEmpty()) return null;

        T removed = heap[0];
        heap[0] = heap[size - 1];
        size--;

        heapifyDown(0);
        return removed;
    }

    /**
     * Controleer of de priority queue leeg is
     * TC: O(1)
     * @return true als leeg
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return de grootte van de priority queue
     * TC: O(1)
     * @return grootte
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Peek = kijk naar het element met de hoogste prio zonder deze te verwijderen
     * TC: O(1)
     * @return element met hoogste prio
     */
    @Override
    public T peek() {
        if (isEmpty()) return null;
        return heap[0];
    }

    /**
     * Zorg dat de heap genoeg capaciteit heeft
     * TC: Best case O(1), worst case O(n)
     * SC: O(n) bij resize
     * @param minCapacity min. capaciteit
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    /**
     *  Maakt de heap geldig door een element omhoog te verplaatsen
     *  TC: O(log n)
     * @param index index van het te verplaatsen element
     */
    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;

        while (index > 0 && heap[index].compareTo(heap[parent]) < 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    /**
     * Maakt de heap geldig door een element omlaag te verplaatsen
     * Verwijder altijd het root element dus begin altijd bij index 0
     * SuppressWarnings omdat de parameter altijd 0 is bij aanroep
     * TC: O(log n)
     * @param index index van het te verplaatsen element
     */
    @SuppressWarnings( "SameParameterValue" )
    private void heapifyDown(int index) {
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = index;

            if (left < size && heap[left].compareTo(heap[smallest]) < 0) {
                smallest = left;
            }

            if (right < size && heap[right].compareTo(heap[smallest]) < 0) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }
    }

    /**
     * Wissel twee elementen in de heap
     * TC: O(1)
     * @param i index van het eerste element
     * @param j index van het tweede element
     */
    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
