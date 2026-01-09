package app.graph;

import app.lists.LinkedList;
import java.util.*;

public class Graph<T extends Comparable<T>> {

    private final LinkedList<AdjNode> adjacencyList = new LinkedList<>();

    private class AdjNode {
        final Vertex<T> vertex;
        final LinkedList<Edge<T>> edges = new LinkedList<>();

        AdjNode(Vertex<T> vertex) {
            this.vertex = vertex;
        }
    }

    private AdjNode findNode(Vertex<T> vertex) {
        for (int i = 0; i < adjacencyList.size(); i++) {
            AdjNode n = adjacencyList.get(i);
            if (Objects.equals(n.vertex, vertex)) return n;
        }
        return null;
    }

    public void addVertex(Vertex<T> vertex){
        if (findNode(vertex) == null) {
            adjacencyList.add(new AdjNode(vertex));
        }
    }

    public void addEdge(Vertex<T> source, Vertex<T> target, int weight) {
        addVertex(source);
        addVertex(target);

        AdjNode src = findNode(source);
        if (src != null) {
            src.edges.add(new Edge<>(weight, target));
        }
    }

    public void dijkstra() {
        //make later dijkstra
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
