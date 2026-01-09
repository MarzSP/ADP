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

    /**
     * Voeg een vertex toe aan de graaf (in de adjacencyList) als deze nog niet bestaat
     * @param vertex 
     */
    public void addVertex(Vertex<T> vertex){
        if (findNode(vertex) == null) {
            adjacencyList.add(new AdjacencyNode(vertex));
        }
    }

    /**
     * Voeg een gerichte edge toe van source vertex naar target vertex met gegeven gewicht
     * @param source
     * @param target
     * @param weight
     */
    public void addEdge(Vertex<T> source, Vertex<T> target, int weight) {
        addVertex(source);
        addVertex(target);

        AdjacencyNode src = findNode(source);
        if (src != null) {
            src.edges.add(new Edge<>(weight, target));
        }
    }

    /**
     * Dijkstra met adjacency list
     * Returns afstanden met een double[] met Double.POSITIVE_INFINITY voor onbezochte vertices
     * Boolean[] houdt bij welke vertices al gedaan zijn
     * Double[] houdt de kortste afstanden bij
     * @param startIndex
     * @return distances from startIndex to all others
     */
    public double[] dijkstra(int startIndex) {
        int n = adjacencyList.size();
        if (startIndex < 0 || startIndex >= n) {
            throw new IllegalArgumentException("startIndex out of range");
        }

        double[] dist = new double[n];
        boolean[] done = new boolean[n];

        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[startIndex] = 0.0;

        int start = findClosestNotDone(dist, done);
        // start should equal startIndex but using the find ensures consistent behavior
        dijkstraRecursive(start, dist, done);

        return dist;
    }

    /**
     * Base case: currentIndex == -1. Dan zijn er geen bereikbare vertices meer
     * Recursive case: update afstanden van buren van currentIndex
     * @param currentIndex 
     * @param distance
     * @param visited
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
        int nextIndex = findClosestNotDone(distance, visited);
        dijkstraRecursive(nextIndex, distance, visited);
    }

    private int findClosestNotDone(double[] distance, boolean[] done) {
        int bestIndex = -1;
        double bestDistance = Double.POSITIVE_INFINITY;

        for (int i = 0; i < distance.length; i++) {
            if (!done[i] && distance[i] < bestDistance) {
                bestDistance = distance[i];
                bestIndex = i;
            }
        }
        return bestIndex;
    }


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
