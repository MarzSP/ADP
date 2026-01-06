package app.hashtable;

/**
 * Verbeterde versie van HashTable:
 * - verkleint de tabel bij lage load factor
 * - voorkomt onnodig lange probe-routes na veel removes
 */
public class VerbeteringHashtableShrink<K, V> implements iHashable<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double MAX_LOAD_FACTOR = 0.75;
    private static final double MIN_LOAD_FACTOR = 0.25; // 👈 verbetering

    private Object[] keys;
    private Object[] values;
    private int size;

    public VerbeteringHashtableShrink() {
        keys = new Object[DEFAULT_CAPACITY];
        values = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private int hash(K key, int length) {
        return Math.abs(key.hashCode()) % length;
    }

    private int hash(K key) {
        return hash(key, keys.length);
    }

    private void ensureCapacityForAdd() {
        if ((size + 1) > (int) (keys.length * MAX_LOAD_FACTOR)) {
            resize(keys.length * 2);
        }
    }

    // Verbetering: Verklein bij lage load factor
    private void ensureCapacityForRemove() {
        if (keys.length > DEFAULT_CAPACITY &&
                size < (int) (keys.length * MIN_LOAD_FACTOR)) {
            resize(keys.length / 2);
        }
    }

    private void resize(int newCapacity) {
        Object[] oldKeys = keys;
        Object[] oldValues = values;

        keys = new Object[newCapacity];
        values = new Object[newCapacity];
        size = 0;

        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null) {
                put((K) oldKeys[i], (V) oldValues[i]);
            }
        }
    }

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

    private int findInsertIndex(K key) {
        int index = hash(key);

        while (keys[index] != null && !keys[index].equals(key)) {
            index = (index + 1) % keys.length;
        }
        return index;
    }

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

    @Override
    public V get(K key) {
        if (key == null) return null;

        int index = findIndex(key);
        if (index == -1) return null;

        return (V) values[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public V remove(K key) {
        if (key == null) return null;

        int index = findIndex(key);
        if (index == -1) return null;

        V removedValue = (V) values[index];

        keys[index] = null;
        values[index] = null;
        size--;

        int next = (index + 1) % keys.length;
        while (keys[next] != null) {
            K rehashKey = (K) keys[next];
            V rehashValue = (V) values[next];

            keys[next] = null;
            values[next] = null;
            size--;

            put(rehashKey, rehashValue);
            next = (next + 1) % keys.length;
        }

        // De verbetering: verklein de tabel indien nodig
        ensureCapacityForRemove();

        return removedValue;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }
}
