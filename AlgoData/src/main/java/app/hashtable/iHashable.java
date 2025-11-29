package app.hashtable;

/**
 * Interface voor de hashtable implementatie
 * @param <K> type van de key
 * @param <V> type van de value
 */
public interface iHashable<K, V> {

    void put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    int size();

}