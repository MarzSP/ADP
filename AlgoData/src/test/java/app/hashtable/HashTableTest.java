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

}
