package app.graph;

import java.util.*;

/**
 * Dijkstra Algoritme: kortste paden in een weighted graph
 * Linear zoeken naar de dichtstbijzijnde onbezochte vertex
 * Time complexity: O(N^2 + E)
 * Space complexity: O(N)
 */
public class Dijkstra {

    /**
     * Berekent de kortste afstanden vanaf de start-vertex naar alle vertices
     */
    public static <Vertex> Map<Vertex, Integer> calculateShortestPaths(

            Graph<Vertex> graph,
            Vertex start
    ) {
        if (graph.getVertices().isEmpty()) {
            throw new IllegalArgumentException("Graph empty");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start vertex not in graph");
        }

        Map<Vertex, Integer> distances = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();

        // init alle afstanden als oneindig
        for (Vertex v : graph.getVertices()) {
            distances.put(v, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        // herhaal totdat alle vertices bezocht zijn
        while (visited.size() < graph.getVertices().size()) {
            Vertex current = findClosestUnvisitedVertex(
                    graph.getVertices(), distances, visited);

            // geen bereikbare vertices meer
            if (current == null) break;

            visited.add(current);

            for (Graph.Edge<Vertex> edge : graph.getNeighbours(current)) {
                int currentDistance = distances.get(current);
                if (currentDistance == Integer.MAX_VALUE) continue;

                int newDistance = currentDistance + edge.weight;
                if (newDistance < distances.get(edge.destination)) {
                    distances.put(edge.destination, newDistance);
                }
            }
        }

        return distances;
    }

    /**
     * Zoekt lineair de onbezochte vertex met de kleinste afstand
     */
    private static <Vertex> Vertex findClosestUnvisitedVertex(
            Set<Vertex> vertices,
            Map<Vertex, Integer> distances,
            Set<Vertex> visited
    ) {
        Vertex closest = null;
        int smallestDistance = Integer.MAX_VALUE;

        for (Vertex v : vertices) {
            if (visited.contains(v)) continue;

            int dist = distances.getOrDefault(v, Integer.MAX_VALUE);
            if (dist < smallestDistance) {
                smallestDistance = dist;
                closest = v;
            }
        }
        return closest;
    }
}
