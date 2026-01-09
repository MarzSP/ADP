package app.graph;

/**
 * Class voor Vertices in een graaf
 * De class omvat alleen de data van de vertex
 * @param <T> genertic type voor de data
 */
public class Vertex<T> implements IVertex<T> {

    private T data;

    public Vertex(T data){
        this.data = data;
    }

    @Override
    public T getData(){
        return data;
    }

}
