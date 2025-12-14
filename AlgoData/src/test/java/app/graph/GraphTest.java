package app.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void addEdge_addsVerticesAutomatically_andStoresNeighbor() {
        Graph<String> graph = new Graph<>();

        graph.addEdge("A", "B", 5);

        assertTrue(graph.getVertices().contains("A"));
        assertTrue(graph.getVertices().contains("B"));

        List<Graph.Edge<String>> neighbors = graph.getNeighbours("A");
        assertEquals(1, neighbors.size());
        assertEquals("B", neighbors.get(0).destination);
        assertEquals(5, neighbors.get(0).weight);
    }

    @Test
    void neighborsOfUnknownVertex_returnsEmptyList() {
        Graph<String> graph = new Graph<>();
        assertTrue(graph.getNeighbours("X").isEmpty());
    }

    @Test
    void graphIsDirected_byDefault() {
        Graph<String> graph = new Graph<>();
        graph.addEdge("A", "B", 1);

        assertEquals(1, graph.getNeighbours("A").size());
        assertEquals(0, graph.getNeighbours("B").size()); // geen automatische terug-edge
    }

    @Test
    void negativeWeights_areNotAllowed() {
        Graph<String> graph = new Graph<>();
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge("A", "B", -1));
    }
}
