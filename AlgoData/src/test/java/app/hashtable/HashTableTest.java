package app.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {

    @Test
    public void testPutGetRemove() {
        HashTable<String, Integer> t = new HashTable<>();
        assertNull(t.get("a"));
        assertNull(t.put("a", 1));
        assertEquals(1, t.get("a"));
        assertEquals(1, t.put("a", 2));
        assertEquals(2, t.get("a"));
        assertEquals(2, t.remove("a"));
        assertNull(t.get("a"));
        assertEquals(0, t.size());
    }

    @Test
    public void testResizeAndManyInserts() {
        HashTable<String, Integer> t = new HashTable<>();
        for (int i = 0; i < 200; i++) {
            t.put("k" + i, i);
        }
        assertEquals(200, t.size());
        t.resize(13);
        assertEquals(42, t.get("k42"));
        assertEquals(199, t.get("k199"));
    }

    @Test
    public void testRemoveNonExisting() {
        HashTable<String, Integer> t = new HashTable<>();
        assertNull(t.remove("nope"));
    }

    @Test
    public void testNullKeyThrows() {
        HashTable<String, Integer> t = new HashTable<>();
        assertThrows(NullPointerException.class, () -> t.put(null, 1));
        assertThrows(NullPointerException.class, () -> t.get(null));
        assertThrows(NullPointerException.class, () -> t.remove(null));
    }

    // Helper key class forceert collisions door altijd dezelfde hashcode te returnen
    private static final class Collider {
        private final String id;
        Collider(String id) { this.id = id; }
        @Override public int hashCode() { return 42; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Collider)) return false;
            return id.equals(((Collider) o).id);
        }
        @Override public String toString() { return id; }
    }

    @Test
    public void testCollisionsHandled() {
        HashTable<Collider, Integer> t = new HashTable<>();
        Collider k1 = new Collider("a");
        Collider k2 = new Collider("b");
        Collider k3 = new Collider("c");

        assertNull(t.get(k1));
        t.put(k1, 1);
        t.put(k2, 2);
        t.put(k3, 3);

        assertEquals(1, t.get(k1));
        assertEquals(2, t.get(k2));
        assertEquals(3, t.get(k3));

        assertEquals(3, t.size());

        // verwijder middelste in chain
        assertEquals(2, t.remove(k2));
        assertNull(t.get(new Collider("b")));
        assertEquals(1, t.get(k1));
        assertEquals(3, t.get(k3));
        assertEquals(2, t.size());
    }

    @Test
    public void testDuplicateKeysDoNotIncreaseSize() {
        HashTable<String, Integer> t = new HashTable<>();
        assertNull(t.put("x", 1));
        assertEquals(1, t.size());
        assertEquals(1, t.put("x", 2));
        assertEquals(2, t.get("x"));
        assertEquals(1, t.size());
    }

    @Test
    public void testNullValueIsAllowed() {
        HashTable<String, Integer> t = new HashTable<>();
        assertNull(t.put("n", null));
        assertNull(t.get("n"));
        assertEquals(1, t.size());
        assertNull(t.remove("n"));
        assertEquals(0, t.size());
    }

    @Test
    public void testEmptyTableOperations() {
        HashTable<String, Integer> t = new HashTable<>();
        assertNull(t.get("nothing"));
        assertNull(t.remove("nothing"));
        assertEquals(0, t.size());
    }

    @Test
    public void testRemoveHeadMiddleTailInChain() {
        HashTable<Collider, Integer> t = new HashTable<>();
        Collider a = new Collider("a");
        Collider b = new Collider("b");
        Collider c = new Collider("c");
        Collider d = new Collider("d");

        t.put(a, 1);
        t.put(b, 2);
        t.put(c, 3);
        t.put(d, 4);
        assertEquals(4, t.size());

        // verwijder head
        assertEquals(1, t.remove(a));
        assertNull(t.get(new Collider("a")));
        assertEquals(3, t.size());

        // verwijder tail
        assertEquals(4, t.remove(d));
        assertNull(t.get(new Collider("d")));
        assertEquals(2, t.size());

        // verwijder middle
        assertEquals(2, t.remove(b));
        assertNull(t.get(new Collider("b")));
        assertEquals(1, t.size());

        assertEquals(3, t.get(c));
    }

    @Test
    public void testLargeInsertRemovePreservesCorrectness() {
        HashTable<String, Integer> t = new HashTable<>();
        final int N = 5000;
        for (int i = 0; i < N; i++) t.put("k" + i, i);
        assertEquals(N, t.size());
        // check sommige entries
        assertEquals(0, t.get("k0"));
        assertEquals(123, t.get("k123"));
        assertEquals(N - 1, t.get("k" + (N - 1)));

        // verwijder half
        for (int i = 0; i < N; i += 2) {
            Integer old = t.remove("k" + i);
            assertNotNull(old);
        }
        assertEquals(N / 2, t.size());
        // verifieer overgebleven
        for (int i = 1; i < N; i += 2) {
            assertEquals(i, t.get("k" + i));
        }

        // verwijder alle
        for (int i = 1; i < N; i += 2) t.remove("k" + i);
        assertEquals(0, t.size());
    }

    @Test
    public void testResizePreservesEntries() {
        HashTable<String, Integer> t = new HashTable<>();
        final int N = 2000;
        for (int i = 0; i < N; i++) t.put("p" + i, i);
        assertEquals(N, t.size());
        // forceer nog een resize
        for (int i = N; i < N + 2000; i++) t.put("p" + i, i);
        assertEquals(N + 2000, t.size());
        // verifieer enkele entries
        assertEquals(0, t.get("p0"));
        assertEquals(N + 1999, t.get("p" + (N + 1999)));
    }

    @Test
    public void testContainsKeyBasic() {
        HashTable<String, Integer> t = new HashTable<>();
        assertFalse(t.containsKey("x"));
        t.put("x", 1);
        assertTrue(t.containsKey("x"));
    }

    @Test
    public void testInvalidCapacityThrows() {
        assertThrows(IllegalArgumentException.class, () -> new HashTable<String, Integer>(0));
        assertThrows(IllegalArgumentException.class, () -> new HashTable<String, Integer>(-1));
    }

    @Test
    public void testClearEmptiesTable() {
        HashTable<String, Integer> t = new HashTable<>();
        t.put("a", 1);
        t.put("b", 2);
        assertEquals(2, t.size());

        t.clear();

        assertEquals(0, t.size());
        assertNull(t.get("a"));
        assertNull(t.get("b"));

        // daarna moet hij nog gewoon werken
        assertNull(t.put("c", 3));
        assertEquals(1, t.size());
        assertEquals(3, t.get("c"));
    }



}
