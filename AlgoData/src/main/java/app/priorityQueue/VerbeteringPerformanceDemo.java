package app.priorityQueue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * De enqueueManyDifferentPrioritiesCanBeSlow()-test van PriorityQueue laat zien dat enqueue behoorlijk langzaam kan worden met groeinde input.
 * Dit komt doordat de linked list van buckets doorlopen moet worden om de juiste plek te vinden voor de nieuwe prioriteit. O(n) in het slechtste geval.
 * Een verbetering is om een Map te gebruiken om direct de bucket voor een bepaalde prioriteit te vinden.
 * Dit maakt het zoeken naar de juiste bucket O(1) gemiddeld, waardoor enqueue veel sneller wordt bij veel verschillende prioriteiten.
 * Deze hypothese wordt hier getest
 */
public class VerbeteringPerformanceDemo<T> implements iPriorityQueue<T> {

    static class Bucket<T> {
        int priority;
        LinkedList<T> values = new LinkedList<>();
        Bucket<T> next;

        Bucket(int priority) {
            this.priority = priority;
        }
    }

    private Bucket<T> head;
    private int size = 0;

    // De verbetering: direct bucket vinden op priority
    private final Map<Integer, Bucket<T>> bucketIndex = new HashMap<>();

    @Override
    public void enqueue(T value, int priority) {
        if (value == null) {
            throw new IllegalArgumentException("value mag niet null zijn");
        }

        // O(1) gemiddeld: direct bucket lookup
        Bucket<T> bucket = bucketIndex.get(priority);
        if (bucket != null) {
            bucket.values.addLast(value);
            size++;
            return;
        }

        // Bucket bestaat nog niet dus maak hem en voeg op juiste plek toe in de bucket-linked-list
        Bucket<T> newBucket = new Bucket<>(priority);
        newBucket.values.addLast(value);

        if (head == null) {
            head = newBucket;
            bucketIndex.put(priority, newBucket);
            size++;
            return;
        }

        // nieuwe hoogste prioriteit
        if (priority < head.priority) {
            newBucket.next = head;
            head = newBucket;
            bucketIndex.put(priority, newBucket);
            size++;
            return;
        }

        Bucket<T> current = head;
        Bucket<T> previous = null;

        while (current != null && current.priority < priority) {
            previous = current;
            current = current.next;
        }

        size++;
    }


    @Override
    public T dequeue() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public boolean contains(T value) {
        return false;
    }

    @Override
    public boolean remove(T value) {
        return false;
    }

    @Override
    public boolean updatePriority(T value, int newPriority) {
        return false;
    }

}