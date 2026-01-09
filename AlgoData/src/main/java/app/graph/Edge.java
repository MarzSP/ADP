package app.graph;

public class Edge<T> implements IEdge<T> {

    private int weight;
    private Vertex<T> target;

    public Edge(int weight, Vertex<T> target){
        this.weight = weight;
        this.target = target;
    }

    @Override
    public Vertex<T> getTargetVertex(){
        return target;
    }

    @Override
    public int getWeight() {
        return weight;
    }

}
