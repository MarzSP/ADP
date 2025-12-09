package app.priorityQueue;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T extends Comparable<T>> implements iPriorityQueue<T> {

    private final List<Entry<T>> list = new ArrayList<>();

    /**
     * TC: O(1)
     * @param value de waarde om op te slaan
     * @param priority  prioriteit (lager = meer prioriteit)
     */
    public void enqueue(T value, int priority) {
        list.add(new Entry<>(value, priority)); // O(1)
    }

    /**
     * Verwijder en return het element met de hoogste prioriteit
     * TC: O(n)
     * @return
     */
    public T dequeue() {
        if (list.isEmpty()) return null;
        return list.remove(findBestIndex()).value;
    }

    /**
     * Bekijk het element met de hoogste prioriteit zonder remove
     * TC: O(n)
     * @return
     */
    public T peek() {
        if (list.isEmpty()) return null;
        return list.get(findBestIndex()).value;
    }

    /**
     * Zoek de index van het element met de hoogste prioriteit
     * TC: O(n)
     * @return
     */
    private int findBestIndex() {
        int best = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(best)) < 0) {
                best = i;
            }
        }
        return best;
    }


    /**
     * Check of een waarde in de priority queue zit
     * TC: O(n)
     */
    public boolean contains(T value) {
        for (Entry<T> entry : list) {
            if (entry.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verwijder eerste voorkomen van een element op basis van waarde
     * TC: O(n)
     */
    public boolean remove(T value) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).value.equals(value)) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Update de priority van een bestaand element
     * TC: O(n)
     */
    public boolean updatePriority(T value, int newPriority) {
        for (Entry<T> entry : list) {
            if (entry.value.equals(value)) {
                entry.priority = newPriority;
                return true;
            }
        }
        return false;
    }
}
