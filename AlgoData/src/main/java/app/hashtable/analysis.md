# Hashtable
## Functions

- private LinkedList<Entry<K, V>>[] table: Array met lengte 'capacity' die LinkedLists van Entry-objecten bevat.
- private int capacity: De capaciteit van de hashtable, 11 standaard.
- private int size: Het aantal key-value paren in de hashtable.
- private float loadFactor: De load factor van de hashtable, 0.75 standaard

- Inner class Entry<K, V>:
  - K key: De sleutel van het entry.
  - V value: De waarde van het entry.

- private int hash(K key): Berekent de hashcode voor een gegeven sleutel en retourneert de index in de tabel.
Key.HashCode() % capacity. Ik gebruik hashCode() om een unieke code te krijgen voor elke key. Dit is deterministisch en consistent met equals().
FloorMod om negatieve waarden te vermijden. De modulo gebruik ik om de hashwaarde binnen de grenzen van de tabelcapaciteit te houden.

- private findIndexForKey(K key): Zoekt de index van een gegeven sleutel in de hashtable.

- public void put(K key, V value): Voegt een key-value paar toe aan de hashtable. Als de sleutel al bestaat, wordt de waarde bijgewerkt.
Controleert of de load factor overschreden is na het toevoegen van een nieuw element. Zo ja, wordt de tabel vergroot en opnieuw gehasht.
Vindt daarna de index voor de sleutel en voegt het entry toe aan de bijbehorende LinkedList. Of maakt een nieuwe LinkedList als die nog niet bestaat.

- public V get(K key): Haalt de waarde op die overeenkomt met de gegeven sleutel. Retourneert null als de sleutel niet bestaat.
Zoekt de index voor de sleutel en doorloopt de LinkedList op die index om het bijbehorende entry te vinden. Het doorzoekt de keten met equals() om de juiste key te vinden.

- public V remove(K key): Verwijdert het key-value paar dat overeenkomt met de gegeven sleutel en retourneert de verwijderde waarde. Retourneert null als de sleutel niet bestaat.
We gebruiken de iterator om veilig door de LinkedList te lopen en het entry te verwijderen als de sleutel overeenkomt. Iterator kan alleen het laatst opgehaalde element verwijderen, daarom moet next() eerst worden aangeroepen zodat de iterator weet welk element verwijderd moet worden.
Dan remove en size-- en geven de oude waarde terug.

- private void resize(); Vergroot de capaciteit van de hashtable en herverdeelt alle bestaande entries.
Maakt een nieuwe tabel met dubbele capaciteit en herhaalt alle bestaande entries om ze opnieuw te hashen en toe te voegen aan de nieuwe tabel.
De resize doen we met capaciteit * 2 + 1 om oneven getallen te behouden, wat kan helpen bij het verminderen van collisions.
We lopen door alle entries van de oude tabel en voegen ze rehashed toe aan de nieuwe tabel. Als alles verplaatst is , stellen we de tabel en capaciteit in op de nieuwe waarden.

## Verbeterpunten
- Gebruik linear of quadratic probing ipv chaining om collisions te behandelen. Dit kan de prestaties verbeteren bij een hoge load factor en vermindert geheugenoverhead door het vermijden van LinkedLists.
- Een andere hashkey gebruiken zodat er minder collisions zijn. Bitmixing kan helpen om de bits van de hashcode beter te verspreiden door de bits te verschuiven en te combineren.
````
private int hashKey(K key, int modulo) {
    int h = key.hashCode();
    h ^= (h >>> 16);
    return Math.floorMod(h, modulo);
}`````