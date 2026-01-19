package app.hashtable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Hashtable implementatie met separate chaining.
 * - Collisions: chaining (LinkedList per Entry)
 * - Resize: bij load factor > 0.75
 * TC: get, put, remove: O(1) gemiddeld, O(n) in slechtste geval bij veel collisions
 * SC: O(n) bij resize
 * @param <K> key type
 * @param <V> value type
 */
public class HashTable<K, V> {
    // defaults to 11
    private int capacity;
    private int size = 0;

    public HashTable(int capacity) {
        this.capacity = capacity;

        //noinspection unchecked
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
    }
    public HashTable() {
        this(11);
    }
    private LinkedList<Entry<K, V>>[] table;

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Hash functie opties
     * put("abc", 42)
     * "abc".hashCode()" = 96354 dan modulo, rest 5.
     * Entry [index 5]:
     *  ├─ key   - "abc"
     *  └─ value - 42
     */

    private int hashKey(K key, int modulo) {
        return Math.floorMod(key.hashCode(), modulo);
    }

/*
    private int hashKey(K key, int modulo) {
    int h = Objects.hashCode(key);
    int mixed = h ^ (h >>> 16);
    return Math.floorMod(mixed, modulo);
    } */

/*
    private int hashKey(K key, int modulo) {
    String s = String.valueOf(key);
    int h = 0;
    for (int i = 0; i < s.length(); i++) {
        h = (31 * h + s.charAt(i)) % modulo;
    }
    return h;
    } */


    /**
     * vind de index voor een key
     * findIndexFor("abc")  =  5
     * @param key key
     * @return  index
     */
    private int findIndexFor(K key) {
        return hashKey(key, capacity);
    }

    /**
     * Insert/update key/value pair
     * TC: Best O(1), Worst O(n) bij veel collisions
     * @return vorige waarde of null bij new entry
     */
    public V put(K key, V value) {
        Objects.requireNonNull(key, "key is null");

        int index = findIndexFor(key);
        LinkedList<Entry<K, V>> entries = table[index];
        if (entries == null) {
            entries = new LinkedList<>();
            table[index] = entries;
        }

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

    public boolean contains(K key) {
        return get(key) != null;
    }

    /**
     * Get waarde voor key
     * TC: Best O(1), Worst O(n) bij veel collisions
     * @return waarde of null
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
     * TC: Best O(1), Worst O(n) bij veel collisions. SC: O(1)
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
     * Aantal entries in tabel
     */
    public int size() {
        return size;
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

    void clear() {
        for (int i = 0; i < table.length; i++) {
            LinkedList<Entry<K, V>> entries = table[i];
            if (entries == null) {
                continue;
            }
            entries.clear();
            table[i] = entries;
        }
        size = 0;
    }

}
