package app.graph;

import java.util.*;

/**
 * Class voor een gerichte gewogen graaf met generieke vertices met adjacency list
 * Inner class VertexEntry voor entries in de adjacency list (vertex + lijst van edges)
 * @param <T> generic type voor de data in de vertices
 */
public class Graph<T extends Comparable<T>> {
    // 0 -> [A, 0] -> [B ,5] -> [C,2]
    private final LinkedList<VertexEntry> adjacencyList = new LinkedList<>();

    /**
     * Class voor een entry in de adjacency list
     * Bevat een vertex en een lijst van edges
     */
    private class VertexEntry {
        final Vertex<T> vertex;
        // VertexEntry: [A] -> edges: [ (weight 5, targetVertex B)]
        final LinkedList<Edge<T>> edges = new LinkedList<>();

        VertexEntry(Vertex<T> vertex) {
            this.vertex = vertex;
        }
    }

    /**
     * Zoek een entry in de adjacency list op basis van de vertex
     * TC: Best O(1) als de vertex vooraan staat, worst O(n) als deze achteraan staat of niet bestaat
     * SC: O(1)
     * @param vertex de vertex om te zoeken
     * @return de gevonden VertexEntry of null als niet gevonden
     */
    private VertexEntry findEntry(Vertex<T> vertex) {
        for (VertexEntry entry : adjacencyList) {
            if (Objects.equals(entry.vertex, vertex)) return entry;
        }
        return null;
    }

    /**
     * Zoek de index van een vertex in de adjacency list
     * TC: Best O(1) als de vertex vooraan staat, worst O(n) als deze achteraan staat of niet bestaat
     * SC: O(1)
     * @param vertex de vertex om te zoeken
     * @return index van de vertex of -1 als niet gevonden
     */
    private int indexOfVertex(Vertex<T> vertex) {
        int index = 0;
        for (VertexEntry entry : adjacencyList) {
            if (Objects.equals(entry.vertex, vertex)) return index;
            index++;
        }
        return -1;
    }

    /**
     * Haal een VertexEntry op of maak deze aan als deze nog niet bestaat
     * TC: O(n)
     * SC: O(1) + ruimte voor nieuwe entry als nodig
     * @param vertex de vertex om te zoeken of toe te voegen
     * @return gevonden of nieuw aangemaakte VertexEntry
     */
    private VertexEntry getOrCreateEntry(Vertex<T> vertex) {
        VertexEntry node = findEntry(vertex);
        if (node == null) {
            node = new VertexEntry(vertex);
            adjacencyList.add(node);
        }
        return node;
    }

    /**
     * Voeg een vertex toe aan de graaf (in de adjacencyList) als deze nog niet bestaat
     * TC: O(n)
     * SC: O(1) + ruimte voor nieuwe vertex
     * @param vertex  toe te voegen vertex
     */
    public void addVertex(Vertex<T> vertex){
        getOrCreateEntry(vertex);
    }

    /**
     * Voeg een gerichte edge toe van source vertex naar target vertex met gegeven gewicht
     * TC: O(n)
     * SC: O(1) + ruimte voor nieuwe edge
     * @param source bron vertex
     * @param target doel vertex
     * @param weight gewicht van de edge
     */
    public void addEdge(Vertex<T> source, Vertex<T> target, int weight) {
        VertexEntry src = getOrCreateEntry(source);
        addVertex(target);

        src.edges.add(new Edge<>(weight, target));
    }

    /**
     * Verwijder een vertex en alle edges die ernaar wijzen
     * TC: O(n + m) n vertices en m edges = 1x vertex zoeken/verwijderen + alle edges nalopen om verwijzingen te verwijderen
     * SC: O(1)
     * @param vertex te verwijderen vertex
     */
    public void removeVertex(Vertex<T> vertex) {
        // verwijder de vertex zelf
        for (int i = 0; i < adjacencyList.size(); i++) {
            if (Objects.equals(adjacencyList.get(i).vertex, vertex)) {
                adjacencyList.remove(i);
                break;
            }
        }
        // Verwijder edges die naar de vertex wijzen
        for (VertexEntry vertexEntry : adjacencyList) {
            LinkedList<Edge<T>> edges = vertexEntry.edges;
            for (int j = edges.size() - 1; j >= 0; j--) {
                if (Objects.equals(edges.get(j).getTargetVertex(), vertex)) {
                    edges.remove(j);
                }
            }
        }
    }

    /**
     * Dijkstra met adjacency list
     * Returns afstanden met een double[] met Double.POSITIVE_INFINITY voor onbezochte vertices
     * Boolean[] houdt bij welke vertices al gedaan zijn
     * Double[] houdt de kortste afstanden bij
     * TC: O(n + m*n) met n vertices en m edges
     * SC: O(n) voor distance en visited arrays
     * @param startIndex beginpunt voor Dijkstra
     * @return distances from startIndex to all others
     */
    public double[] dijkstraFromIndex(int startIndex) {
        int n = adjacencyList.size();
        if (startIndex < 0 || startIndex >= n) {
            throw new IllegalArgumentException("startIndex out of range");
        }

        double[] distance = new double[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[startIndex] = 0.0;

        int start = findClosestNotVisited(distance, visited);

        dijkstraRecursive(start, distance, visited);

        return distance;
    }

    /**
     * Base case: currentIndex == -1. Dan zijn er geen bereikbare vertices meer
     * Recursive case: update afstanden van buren van currentIndex
     * TC: O(n + m*n) met n vertices en m edges
     * SC: O(n) voor distance en visited arrays + O(n) voor recursie stack
     * @param currentIndex  huidige vertex index
     * @param distance   afstanden
     * @param visited bezochte vertices
     */
    private void dijkstraRecursive(int currentIndex, double[] distance, boolean[] visited) {
        if (currentIndex == -1) return;

        VertexEntry currentNode = adjacencyList.get(currentIndex);
        for (Edge<T> edge : currentNode.edges) {
            Vertex<T> targetVertex = edge.getTargetVertex();

            int neighborIndex = indexOfVertex(targetVertex);
            if (neighborIndex == -1) continue;

            double candidate = distance[currentIndex] + edge.getWeight();
            if (candidate < distance[neighborIndex]) {
                distance[neighborIndex] = candidate;
            }
        }

        visited[currentIndex] = true;
        int nextIndex = findClosestNotVisited(distance, visited);
        dijkstraRecursive(nextIndex, distance, visited);
    }

    /**
     * Zoek de index van de dichtstbijzijnde vertex die nog niet gedaan is (!visited[])
     * TC: O(n) met n vertices om de distance array te doorlopen
     * SC: O(1)
     * @param distance afstanden
     * @param visited bezochte vertices
     * @return bestIndex index van de dichtstbijzijnde onbezochte vertex
     */
    private int findClosestNotVisited(double[] distance, boolean[] visited) {
        int bestIndex = -1;
        double bestDistance = Double.POSITIVE_INFINITY;

        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && distance[i] < bestDistance) {
                bestDistance = distance[i];
                bestIndex = i;
            }
        }
        return bestIndex;
    }
}
