package app.graph;

import app.Data.Person;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    public void addEdgeCreateUndirectedConnection() {
        Person a = new Person("Carol", 25);
        Person b = new Person("Rabanne", 30);
        Graph g = new Graph(new Person[]{a, b});

        g.addEdge(a, b, 5);

        Map<Person, Integer> na = g.getNeighbours(a);
        Map<Person, Integer> nb = g.getNeighbours(b);

        assertNotNull(na);
        assertNotNull(nb);
        assertTrue(na.containsKey(b), "a should have b as neighbor");
        assertTrue(nb.containsKey(a), "b should have a as neighbor");
        assertEquals(5, na.get(b).intValue());
        assertEquals(5, nb.get(a).intValue());
    }

    @Test
    public void addEdgeWithUnknownNodeDoesNada() {
        Person a = new Person("Carol", 25);
        Person unknown = new Person("Unknown", 40);
        Graph g = new Graph(new Person[]{a});

        // unknown not part of graph
        g.addEdge(a, unknown, 3);

        Map<Person, Integer> na = g.getNeighbours(a);
        assertNotNull(na);
        assertFalse(na.containsKey(unknown), "edge to unknown node should not be added");
    }

    @Test
    public void getNodesContainsAllGivenNodes() {
        Person a = new Person("Zoshia", 25);
        Person b = new Person("Rabanne", 30);
        Person c = new Person("Carol", 28);
        Graph g = new Graph(new Person[]{a, b, c});

        Set<Person> nodes = g.getNodes();
        assertEquals(3, nodes.size());
        assertTrue(nodes.contains(a));
        assertTrue(nodes.contains(b));
        assertTrue(nodes.contains(c));
    }
}
