package app.priorityQueue;
import java.util.LinkedList;

public class PriorityQueue<T> implements iPriorityQueue<T> {

    /**
     * Interne bucket:
     * één prioriteit met een FIFO-lijst van waarden
     */
    private static class Bucket<T> {
        int priority;
        LinkedList<T> values = new LinkedList<>();
        Bucket<T> next;

        Bucket(int priority) {
            this.priority = priority;
        }
    }

    private Bucket<T> head; // bucket met hoogste prioriteit
    private int size = 0;

    @Override
    public void enqueue(T value, int priority) {
        if (value == null) {
            throw new IllegalArgumentException("value mag niet null zijn");
        }

        // lege queue
        if (head == null) {
            head = new Bucket<>(priority);
            head.values.add(value);
            size++;
            return;
        }

        // nieuwe hoogste prioriteit
        if (priority < head.priority) {
            Bucket<T> bucket = new Bucket<>(priority);
            bucket.values.add(value);
            bucket.next = head;
            head = bucket;
            size++;
            return;
        }

        Bucket<T> current = head;
        Bucket<T> previous = null;

        // zoek juiste plek
        while (current != null && current.priority < priority) {
            previous = current;
            current = current.next;
        }

        // bucket met zelfde prioriteit gevonden
        if (current != null && current.priority == priority) {
            current.values.add(value);
        } else {
            // nieuwe bucket invoegen
            Bucket<T> bucket = new Bucket<>(priority);
            bucket.values.add(value);
            bucket.next = current;
            previous.next = bucket;
        }

        size++;
    }

    @Override
    public T dequeue() {
        if (head == null) {
            return null;
        }

        T value = head.values.removeFirst();
        size--;

        if (head.values.isEmpty()) {
            head = head.next;
        }

        return value;
    }

    @Override
    public T peek() {
        if (head == null) return null;
        return head.values.getFirst();
    }

    @Override
    public boolean contains(T value) {
        for (Bucket<T> b = head; b != null; b = b.next) {
            if (b.values.contains(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(T value) {
        Bucket<T> previous = null;
        Bucket<T> current = head;

        while (current != null) {
            if (current.values.remove(value)) {
                size--;

                // bucket leeg? verwijder hem
                if (current.values.isEmpty()) {
                    if (previous == null) {
                        head = current.next;
                    } else {
                        previous.next = current.next;
                    }
                }
                return true;
            }
            previous = current;
            current = current.next;
        }

        return false;
    }

    @Override
    public boolean updatePriority(T value, int newPriority) {
        // Eerst verwijderen
        if (!remove(value)) {
            return false;
        }
        //opnieuw invoegen met nieuwe prioriteit
        enqueue(value, newPriority);
        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}