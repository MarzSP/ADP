package app.priorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {

    private PriorityQueue<Integer> pq;

    @BeforeEach
    void setUp() {
        pq = new PriorityQueue<>();
    }

    @Test
    void testEmptyQueue() {
        assertTrue(pq.isEmpty());
        assertEquals(0, pq.size());
        assertNull(pq.peek());
        assertNull(pq.dequeue());
    }

    @Test
    void testEnqueueDequeueOrderAndPeek() {
        pq.enqueue(5);
        pq.enqueue(1);
        pq.enqueue(3);

        assertEquals(Integer.valueOf(1), pq.peek(), "peek should return smallest element without removing");
        assertEquals(3, pq.size());

        assertEquals(Integer.valueOf(1), pq.dequeue());
        assertEquals(Integer.valueOf(3), pq.dequeue());
        assertEquals(Integer.valueOf(5), pq.dequeue());
        assertTrue(pq.isEmpty());
    }

    @Test
    void testDuplicatesPreserved() {
        pq.enqueue(2);
        pq.enqueue(2);
        pq.enqueue(1);

        assertEquals(Integer.valueOf(1), pq.dequeue());
        assertEquals(Integer.valueOf(2), pq.dequeue());
        assertEquals(Integer.valueOf(2), pq.dequeue());
        assertTrue(pq.isEmpty());
    }

    @Test
    void testCapacityGrowthAndAllOrder() {
        // Insert more than initial capacity (10) to trigger resizing
        for (int i = 14; i >= 0; i--) {
            pq.enqueue(i);
        }
        assertEquals(15, pq.size());

        for (int expected = 0; expected < 15; expected++) {
            assertEquals(Integer.valueOf(expected), pq.dequeue());
        }
        assertTrue(pq.isEmpty());
    }

    @Test
    void testInterleavedOperations() {
        pq.enqueue(10);
        pq.enqueue(4);
        assertEquals(Integer.valueOf(4), pq.dequeue());
        pq.enqueue(6);
        pq.enqueue(2);
        assertEquals(Integer.valueOf(2), pq.peek());
        assertEquals(3, pq.size());
        assertEquals(Integer.valueOf(2), pq.dequeue());
        assertEquals(Integer.valueOf(6), pq.dequeue());
        assertEquals(Integer.valueOf(10), pq.dequeue());
        assertNull(pq.dequeue());
    }
}