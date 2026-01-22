package app.hashtable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Hashtable implementatie met separate chaining.
 * - Collisions: chaining (LinkedList per Entry)
 * - Resize: bij load factor > 0.75
 * - Default capacity: 11
 * TC: get, put, remove: O(1) gemiddeld, O(n) in slechtste geval bij veel collisions
 * SC: O(n) bij resize
 * @param <K> key type
 * @param <V> value type
 */
public class HashTable<K, V> {

    private int capacity;
    private int size = 0;
    private static final double LOAD_FACTOR = 0.75;

    private LinkedList<Entry<K, V>>[] table;

    private static class Entry<K, V> {
        final K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Constructor 1: initialiseer hashtable met gegeven capaciteit
     * @param capacity moet > 0 zijn
     */
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");

        this.capacity = capacity;
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
    }

    /**
     * Constructor 2: initialiseer hashtable met default capaciteit(11)
     */
    public HashTable() {
        this(11);
    }


    /**
     * Hash een key naar een index. FloorMod zorgt voor positieve index.
     * Bijvoorbeeld: Zet key "abc", met waarde 42, in een tabel met modulo 11
     * "abc".hashCode() = 96354 dan modulo, rest 5.
     * Entry [index 5]:
     *  ├─ key   - "abc"
     *  └─ value - 42
     *  TC: O(1)
     * @param key key
     * @param modulo modulo (capacity)
     * @return index
     */
    private int hashKey(K key, int modulo) {
        return Math.floorMod(key.hashCode(), modulo);
    }

    /**
     * Vind de index voor een key
     * TC: O(1)
     * @param key key
     * @return  index
     */
    private int findIndexFor(K key) {
        return hashKey(key, capacity);
    }

    /**
     * Insert of update key/value pair
     * TC: Best O(1), Worst O(n) bij veel collisions
     * @return vorige waarde of null bij new entry
     */
    public V put(K key, V value) {
        Objects.requireNonNull(key, "key is null");
        Objects.requireNonNull(value, "value is null");

        // Resize voordat we toevoegen als load factor overschreden wordt
        if ((double) size / capacity >= LOAD_FACTOR) {
            resize(capacity * 2 + 1);
        }

        int index = findIndexFor(key);
        LinkedList<Entry<K, V>> entries = table[index];

        // Maak alleen een nieuwe LinkedList aan als nodig
        if (entries == null) {
            entries = new LinkedList<>();
            table[index] = entries;
        }

        // Update pad: key bestaat al -> value vervangen
        for (Entry<K, V> e : entries) {
            if (key.equals(e.key)) {
                V old = e.value;
                e.value = value;
                return old;
            }
        }

        entries.add(new Entry<>(key, value));
        size++;
        return null;
    }

    /**
     * Haal de value op voor een key
     * TC: Best O(1), Worst O(n) bij veel collisions
     * @return waarde of null als key niet bestaat
     */
    public V get(K key) {
        Objects.requireNonNull(key, "key is null");

        int index = findIndexFor(key);
        LinkedList<Entry<K, V>> entries = table[index];
        if (entries == null) {
            return null;
        }

        for (Entry<K, V> e : entries) {
            if (key.equals(e.key)) {
                return e.value;
            }
        }
        return null;
    }

    /**
     * Verwijder key uit tabel
     * TC: Best O(1), Worst O(n) bij veel collisions.
     * @return waarde of null
     */
    public V remove(K key) {
        Objects.requireNonNull(key, "key is null");

        int index = findIndexFor(key);
        LinkedList<Entry<K, V>> entries = table[index];
        if (entries == null) {
            return null;
        }

        Iterator<Entry<K, V>> it = entries.iterator();
        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            if (!key.equals(entry.key)) {
                continue;
            }
            V old = entry.value;
            it.remove();
            size--;

            if (entries.isEmpty()) {
                table[index] = null;
            }
            return old;
        }
        return null;
    }

    /**
     * Resize en rehash entries naar een grotere tabel
     * Capacity wordt verdubbeld + 1
     * TC: O(n) SC: O(n)
     */
    @SuppressWarnings("unchecked")
    public void resize(int newCapacity) {
        LinkedList<Entry<K, V>>[] newTable = (LinkedList<Entry<K, V>>[]) new LinkedList[newCapacity];

        for (LinkedList<Entry<K, V>> entries : table) {
            if (entries == null) {
                continue;
            }
            for (Entry<K, V> entry : entries) {
                int index = hashKey(entry.key, newCapacity);
                LinkedList<Entry<K, V>> newEntries = newTable[index];
                if (newEntries == null) {
                    newEntries = new LinkedList<>();
                    newTable[index] = newEntries;
                }
                newEntries.add(new Entry<>(entry.key, entry.value));
            }
        }

        this.table = newTable;
        this.capacity = newCapacity;
    }

    /**
     * Verwijdert alle entries uit de hashtable.
     * Alle buckets worden op null gezet zodat er geen lege chains achterblijven
     * TC: O(capacity)
     * SC: O(1)
     */
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    /**
     * Check of key in tabel zit
     * TC: Best O(1), Worst O(n) bij veel collisions
     * @return true als key bestaat
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Aantal entries in tabel
     */
    public int size() {
        return size;
    }


}
