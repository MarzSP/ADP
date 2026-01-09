package app.hashtable;

import java.security.Key;

/**
 * Generic hashtable implementatie met linear probing
 * - Collisions: linear probing
 * - Resize: bij load factor > 0.75 doen we rehash naar een grotere array
 */
public class HashTable<K, V> implements iHashable<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double MAX_LOAD_FACTOR = 0.75;

    private K[] keys;
    private Object[] values;
    private int size;

    public HashTable() {
        keys = new K[DEFAULT_CAPACITY];
        values = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Berekent de hash index voor een key
     * TC: O(1)
     * SC: O(1)
     */
    private int hash(K key, int length) {
        return Math.abs(key.hashCode()) % length;
    }


    private int hash(K key) {
        return hash(key, keys.length);
    }

    /**
     * Controleert of er genoeg ruimte is om een element toe te voegen.
     * Zo niet, dan resize + rehash.
     * TC: Best O(1), Worst O(n) wanneer resize nodig is
     * SC: Worst O(n) nieuwe arrays bij resize
     */
    private void ensureCapacityForAdd() {
        if ((size + 1) > (int) (keys.length * MAX_LOAD_FACTOR)) {
            resize(keys.length * 2);
        }
    }

    /**
     * Vergroot de hashtable en herplaatst alle elementen.
     * TC: O(n) alle elementen opnieuw hashen
     * SC: O(n) nieuwe arrays
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Object[] oldKeys = keys;
        Object[] oldValues = values;

        keys = new K[newCapacity];
        values = new Object[newCapacity];
        size = 0;

        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null) {
                put((K) oldKeys[i], (V) oldValues[i]);
            }
        }
    }

    /**
     * Zoekt de index van een key, of geeft -1 terug als hij niet bestaat.
     * TC: Best O(1) Worst O(n) want bij veel collisions moet je ver zoeken
     * SC: O(1)
     */
    private int findIndex(K key) {
        int index = hash(key);

        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return index;
            }
            index = (index + 1) % keys.length;
        }

        return -1;
    }

    /**
     * Zoekt een lege plek om een key te plaatsen (of de plek van dezelfde key).
     * TC: Best O(1) Worst O(n) veel collisions
     * SC: O(1)
     */
    private int findInsertIndex(K key) {
        int index = hash(key);

        while (keys[index] != null && !keys[index].equals(key)) {
            index = (index + 1) % keys.length;
        }

        return index;
    }

    /**
     * Voegt een key-value paar toe of overschrijft een bestaande key.
     * TC: Best O(1) Worst O(n) bij resize of collisions
     * SC: O(1)
     */
    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("key mag niet null zijn");

        ensureCapacityForAdd();

        int index = findInsertIndex(key);

        if (keys[index] == null) {
            size++;
        }

        keys[index] = key;
        values[index] = value;
    }

    /**
     * Haalt de value op voor een gegeven key.
     * TC: Best O(1) Worst O(n)
     * SC: O(1)
     */
    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) {
        if (key == null) return null;

        int index = findIndex(key);
        if (index == -1) return null;

        return (V) values[index];
    }

    /**
     * Verwijdert een key-value paar uit de hashtable.
     * Omdat we geen tombstones gebruiken, moeten we de probe-keten repareren:
     * - Na het verwijderen rehashen we alle keys erna totdat we een null tegenkomen.
     * TC: Best O(1) Worst O(n) bij veel collisions
     * SC: O(1)
     */
    @SuppressWarnings("unchecked")
    @Override
    public V remove(K key) {
        if (key == null) return null;

        int index = findIndex(key);
        if (index == -1) return null;

        V removedValue = (V) values[index];

        // Maak het gat
        keys[index] = null;
        values[index] = null;
        size--;

        // Rehash de cluster na dit gat anders worden keys "onvindbaar"
        int next = (index + 1) % keys.length;
        while (keys[next] != null) {
            K rehashKey = (K) keys[next];
            V rehashValue = (V) values[next];

            keys[next] = null;
            values[next] = null;
            size--; // put() telt hem straks weer mee

            put(rehashKey, rehashValue);

            next = (next + 1) % keys.length;
        }

        return removedValue;
    }

    /**
     * Controleer of een key bestaat
     * TC: Best O(1), Average O(1), Worst O(n)
     * SC: O(1)
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Geef het aantal opgeslagen elementen terug
     * TC: O(1)
     * SC: O(1)
     */
    @Override
    public int size() {
        return size;
    }
}
