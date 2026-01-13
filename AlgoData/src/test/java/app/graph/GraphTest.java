package app.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private static Vertex<String> v(String name) {
        return new Vertex<>(name);
    }

    @Test
    void addVertexNoNotDuplicates() {
        Graph<String> g = new Graph<>();
        Vertex<String> a1 = v("A");
        Vertex<String> a2 = v("A");

        g.addVertex(a1);
        g.addVertex(a2);

        double[] dist = g.dijkstraFromIndex(0);
        assertEquals(1, dist.length, "Graph mag maar een vertex A hebben");
        assertEquals(0.0, dist[0], 0.000001);
    }

    @Test
    void addEdgeCreatesMissingVerticesAndIsDirected() {
        Graph<String> g = new Graph<>();

        // A eerst toevoegen B daarna toevoegen(via addEdge)
        Vertex<String> a = v("A");
        Vertex<String> b = v("B");

        g.addVertex(a);
        g.addEdge(a, b, 5);

        // Indexen: A=0, B=1
        double[] fromA = g.dijkstraFromIndex(0);
        assertEquals(2, fromA.length);
        assertEquals(0.0, fromA[0], 0.000001);
        assertEquals(5.0, fromA[1], 0.000001, "A -> B is bereikbaar met weight 5");

        // Directed check: van B naar A is unreachable (infinite)
        double[] fromB = g.dijkstraFromIndex(1);
        assertTrue(Double.isInfinite(fromB[0]), "B -> A is onbereikbaar (directed graph)");
        assertEquals(0.0, fromB[1], 0.000001);
    }

    @Test
    void dijkstraShortestPathMultipleHops() {
        Graph<String> g = new Graph<>();

        Vertex<String> a = v("A");
        Vertex<String> b = v("B");
        Vertex<String> c = v("C");
        Vertex<String> d = v("D");

        //Vind indexes: A=0, B=1, C=2, D=3
        g.addVertex(a);
        g.addVertex(b);
        g.addVertex(c);
        g.addVertex(d);

        // Edges:
        // A -> B (1)
        // A -> C (10)
        // B -> C (2)
        // C -> D (3)
        g.addEdge(a, b, 1);
        g.addEdge(a, c, 10);
        g.addEdge(b, c, 2);
        g.addEdge(c, d, 3);

        double[] dist = g.dijkstraFromIndex(0);

        assertEquals(4, dist.length);
        assertEquals(0.0, dist[0], 0.000001);  // A
        assertEquals(1.0, dist[1], 0.000001);  // A->B
        assertEquals(3.0, dist[2], 0.000001);  // A->B->C = 1+2 (korter dn 10)
        assertEquals(6.0, dist[3], 0.000001);  // A->B->C->D = 1+2+3
    }

    @Test
    void dijkstraUnreachableVerticesRemainInfinity() {
        Graph<String> g = new Graph<>();

        Vertex<String> a = v("A");
        Vertex<String> b = v("B");
        Vertex<String> x = v("X");

        // Indexen: A=0, B=1, X=2
        g.addVertex(a);
        g.addVertex(b);
        g.addVertex(x);

        g.addEdge(a, b, 7);
        // X is disconnected

        double[] dist = g.dijkstraFromIndex(0);

        assertEquals(3, dist.length);
        assertEquals(0.0, dist[0], 0.000001);
        assertEquals(7.0, dist[1], 0.000001);
        assertTrue(Double.isInfinite(dist[2]), "Disconnecte vertex X moet infinity blijven");
    }

    @Test
    void removeRemovesVertexAndIncomingEdgesToIt() {
        Graph<String> g = new Graph<>();

        Vertex<String> a = v("A");
        Vertex<String> b = v("B");
        Vertex<String> c = v("C");

        // Indexen: A=0, B=1, C=2
        g.addVertex(a);
        g.addVertex(b);
        g.addVertex(c);

        g.addEdge(a, b, 1);
        g.addEdge(c, b, 2);

        // Sanity: B is bereikbaar A
        double[] before = g.dijkstraFromIndex(0);
        assertEquals(0.0, before[0], 0.000001);
        assertEquals(1.0, before[1], 0.000001);

        //Verwijder B: moet B verwijdern en alle edges die er naar wijzen (A->B, C->B)
        g.removeVertex(b);

        // We kunnen nu nog testen of A en C nog steeds bestaan en dat B weg is
        double[] after = g.dijkstraFromIndex(0);
        assertEquals(2, after.length, "Na verwijderen van B heeft graph 2 vertices");
        assertEquals(0.0, after[0], 0.000001);
        assertTrue(Double.isInfinite(after[1]) || after[1] == 0.0,
                "Geen edges over, dus andere vertex C is unreachable (van A uit gezien)");
    }
}
