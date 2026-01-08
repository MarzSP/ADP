package app.Data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    // Check getters en setters
    @Test
    void constructorAndGetters() {
        Person p = new Person("Turing", 41);

        assertEquals("Turing", p.getName());
        assertEquals(41, p.getAge());
    }

    // Zelfde data wordt ook gelijk beschouwd
    @Test
    void equalsAndHashCodeMatchForSameData() {
        Person p1 = new Person("Dijkstra", 55);
        Person p2 = new Person("Dijkstra", 55);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    // Test dat toString de naam en leeftijd bevat
    @Test
    void toStringContainsNameAndAge() {
        Person p = new Person("Knuth", 84);
        String result = p.toString();

        assertTrue(result.contains("Knuth"));
        assertTrue(result.contains("84"));
    }
}
