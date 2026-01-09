package app.lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {

    private ArrayList<String> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
    }

    /**
     * Elementen toevoegen en grootte controleren
     */
    @Test
    void testAddAndGetSize() {
        assertTrue(list.isEmpty());
        list.add("carol");
        list.add("zoshia");
        assertEquals(2, list.size());
        assertEquals("carol", list.get(0));
        assertEquals("zoshia", list.get(1));
    }

    /**
     * Elementen toevoegen op specifieke index en ophalen
     */
    @Test
    void testAddAtIndexAndGet() {
        list.add("first");
        list.add("third");
        list.add(1, "second");
        assertEquals(3, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(2));
    }

    /**
     * Elementen setten en verwijderen
     */
    @Test
    void testSetAndRemove() {
        list.add("x");
        list.add("y");
        String old = list.set(1, "z");
        assertEquals("y", old);
        assertEquals("z", list.get(1));

        String removed = list.remove(0);
        assertEquals("x", removed);
        assertEquals(1, list.size());
        assertEquals("z", list.get(0));
    }

    /**
     * Lijst legen en controleren of leeg
     */
    @Test
    void testClearAndIsEmpty() {
        list.add("one");
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    /**
     * Bevat en indexOf methodes testen en null handling
     */
    @Test
    void testContainsIndexOfAndNullHandling() {
        list.add(null);
        list.add("z");
        assertTrue(list.contains(null));
        assertTrue(list.contains("z"));
        assertFalse(list.contains("nope"));
        assertEquals(0, list.indexOf(null));
        assertEquals(1, list.indexOf("z"));
        assertEquals(-1, list.indexOf("nope"));
    }

    /**
     * IndexOutOfBoundsException testen
     */
    @Test
    void testIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        list.add("a");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "b"));
    }

    /**
     * Grote dingen toevoegen om resizing te testen
     */
    @Test
    void testLargeAddResizing() {
        for (int i = 0; i < 1000; i++) {
            list.add("v" + i);
        }
        assertEquals(100, list.size());
        assertEquals("v0", list.get(0));
        assertEquals("v99", list.get(99));
    }
}
