package app.graph;

import app.lists.LinkedList;
import java.util.*;

/**
 * Class voor een gerichte gewogen graaf met generieke vertices
 * De graaf wordt gerepresenteerd met een adjacency list
 * @param <T> generic type voor de data in de vertices
 */
public class Graph<T extends Comparable<T>> {

    private final LinkedList<AdjacencyNode> adjacencyList = new LinkedList<>();

    /**
     * Class voor een entry/node in de adjacency list
     * Bevat een vertex en een lijst van edges
     */
    private class AdjacencyNode {
        final Vertex<T> vertex;
        final LinkedList<Edge<T>> edges = new LinkedList<>();

        AdjacencyNode(Vertex<T> vertex) {
            this.vertex = vertex;
        }
    }

    /**
     * Zoek een node in de adjacency list op basis van de vertex
     * @param vertex de vertex om te zoeken
     * @return de gevonden AdjacencyNode of null als niet gevonden
     */
    private AdjacencyNode findNode(Vertex<T> vertex) {
        for (int i = 0; i < adjacencyList.size(); i++) {
            AdjacencyNode n = adjacencyList.get(i);
            if (Objects.equals(n.vertex, vertex)) return n;
        }
        return null;
    }
    private int findNodeIndex(Vertex<T> vertex) {
        for (int i = 0; i < adjacencyList.size(); i++) {
            AdjacencyNode n = adjacencyList.get(i);
            if (Objects.equals(n.vertex, vertex)) return i;
        }
        return -1;
    }

    private AdjacencyNode upsertVertex(Vertex<T> vertex) {
        AdjacencyNode node = findNode(vertex);
        if (node == null) {
            node = new AdjacencyNode(vertex);
            adjacencyList.add(node);
        }
        return node;
    }

    /**
     * Voeg een vertex toe aan de graaf (in de adjacencyList) als deze nog niet bestaat
     * @param vertex  toe te voegen vertex
     */
    public void addVertex(Vertex<T> vertex){
        upsertVertex(vertex);
    }

    /**
     * Voeg een gerichte edge toe van source vertex naar target vertex met gegeven gewicht
     * @param source bron vertex
     * @param target doel vertex
     * @param weight gewicht van de edge
     */
    public void addEdge(Vertex<T> source, Vertex<T> target, int weight) {
        AdjacencyNode src = upsertVertex(source);
        addVertex(target);

        src.edges.add(new Edge<>(weight, target));
    }

    /**
     * Dijkstra met adjacency list
     * Returns afstanden met een double[] met Double.POSITIVE_INFINITY voor onbezochte vertices
     * Boolean[] houdt bij welke vertices al gedaan zijn
     * Double[] houdt de kortste afstanden bij
     * @param startIndex beginpunt voor Dijkstra
     * @return distances from startIndex to all others
     */
    public double[] dijkstra(int startIndex) {
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

    private void dijkstraCheckAdjustedNode(int currentIndex, double[] distance, boolean[] visited) {
        if (currentIndex == -1) return;
        visited[currentIndex] = true;

        AdjacencyNode node = adjacencyList.get(currentIndex);
        for(int i = 0; i < node.edges.size(); i++) {
            Edge edge = node.edges.get(i);

            Vertex targetVertex = edge.getTargetVertex();
            int neighbourIndex = findNodeIndex(targetVertex);

            // take care of the neighbours
            dijkstraCheckAdjustedNode(neighbourIndex, distance, visited);

            // find index of target vertex in adjacencyList
            int neighborIndex = -1;
            for (int j = 0; j < adjacencyList.size(); j++) {
                if (Objects.equals(adjacencyList.get(j).vertex, targetVertex)) {
                    neighborIndex = j;
                    break;
                }
            }
            if (neighborIndex == -1) continue;

            double candidate = distance[currentIndex] + edge.getWeight();
            if (candidate < distance[neighborIndex]) {
                distance[neighborIndex] = candidate;
            }

        }

    }


    /**
     * Base case: currentIndex == -1. Dan zijn er geen bereikbare vertices meer
     * Recursive case: update afstanden van buren van currentIndex
     * @param currentIndex  huidige vertex index
     * @param distance   afstanden
     * @param visited bezochte vertices
     */
    private void dijkstraRecursive(int currentIndex, double[] distance, boolean[] visited) {
        if (currentIndex == -1) return;

        AdjacencyNode currentNode = adjacencyList.get(currentIndex);
        for (int e = 0; e < currentNode.edges.size(); e++) {
            Edge<T> edge = currentNode.edges.get(e);
            Vertex<T> targetVertex = edge.getTargetVertex();

            // find index of target vertex in adjacencyList
            int neighborIndex = -1;
            for (int i = 0; i < adjacencyList.size(); i++) {
                if (Objects.equals(adjacencyList.get(i).vertex, targetVertex)) {
                    neighborIndex = i;
                    break;
                }
            }
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

    /**
     * Verwijder een vertex en alle edges die ernaar wijzen
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
        for (int i = 0; i < adjacencyList.size(); i++) {
            LinkedList<Edge<T>> edges = adjacencyList.get(i).edges;
            for (int j = edges.size() - 1; j >= 0; j--) {
                if (Objects.equals(edges.get(j).getTargetVertex(), vertex)) {
                    edges.remove(j);
                }
            }
        }
    }
}
