package app.priorityQueue;
import java.util.LinkedList;

public class PriorityQueue<T> implements iPriorityQueue<T> {

    /**
     * Interne bucket: een prio met een FIFO-lijst van waarden
     * TC: O(1) voor add/remove aan begin/eind lijst
     * SC: O(n) voor n elementen in de lijst
     */
    private static class Bucket<T> {
        int priority;
        LinkedList<T> values = new LinkedList<>();
        Bucket<T> next;

        Bucket(int priority) {
            this.priority = priority;
        }
    }

    private Bucket<T> head;
    private int size = 0;

    /**
     * Voeg een nieuw element toe aan de queue met een prio
     * TC: O(n) in het slechtste geval (zoeken juiste plek)
     * SC: O(1) extra ruimte
     * @param value waarde om op te slaan
     * @param priority prio
     */
    @Override
    public void enqueue(T value, int priority) {
        if (value == null) {
            throw new IllegalArgumentException("value mag niet null zijn");
        }

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

        // bucket met zelfde prio gevonden
        if (current != null && current.priority == priority) {
            current.values.add(value);
        } else {
            Bucket<T> bucket = new Bucket<>(priority);
            bucket.values.add(value);
            bucket.next = current;
            previous.next = bucket;
        }
        size++;
    }

    /**
     * Haalt en verwijdert het element met de hoogste prioriteit.
     * Bij gelijke prioriteit wordt FIFO-volgorde gebruikt.
     * TC: O(1)
     * SC: O(1)
     * @return
     */
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

    /**
     * TC: O(1)
     * SC: O(1)
     * @return waarde || null als leeg
     */
    @Override
    public T peek() {
        if (head == null) return null;
        return head.values.getFirst();
    }

    /**
     * TC: O(n) worst case
     * SC: O(1)
     * @param value
     * @return true als aanwezig
     */
    @Override
    public boolean contains(T value) {
        for (Bucket<T> b = head; b != null; b = b.next) {
            if (b.values.contains(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * TC: O(n) worst case
     * SC: O(1)
     * @param value  te verwijderen waarde
     * @return true als gevonden + verwijderd
     */
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

    /**
     * TC: O(n) worst case
     * SC: O(1)
     * @param value  waarvan de prio wordt aangepast
     * @param newPriority nieuwe prio
     * @return true als gevonden + bijgewerkt
     */
    @Override
    public boolean updatePriority(T value, int newPriority) {
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