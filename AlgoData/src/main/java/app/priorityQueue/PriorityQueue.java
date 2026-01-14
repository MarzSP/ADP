package app.priorityQueue;

import java.util.NoSuchElementException;

/**
 * PQ implementatie als een gesorteerde single linked list
 * Lowest value = highest priority
 * insert:    O(n)
 * findMin:   O(1)
 * removeMin: O(1)
 */
public class PriorityQueue<T> {

    private static class Node<T> {
        T value;
        int priority;
        Node<T> next;

        Node(T value, int priority) {
            this.value = value;
            this.priority = priority;
        }
    }

    private Node<T> head; // altijd de hoogste prioriteit (laagste nr)
    private int size;

    /**
     * insert altijd op de juiste plek om de lijst gesorteerd te houden
     * TC: O(n) in het slechtste geval (toevoegen achteraan) Best O(1) (toevoegen vooraan)
     */
    public void insert(T value, int priority) {
        Node<T> newNode = new Node<>(value, priority);

        // Case 1: leeg Of new node wordt de head
        if (head == null || priority < head.priority) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        // Case 2: vindt de plek om te inserten
        Node<T> current = head;
        while (current.next != null && current.next.priority <= priority) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    /**
     * Vindt de kleinste waarde (hoogste prioriteit)
     *
     * @return de waarde met de hoogste prioriteit
     * @throws NoSuchElementException als de queue leeg is
     */
    public T findMin() {
        if (head == null) {
            throw new NoSuchElementException("PriorityQueue is empty");
        }
        return head.value;
    }

    /**
     * Verwijdert de waarde met de hoogste prio
     *
     * @return verwijderde waarde
     * @throws NoSuchElementException als de queue leeg is
     */
    public T removeMin() {
        if (head == null) {
            throw new NoSuchElementException("PriorityQueue is empty");
        }

        T result = head.value;
        head = head.next;
        size--;
        return result;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
}