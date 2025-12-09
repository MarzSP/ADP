package app.priorityQueue;

import app.Data.Dataset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    private PriorityQueue<String> pq;

    @BeforeEach
    void setUp() {
        pq = new PriorityQueue<>();
    }

    private static String[] getNames() {
        return Dataset.getNames();
    }

    @Test
    void emptyQueuePeekAndDequeue() {
        assertNull(pq.peek(), "Empty queue should return null with peek");
        assertNull(pq.dequeue(), "Empty queue should return null with dequeue");
    }

    @Test
    void dequeueCheckPriorityOrder() {
        // lagere waarde = hogere prioriteit
        pq.enqueue("UncleBob", 5);
        pq.enqueue("Lovelace", 1);
        pq.enqueue("McCarthy", 3);

        // hoogste prioriteit is Lovelace (prio 1)
        assertEquals("Lovelace", pq.peek(), "peek returns element with highest priority");
        assertEquals("Lovelace", pq.dequeue());
        assertEquals("McCarthy", pq.dequeue());
        assertEquals("UncleBob", pq.dequeue());

        assertNull(pq.dequeue());
        assertNull(pq.peek());
    }

    @Test
    void peekDoesntRemoveElement() {
        pq.enqueue("Turing", 2);
        pq.enqueue("Lovelace", 1);

        // Lovelace heeft hoogste prioriteit
        assertEquals("Lovelace", pq.peek(), "peek shows highest-priority element");
        // peek mag niet verwijderen
        assertEquals("Lovelace", pq.peek(), "peek shouldnt remove the element");

        // dequeue haalt nu pas weg
        assertEquals("Lovelace", pq.dequeue());
        assertEquals("Turing", pq.dequeue());
        assertNull(pq.dequeue());
    }

    @Test
    void duplicateWithSamePrio() {
        pq.enqueue("Turing", 2);
        pq.enqueue("Turing", 2);
        pq.enqueue("Lovelace", 1);

        // Eerst Lovelace (prio 1)
        assertEquals("Lovelace", pq.dequeue());

        // Daarna de twee Turings (zelfde prio)
        String firstTuring = pq.dequeue();
        String secondTuring = pq.dequeue();

        assertEquals("Turing", firstTuring);
        assertEquals("Turing", secondTuring);
        assertNull(pq.dequeue());
    }

    @Test
    void manyElementsInPriorityOrder() {
        String[] names = getNames();

        // gebruik de eerste 15 namen, priority = index
        for (int i = 0; i < 15; i++) {
            pq.enqueue(names[i], i); // 0 = hoogste prioriteit
        }

        // in volgorde van prioriteit dequeuen
        for (int i = 0; i < 15; i++) {
            String dequeued = pq.dequeue();
            assertEquals(names[i], dequeued,
                    "Elements are dequeued in order of increasing priority");
        }

        assertNull(pq.dequeue());
        assertNull(pq.peek());
    }

    @Test
    void operationsRespectPriority() {
        pq.enqueue("Torvalds", 10);
        pq.enqueue("Ritchie", 4);

        assertEquals("Ritchie", pq.dequeue());

        pq.enqueue("van Rossum", 6);
        pq.enqueue("Dijsktra", 2);

        assertEquals("Dijsktra", pq.peek());

        assertEquals("Dijsktra", pq.dequeue());
        assertEquals("van Rossum", pq.dequeue());
        assertEquals("Torvalds", pq.dequeue());
        assertNull(pq.dequeue());
        assertNull(pq.peek());
    }


    @Test
    void containsChecksPresenceOfValue() {
        pq.enqueue("Lovelace", 1);
        pq.enqueue("Turing", 2);

        assertTrue(pq.contains("Lovelace"));
        assertTrue(pq.contains("Turing"));
        assertFalse(pq.contains("Einstein"));

        // na dequeue moet contains nog kloppen
        pq.dequeue(); // Lovelace eruit
        assertFalse(pq.contains("Lovelace"));
        assertTrue(pq.contains("Turing"));
    }

    @Test
    void removeDeletesFirstMatchingValue() {
        pq.enqueue("Turing", 5);
        pq.enqueue("Lovelace", 1);
        pq.enqueue("Turing", 3);

        // remove verwijdert eerste "Turing"
        assertTrue(pq.remove("Turing"));
        // er staat nog steeds één Turing en één Lovelace in
        assertTrue(pq.contains("Turing"));
        assertTrue(pq.contains("Lovelace"));

        // verwijderen van iets dat er niet meer in zit geeft false
        assertTrue(pq.remove("Turing"));  // tweede Turing eruit
        assertFalse(pq.remove("Turing")); // nu bestaat hij niet meer

        assertTrue(pq.contains("Lovelace"));
        assertFalse(pq.contains("Turing"));
    }

    @Test
    void updatePriorityChangesOrder() {
        pq.enqueue("Lovelace", 5);
        pq.enqueue("Turing", 2);
        pq.enqueue("Dijsktra", 1);

        // Eerst Dijsktra (1) > Turing (2) > Lovelace (5)
        assertEquals("Dijsktra", pq.peek());

        // maak Lovelace nu hoogste prioriteit
        assertTrue(pq.updatePriority("Lovelace", 0));

        // nu moet Lovelace eerst komen
        assertEquals("Lovelace", pq.dequeue());
        // daarna nog steeds "Dijsktra" en "Turing" in volgorde van priority
        assertEquals("Dijsktra", pq.dequeue());
        assertEquals("Turing", pq.dequeue());
        assertNull(pq.dequeue());
    }

    @Test
    void updatePriorityReturnsFalseIfValueNotFound() {
        pq.enqueue("Lovelace", 1);

        assertFalse(pq.updatePriority("Turing", 0));
        // Lovelace is nog steeds enige element
        assertEquals("Lovelace", pq.peek());
    }
}
