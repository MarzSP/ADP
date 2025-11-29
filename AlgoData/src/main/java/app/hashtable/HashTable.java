package app.hashtable;

/**
 * Implementatie van een hashtable met generieke types voor key en value
 * @param <K> type van de key
 * @param <V> type van de value
 */
public class HashTable<K, V> implements iHashable<K, V> {

    public K[] array; //array om de keys op te slaan
    public V[] values; //array om de values op te slaan
    public int size; //aantal elementen in de hashtable
    public static final int DEFAULT_CAPACITY = 16; //standaard capaciteit van de hashtable

    // Constructor
    @SuppressWarnings("unchecked")
    public HashTable() {
        array = (K[]) new Object[DEFAULT_CAPACITY];
        values = (V[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Hashfunctie om de index in de array te berekenen
     * Math.abs om negatieve indexen te voorkomen
     * key.hashCode() geeft een unieke integer voor de key
     * modulo array.length zorgt ervoor dat de index binnen de grenzen van de array blijft omdat de array lengte verschillend kan zijn
     * @param key  key waarvoor de index berekend moet worden
     * @return index in de array
     */
    public int hash(K key) {
        return Math.abs(key.hashCode()) % array.length;
    }

    /**
     * Zoekt de juiste plek voor een key in de array
     * @param key key waarvoor de plek gezocht moet worden
     * @return index in de array waar de key geplaatst kan worden
     */
    public int findPlace(K key) {
        int index = hash(key);

        //Linear probe: ga door totdat er een lege plek is of de key gevonden is
        while (array[index] != null && !array[index].equals(key)) {
            index = (index + 1) % array.length; // lineaire probing
        }
        return index;
    }

    /**
     * Voegt een key-value paar toe aan de hashtable
     * @param key key die toegevoegd moet worden
     * @param value value die toegevoegd moet worden
     */
    @Override
    public void put(K key, V value) {
        int index = findPlace(key);

        //Als de plek leeg is, verhoog de size
        if (array[index] == null) {
            size++;
        }

        array[index] = key; // sla key op
        values[index] = value; // sla value op

    }

    /**
     * Haalt de value op voor een gegeven key
     * @param key de key waarvan de value opgezocht wordt
     * @return value voor de key als er geen is dan null
     */
    @Override
    public V get(K key) {
        int index = findPlace(key);

        if (array[index] == null) {
            return null; // key niet gevonden
        }

        return values[index];
    }

    /**
     * Verwijdert een key-value paar uit de hashtable
     * @param key de key die verwijderd moet worden
     * @return de verwijderde value
     */
    @Override
    public V remove(K key) {
        int index = findPlace(key);
        if (array[index] == null) {
            return null; // key niet gevonden
        }
        V removedValue = values[index];

        array[index] = null;
        values[index] = null;
        size--;

        return removedValue;
    }

    /**
     * Controleert of de key al bestaat. Geeft het iets terug? Dan bestaat de key al
     * @param key de key die gechecked moet worden
     * @return true als de key bestaat, anders false
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
    * Geeft het aantal elementen in de hashtable terug
     * @return aantal elementen in de hashtable
    */
    @Override
    public int size() {
        return size;
    }

}
