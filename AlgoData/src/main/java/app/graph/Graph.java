package app.graph;

import java.util.*;

/**
 * Generieke implementatie van een gewogen graaf op basis van een adjacency list.
 * De graaf wordt opgeslagen als een Map waarbij:
 * Key: Vertex
 * Value: een lijst is van uitgaande edges met weights
 * De graph is generic en kan gebruikt worden met elk type vertex,
 * zoals bijvoorbeeld Person. (Zie Class DijkstraDemo)
 **/
public class Graph<Vertex> {

    /**
     * Edge is een weighted verbinding naar een andere vertex
     */
    public static class Edge<Vertex> {
        public final Vertex destination;
        public final int weight;

        public Edge(Vertex destination, int weight) {
            if (weight < 0) {
                throw new IllegalArgumentException("Dijkstra's algoritme doet niet aan negatieve gewichten");
            }
            this.destination = destination;
            this.weight = weight;
        }
    }

    /**
     * Adjacency list: elke vertex een lijst heeft van zijn directe buren met weights
     */
    private final Map<Vertex, List<Edge<Vertex>>> adjacencyList = new HashMap<>();

    public void addVertex(Vertex vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex from, Vertex to, int weight) {
        addVertex(from);
        addVertex(to);
        adjacencyList.get(from).add(new Edge<>(to, weight));
    }

    public List<Edge<Vertex>> getNeighbours(Vertex vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyList());
    }

    public Set<Vertex> getVertices() {
        return adjacencyList.keySet();
    }
}
