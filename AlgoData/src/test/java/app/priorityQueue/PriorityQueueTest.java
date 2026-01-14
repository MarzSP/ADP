package app.priorityQueue;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    @Test
    void insertElementFindMinReturnsElement() {
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.insert("A", 10);

        assertEquals("A", pq.findMin());
    }

    @Test
    void insertMultiElementsFindMinReturnsLowestPrio() {
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.insert("low", 50);
        pq.insert("high", 1);
        pq.insert("mid", 10);

        assertEquals("high", pq.findMin());
    }

    @Test
    void removeMinReturnsElementsInPriorityOrder() {
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.insert("p50", 50);
        pq.insert("p1", 1);
        pq.insert("p10", 10);
        pq.insert("p2", 2);

        assertEquals("p1", pq.removeMin());
        assertEquals("p2", pq.removeMin());
        assertEquals("p10", pq.removeMin());
        assertEquals("p50", pq.removeMin());
    }

    @Test
    void insertNewHeadHasLowerPriorityNumberIsNewHead() {
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.insert("first", 10);
        assertEquals("first", pq.findMin());

        pq.insert("newHead", 1);
        assertEquals("newHead", pq.findMin());
    }

    @Test
    void samePriorityKeepsInsertionOrder() {
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.insert("A", 5);
        pq.insert("B", 5);
        pq.insert("C", 5);

        // Door <= in de while-loop blijft insertion order behouden (stable)
        assertEquals("A", pq.removeMin());
        assertEquals("B", pq.removeMin());
        assertEquals("C", pq.removeMin());
    }

    @Test
    void findMinAlwaysMatchesNextRemoveMin() {
        PriorityQueue<String> pq = new PriorityQueue<>();

        pq.insert("X", 10);
        pq.insert("Y", 1);
        assertEquals(pq.findMin(), pq.removeMin()); // Y

        pq.insert("Z", 5);
        assertEquals("Z", pq.findMin());
        assertEquals("Z", pq.removeMin());

        assertEquals("X", pq.findMin());
        assertEquals("X", pq.removeMin());

        assertThrows(NoSuchElementException.class, pq::removeMin);
    }

    @Test
    void genericTypeTest() {
        PriorityQueue<Double> pq = new PriorityQueue<>();
        pq.insert(3.14, 2);
        pq.insert(2.71, 1);

        assertEquals(2.71, pq.removeMin());
        assertEquals(3.14, pq.removeMin());
    }

    @Test
    void negativePrioritiesAllowedAreFirst() {
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.insert("zero", 0);
        pq.insert("minusOne", -1);
        pq.insert("minusTen", -10);

        assertEquals("minusTen", pq.removeMin());
        assertEquals("minusOne", pq.removeMin());
        assertEquals("zero", pq.removeMin());
    }
}
