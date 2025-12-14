package app.priorityQueue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    @Test
    void dequeueEmptyReturnsNull() {
        iPriorityQueue<String> pq = new PriorityQueue<>();
        assertNull(pq.dequeue());
    }

    @Test
    void peekEmptyReturnsNull() {
        iPriorityQueue<String> pq = new PriorityQueue<>();
        assertNull(pq.peek());
    }

    //peek() mag alleen kijken, met dequeue() checken we dat het el er nog in zit
    @Test
    void peekDoesntRemoveElement() {
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.enqueue("Turing", 1);

        assertEquals("Turing", pq.peek());
        assertEquals("Turing", pq.peek());
        assertEquals("Turing", pq.dequeue());
        assertNull(pq.peek());
    }

    //Bij gelijke prio moet FIFO gelden zodat 1ste added ook 1ste out is
    @Test
    void samePriorityIsFIFO() {
        iPriorityQueue<String> pq = new PriorityQueue<>();
        pq.enqueue("Turing", 5);
        pq.enqueue("Dijkstra", 5);
        pq.enqueue("UncleBob", 5);

        assertEquals("Turing", pq.dequeue());
        assertEquals("Dijkstra", pq.dequeue());
        assertEquals("UncleBob", pq.dequeue());
        assertNull(pq.dequeue());
    }

    // El met laagste nr komt eerst uit de queue
    @Test
    void differentPrioHighestPrioFirst() {
        iPriorityQueue<String> pq = new PriorityQueue<>();
        pq.enqueue("Galileo", 10);
        pq.enqueue("UncleBob", 1);
        pq.enqueue("Newton", 5);

        assertEquals("UncleBob", pq.dequeue());
        assertEquals("Newton", pq.dequeue());
        assertEquals("Galileo", pq.dequeue());
    }

    // Bij mix prioriteiten blijft de FIFO-volgorde binnen dezelfde prioriteit bestaan
    @Test
    void mixedPriosRespectsOrder() {
        iPriorityQueue<String> pq = new PriorityQueue<>();
        pq.enqueue("Curie", 2);
        pq.enqueue("Turing", 1);
        pq.enqueue("Galileo", 2);
        pq.enqueue("Dijkstra", 1);

        assertEquals("Turing", pq.dequeue());
        assertEquals("Dijkstra", pq.dequeue());
        assertEquals("Curie", pq.dequeue());
        assertEquals("Galileo", pq.dequeue());
    }

    //Vind waarde ongeacht in welke prio die zit
    @Test
    void containsWorksAcrossBuckets() {
        iPriorityQueue<String> pq = new PriorityQueue<>();
        pq.enqueue("Curie", 3);
        pq.enqueue("Turing", 1);

        assertTrue(pq.contains("Curie"));
        assertTrue(pq.contains("Turing"));
        assertFalse(pq.contains("Newton"));
    }

    //remove() geeft false terug als de waarde niet in de queue zit
    @Test
    void removeReturnsFalseWhenValueNotPresent() {
        iPriorityQueue<String> pq = new PriorityQueue<>();
        pq.enqueue("Turing", 1);

        assertFalse(pq.remove("Galileo"));
        assertEquals("Turing", pq.dequeue());
    }

    @Test
    void removeExistingValueKeepsOrder() {
        iPriorityQueue<String> pq = new PriorityQueue<>();
        pq.enqueue("Curie", 2);
        pq.enqueue("Newton", 2);
        pq.enqueue("Galileo", 2);

        assertTrue(pq.remove("Newton"));

        assertEquals("Curie", pq.dequeue());
        assertEquals("Galileo", pq.dequeue());
        assertNull(pq.dequeue());
    }

    @Test
    void removeLastValueUpdatesHead() {
        iPriorityQueue<String> pq = new PriorityQueue<>();
        pq.enqueue("UncleBob", 1);
        pq.enqueue("Turing", 2);

        assertTrue(pq.remove("UncleBob"));

        assertEquals("Turing", pq.peek());
        assertEquals("Turing", pq.dequeue());
        assertNull(pq.dequeue());
    }

    @Test
    void updatePrioMissingValueIsFalse() {
        iPriorityQueue<String> pq = new PriorityQueue<>();
        pq.enqueue("Galileo", 5);

        assertFalse(pq.updatePriority("Curie", 1));
        assertEquals("Galileo", pq.dequeue());
    }

    //verplaatst een el naar zijn nieuwe prio
    @Test
    void updatePrioExisingValueMovesElement() {
        iPriorityQueue<String> pq = new PriorityQueue<>();
        pq.enqueue("Newton", 5);
        pq.enqueue("Dijkstra", 1);

        assertTrue(pq.updatePriority("Newton", 0));

        assertEquals("Newton", pq.dequeue());
        assertEquals("Dijkstra", pq.dequeue());
    }

    //Check FIFO bij prio update
    @Test
    void updatePrioExistingPrioKeepsOrder() {
        iPriorityQueue<String> pq = new PriorityQueue<>();
        pq.enqueue("Turing", 1);
        pq.enqueue("Dijkstra", 1);
        pq.enqueue("Curie", 2);

        assertTrue(pq.updatePriority("Curie", 1));

        assertEquals("Turing", pq.dequeue());
        assertEquals("Dijkstra", pq.dequeue());
        assertEquals("Curie", pq.dequeue());
    }

    @Test
    void enqueueNullThrows() {
        PriorityQueue<String> pq = new PriorityQueue<>();
        assertThrows(IllegalArgumentException.class, () -> pq.enqueue(null, 1));
    }

    /**
     * Demo test t.b.v verbeterpunt: Map
     * Deze test laat zien dat enqueue trager wordt wanneer er veel verschillende prioriteiten zijn
     *
     * Vergelijken twee situaties:
     * - veel elementen met dezelfde prio
     * - veel elementen met allemaal een andere prio
     * In het tweede geval moet enqueue steeds meer buckets doorlopen om
     * de juiste plek te vinden, waardoor het duidelijk langer duurt.
     * Gemiddelde over 5 runs:
     * - Same priority enqueue: ~13 ms
     * - Many priorities enqueue: ~13868 ms
    */
    @Test
    void enqueueManyDifferentPrioritiesCanBeSlow() {
        int n = 100_000;

        PriorityQueue<String> samePriorityQueue = new PriorityQueue<>();
        PriorityQueue<String> manyPrioritiesQueue = new PriorityQueue<>();

        long startSame = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            samePriorityQueue.enqueue("Turing" + i, 1);
        }
        long durationSame = System.currentTimeMillis() - startSame;

        long startMany = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            manyPrioritiesQueue.enqueue("Dijkstra" + i, i);
        }
        long durationMany = System.currentTimeMillis() - startMany;

        System.out.println("Same priority enqueue: " + durationSame + " ms");
        System.out.println("Many priorities enqueue: " + durationMany + " ms");

        assertTrue(durationMany >= durationSame);
    }

}
