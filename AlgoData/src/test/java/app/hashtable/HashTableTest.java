package app.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests voor de HashTable implementatie
 */
public class HashTableTest {

    @Test
    void testPutAndGet() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("a", 1);
        table.put("b", 2);

        assertEquals(1, table.get("a"));
        assertEquals(2, table.get("b"));
    }

    /**
     * Deze test is nodig om te controleren of het opvragen van een niet-bestaande key wordt afgehandeld
     */
    @Test
    void testGetUnknownKeyReturnsNull() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("a", 1);

        assertNull(table.get("b")); // b is nooit toegevoegd, alleen a
    }

    /**
     * Deze test is nodig omdat het overschrijven van een bestaande key de size van de hashtable niet mag vergrooten
     */
    @Test
    void testOverwriteExistingKeyDoesNotIncreaseSize() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("key", 10);
        table.put("key", 20); // zelfde key, andere value

        assertEquals(1, table.size());       // nog steeds 1 element
        assertEquals(20, table.get("key"));  // value is overschreven
    }

    @Test
    void testSizeIncreasesOnNewKeys() {
        HashTable<String, Integer> table = new HashTable<>();

        assertEquals(0, table.size());

        table.put("a", 1);
        table.put("b", 2);

        assertEquals(2, table.size());
    }

    @Test
    void testRemoveExistingKey() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("a", 1);
        table.put("b", 2);

        Integer removed = table.remove("a");

        assertEquals(1, removed);         // value terug die is verwijderd
        assertNull(table.get("a"));       // a bestaat niet meer
        assertEquals(1, table.size());    // size is kleiner geworden
    }

    /**
     * Deze test is nodig om te controleren of het verwijderen van een niet-bestaande key wel wordt afgehandeld
     */
    @Test
    void testRemoveNonExistingKeyReturnsNull() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("a", 1);

        Integer removed = table.remove("b"); // b bestaat niet

        assertNull(removed);
        assertEquals(1, table.size()); // size verandert niet
    }

    @Test
    void testContainsKey() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("a", 1);

        assertTrue(table.containsKey("a"));
        assertFalse(table.containsKey("b"));
    }

    /**
     * Deze test is nodig om collisions met linear probing te controleren
     */
    @Test
    void testCollisionAndLinearProbing() {
        HashTable<Integer, String> table = new HashTable<>();

        // 1 en 17 hebben dezelfde hash modulo 16 (DEFAULT_CAPACITY)
        table.put(1, "een");
        table.put(17, "zeventien");

        //Met linear probing kunnen we beide keys terugvinden
        assertEquals("een", table.get(1));
        assertEquals("zeventien", table.get(17));
        assertEquals(2, table.size());
    }
}
