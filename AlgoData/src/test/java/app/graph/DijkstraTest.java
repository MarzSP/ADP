package app.graph;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    // Normaal scenario: test op kortste paden berekenen
    @Test
    void calculateShortestPathsSmallGraph() {
        Graph<String> graph = new Graph<>();

        // A -> B (4), A -> C (2), C -> B (1), B -> D (2), C -> D (7)
        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 2);
        graph.addEdge("C", "B", 1);
        graph.addEdge("B", "D", 2);
        graph.addEdge("C", "D", 7);

        Map<String, Integer> dist = Dijkstra.calculateShortestPaths(graph, "A");

        assertEquals(0, dist.get("A"));
        assertEquals(2, dist.get("C"));
        assertEquals(3, dist.get("B")); // A->C->B = 2+1
        assertEquals(5, dist.get("D")); // A->C->B->D = 2+1+2
    }

    // Dijkstra kan niet werken op een lege graaf
    @Test
    void emptyGraphThrowsException() {
        Graph<String> graph = new Graph<>();

        assertThrows(IllegalArgumentException.class, () ->
                Dijkstra.calculateShortestPaths(graph, "A")
        );
    }

    // Test afstand startnode naar zichzelf is altijd 0
    @Test
    void singleVertexGraphDistanceToSelfIsZero() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");

        Map<String, Integer> distances =
                Dijkstra.calculateShortestPaths(graph, "A");

        assertEquals(1, distances.size());
        assertEquals(0, distances.get("A"));
    }

    // Test bij meerdere paden en kiest de kortste
    @Test
    void multiplePathsShortestDistance() {
        Graph<String> graph = new Graph<>();

        graph.addEdge("A", "B", 10);
        graph.addEdge("A", "C", 1);
        graph.addEdge("C", "B", 1);

        Map<String, Integer> distances =
                Dijkstra.calculateShortestPaths(graph, "A");

        assertEquals(2, distances.get("B"));
    }

    // Test meerdere routes kortste route. Van A naar D is sowieso 4
    @Test
    void tieMultipleRoutesSameDistanceReturnDistance() {
        Graph<String> graph = new Graph<>();

        graph.addEdge("A", "B", 2);
        graph.addEdge("B", "D", 2);

        graph.addEdge("A", "C", 2);
        graph.addEdge("C", "D", 2);

        Map<String, Integer> distances =
                Dijkstra.calculateShortestPaths(graph, "A");

        assertEquals(4, distances.get("D"));
    }
}