package app.graph;

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
