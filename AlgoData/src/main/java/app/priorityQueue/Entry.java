package app.priorityQueue;

/**
 * Een element in de priority queue. Deze bevat een value en een priority.
 * We kunnen elementern vergelijken op basis van hun priority.
 * TC: O(1)
 * SC: O(1)
 * @param <T>
 */
public class Entry<T> implements Comparable<Entry<T>> {
    final T value;
    int priority;

    public Entry(T value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    /**
     * Vergelijkt deze entry met een andere op basis van priority
     * TC: O(1)
     * @param other the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Entry<T> other) {
        // lagere priority = hogere prioriteit
        return Integer.compare(this.priority, other.priority);
    }
}
