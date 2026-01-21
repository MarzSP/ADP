# Hashtable (Separate Chaining)

## Velden 
- `private LinkedList<Entry<K, V>>[] table`  
  Array met lengte `capacity`. Elke positie bevat of `null` (leeg) of een `LinkedList` met `Entry`-objecten 

- `private int capacity`  
  Aantal posities in de array (`table.length`). Standaard 11.

- `private int size`  
  Aantal opgeslagen key-value paren (aantal `Entry`’s totaal over alle ketens).

- `private static final double LOAD_FACTOR = 0.75`  
  Drempel voor resizing. Load factor = `size / capacity`.

## Inner class: `Entry<K, V>`
- `final K key`  
  De sleutel. `final` zodat de key niet kan veranderen.

- `V value`  
  De waarde. Deze kan veranderen bij een update (`put` met bestaande key).

## Methoden

### `private int hashKey(K key, int modulo)`
Berekent de array-index voor een key:
- Gebruikt `key.hashCode()` om een  hash te krijgen die consistent moet zijn met `equals()`.
- Gebruikt `Math.floorMod(hash, modulo)` zodat negatieve hashcodes ook een geldige (niet-negatieve) index opleveren.
- Gebruikt modulo (`modulo`) om de hash binnen `[0 .. modulo-1]` te houden.

### `private int findIndexFor(K key)`
Helper die de index bepaalt op basis van de *huidige* `capacity`:
- `return hashKey(key, capacity);`
- TC: O(1)

### `public V put(K key, V value)`
Voegt een key-value paar toe of werkt een bestaande key bij.
Stappen:
1. `key` mag niet `null` zijn (`Objects.requireNonNull`).
2. Check load factor *vóór* toevoegen: als `size / capacity >= 0.75` → `resize(capacity * 2 + 1)`.
3. Bepaal array-index met `findIndexFor(key)`.
4. Als de `LinkedList` op die index nog `null` is: maak een nieuwe `LinkedList`.
5. Loop door de keten:
  - Bestaat de key al? Update de value en return de oude value.
  - Bestaat de key nog niet? Voeg nieuwe `Entry` toe, `size++`, return `null`.

TC: Best O(1), worst O(n) bij veel collisions in 1 keten.

### `public V get(K key)`
Haalt de value op voor een key.
Stappen:
1. `key` mag niet `null` zijn.
2. Bepaal array-index met `findIndexFor(key)`.
3. Als de `LinkedList` op die index `null` is: return `null`.
4. Loop door de keten en vergelijk keys met `equals()`:
  - Match gevonden → return `value`
  - Geen match → return `null`

TC: gemiddeld O(1), worst O(n).

### `public V remove(K key)`
Verwijdert een key-value paar en geeft de oude value terug.
Stappen:
1. `key` mag niet `null` zijn.
2. Bepaal array-index en haal de `LinkedList` op.
3. Als de lijst `null` is: return `null`.
4. Gebruik een `Iterator` om veilig te verwijderen tijdens iteratie:
  - `next()` haalt het huidige element op
  - `iterator.remove()` verwijdert het laatst opgehaalde element veilig (zonder ConcurrentModificationException)
5. Bij match:
  - bewaar oude value
  - verwijder entry (`it.remove()`), `size--`
  - als de keten leeg is: `table[index] = null`
  - return oude value
6. Geen match: return `null`

TC: Best O(1), worst O(n).

### `public void resize(int newCapacity)`
Vergroot de tabel en “rehash” alle entries, omdat de index-berekening afhangt van de capaciteit (`hash % capacity`).
Stappen:
1. Maak een nieuwe array `newTable` met lengte `newCapacity` (alle posities zijn `null`).
2. Loop door alle ketens van de oude `table`.
3. Voor elke entry:
  - bereken nieuwe index met `hashKey(entry.key, newCapacity)`
  - maak op die index een nieuwe `LinkedList` als die nog `null` is
  - voeg de entry toe aan de nieuwe lijst
4. Zet `table = newTable` en `capacity = newCapacity`.

TC: O(n), SC: O(n).

### `public void clear()`
Verwijdert alle entries:
- `Arrays.fill(table, null);`
- `size = 0;`

TC: O(capacity), SC: O(1).

### `public boolean containsKey(K key)`
Controleert of een key bestaat. Key mag niet `null` zijn, maar dit is al afgevangen in `put` en `get`.

### `public int size()`
Returns `size` (aantal entries).

---

## Verbeterpunten
## Verbeterpunten en ontwerpkeuzes

### 1) Collision-afhandeling: separate chaining vs open addressing
In deze implementatie is gekozen voor **separate chaining** (LinkedList per array-index), omdat dit robuust is en eenvoudig te implementeren.

Een mogelijk alternatief is **open addressing** (bijvoorbeeld linear of quadratic probing), waarbij alle entries direct in de array worden opgeslagen.

**Trade-off:**
- Open addressing heeft minder geheugenoverhead, omdat er geen extra LinkedList-objecten nodig zijn.
- Door betere cache-locality kan open addressing in de praktijk sneller zijn bij lage load factors.
- Separate chaining blijft stabiel bij hogere load factors en is eenvoudiger te resizen.

**Conclusie:**  
De keuze tussen separate chaining en open addressing hangt af van de use-case:
- bij geheugen- of cache-gevoelige toepassingen kan open addressing gunstiger zijn;
- bij onvoorspelbare of hogere load factors is separate chaining vaak robuuster.

---

### 2) Verbetering van de hashfunctie door bitmixing
De huidige implementatie gebruikt `hashCode()` gecombineerd met modulo om een array-index te berekenen.  
Sommige `hashCode()` implementaties bevatten echter patronen in hun bits, wat kan leiden tot clustering.

Door **bitmixing** toe te passen, worden de bits van de hash beter verspreid voordat modulo wordt toegepast:

```java
private int hashKey(K key, int modulo) {
    int h = key.hashCode();
    h ^= (h >>> 16);
    return Math.floorMod(h, modulo);
}
````
