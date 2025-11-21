package app.priorityQueue;

public interface iPriorityQueue<T extends Comparable<T>> {

    void enqueue(T element);

    T dequeue();

    boolean isEmpty();

    int size();

    T peek();
}
