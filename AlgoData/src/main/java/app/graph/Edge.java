package app.graph;

/**
 * Class voor Edges in een graaf
 * De class omvat het gewicht en de target vertex van de edge
 * Deze class bevat alleen de data van de edge
 * @param <T>
 */
public class Edge<T> implements IEdge<T> {

    private final int weight;
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
