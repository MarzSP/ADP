package app.graph;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class GraphVerbetering<T extends Comparable<T>> {

    private final LinkedList<VertexEntry> adjacencyList = new LinkedList<>();

    private class VertexEntry {
        final Vertex<T> vertex;
        final LinkedList<Edge<T>> edges = new LinkedList<>();

        VertexEntry(Vertex<T> vertex) {
            this.vertex = vertex;
        }
    }

    private VertexEntry findEntry(Vertex<T> vertex) {
        for (VertexEntry entry : adjacencyList) {
            if (Objects.equals(entry.vertex, vertex)) return entry;
        }
        return null;
    }

    private int indexOfVertex(Vertex<T> vertex) {
        int index = 0;
        for (VertexEntry entry : adjacencyList) {
            if (Objects.equals(entry.vertex, vertex)) return index;
            index++;
        }
        return -1;
    }

    private VertexEntry getOrCreateEntry(Vertex<T> vertex) {
        VertexEntry node = findEntry(vertex);
        if (node == null) {
            node = new VertexEntry(vertex);
            adjacencyList.add(node);
        }
        return node;
    }

    public void addVertex(Vertex<T> vertex) {
        getOrCreateEntry(vertex);
    }

    public void addEdge(Vertex<T> source, Vertex<T> target, int weight) {
        VertexEntry src = getOrCreateEntry(source);
        addVertex(target);
        src.edges.add(new Edge<>(weight, target));
    }

    public void removeVertex(Vertex<T> vertex) {
        // verwijder de vertex zelf
        for (int i = 0; i < adjacencyList.size(); i++) {
            if (Objects.equals(adjacencyList.get(i).vertex, vertex)) {
                adjacencyList.remove(i);
                break;
            }
        }
        // verwijder edges die naar de vertex wijzen
        for (VertexEntry vertexEntry : adjacencyList) {
            LinkedList<Edge<T>> edges = vertexEntry.edges;
            for (int j = edges.size() - 1; j >= 0; j--) {
                if (Objects.equals(edges.get(j).getTargetVertex(), vertex)) {
                    edges.remove(j);
                }
            }
        }
    }

    public double[] dijkstraFromIndex(int startIndex) {
        int n = adjacencyList.size();
        if (startIndex < 0 || startIndex >= n) {
            throw new IllegalArgumentException("startIndex out of range");
        }

        double[] distance = new double[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[startIndex] = 0.0;

        class PQNode {
            final int index;
            final double dist;
            PQNode(int index, double dist) {
                this.index = index;
                this.dist = dist;
            }
        }

        PriorityQueue<PQNode> pq =
                new PriorityQueue<>(Comparator.comparingDouble(a -> a.dist));

        pq.offer(new PQNode(startIndex, 0.0));

        while (!pq.isEmpty()) {
            PQNode current = pq.poll();
            int u = current.index;

            if (current.dist != distance[u]) continue;

            if (visited[u]) continue;
            visited[u] = true;

            VertexEntry currentNode = adjacencyList.get(u);

            for (Edge<T> edge : currentNode.edges) {
                Vertex<T> targetVertex = edge.getTargetVertex();

                int v = indexOfVertex(targetVertex);
                if (v == -1) continue;
                if (visited[v]) continue;

                double candidate = distance[u] + edge.getWeight();
                if (candidate < distance[v]) {
                    distance[v] = candidate;
                    pq.offer(new PQNode(v, candidate));
                }
            }
        }

        return distance;
    }
}
