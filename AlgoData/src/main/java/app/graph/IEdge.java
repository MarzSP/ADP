package app.graph;

public interface IEdge<T> {

    Vertex<T> getTargetVertex();

    int getWeight();
}
