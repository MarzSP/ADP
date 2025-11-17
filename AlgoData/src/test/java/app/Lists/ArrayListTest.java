package app.Lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {

    private ArrayList<String> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
    }

    @Test
    void testAddAndGetSize() {
        assertTrue(list.isEmpty());
        list.add("a");
        list.add("b");
        assertEquals(2, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
    }

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

    @Test
    void testClearAndIsEmpty() {
        list.add("one");
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

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

    @Test
    void testIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        list.add("a");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "b"));
    }

    @Test
    void testLargeAddResizing() {
        for (int i = 0; i < 100; i++) {
            list.add("v" + i);
        }
        assertEquals(100, list.size());
        assertEquals("v0", list.get(0));
        assertEquals("v99", list.get(99));
    }
}
