package app.lists;

/**
 * Interface iList<T>
 *
 * @param <T>
 */
public interface iList<T>{

    void add(T element);

    void add(int index, T element);


    T get(int index);


    T set(int index, T element);


    T remove(int index);


    int size();

    boolean contains(T element);


    int indexOf(T element);

    void clear();


    boolean isEmpty();
}
