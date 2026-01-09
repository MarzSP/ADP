package app.graph;

import java.util.*;

/**
 * Generieke implementatie van een weighted Graph
 * Gebruikt Class Edge en Class Vertex
 * Geimplementeerd met een PriorityQueue voor Dijkstra's algoritme
 **/
public class Graph<T extends Comparable <T>> {

    private Map<Vertex<T>, List<Edge<T>>> adjacencyList = new HashMap<>();

    public void addVertex(Vertex<T> vertex){
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex<T> source, Vertex<T> target, int weight){
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.putIfAbsent(target, new ArrayList<>());

        adjacencyList.get(source).add(new Edge<T>(weight, target));
    }

    public void Dijsktra() {
        return;
    }

    public void removeVertex(){
        return;
    }

    public void findIndex(){
        return;
    }
}
