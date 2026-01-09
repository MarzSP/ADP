package app.graph;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex<?> other)) return false;
        return Objects.equals(this.data, other.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

}
