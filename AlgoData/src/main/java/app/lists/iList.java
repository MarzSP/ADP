package app.lists;

/**
 * Interface iList<T>
 * Zelfgemaakte interface iList<T> (ipv java interface List).
 * De interface legt niet vast hoe de lijst intern wordt opgeslagen, dit wordt gedaan door de implementatie ArrayList of LinkedList.
 * ArrayLists zijn sneller op basis van index gebaseerde searches en LinkedLists zijn beter bij toevoegingen aan het begin van een lijst.
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
