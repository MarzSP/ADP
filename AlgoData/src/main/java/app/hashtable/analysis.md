## Hashtable
Een hashtable slaat gegevens op als keys en value paren. De key wordt gebruikt om de bijbehorende value snel op te zoeken. 
Deze hashtable implementeerd een hashtable waarin er een array is voor keys, en een array voor values.

Er wordt gebruik gemaakt van linear probing om collisions op te lossen. Dit betekent dat als er een collision optreedt (wanneer twee keys dezelfde hashwaarde hebben), de hashtable zoekt naar de volgende beschikbare index in de array om het nieuwe key-value paar op te slaan.

Er zijn generics gebruikt om de hashtable flexibel te maken voor verschillende typen keys en values.

### Time complexity
In het beste geval is de time complexity O(1), in het slechtste geval is het O(n).

Per functie: 
- hash(K): Maakt een geldige hashwaarde voor een gegeven key. Dit is O(1). <br><br>

- FindPlace(K): Zoekt naar de juiste index om een key op te slaan of te vinden. In het beste geval O(1) wanneer er geen collisions zijn. In het slechtste geval O(n) wanneer er veel collisions zijn en de hashtable bijna vol is, waardoor er veel lineair gezocht moet worden naar een vrije index.<br><br>

- put(K key, V value): In het beste geval O(1) wanneer er geen collisions zijn omdat de hashfunctie direct naar de juiste index wijst. In het slechtste geval O(n) wanneer er veel collisions zijn en de hashtable bijna vol is, waardoor er veel lineair gezocht moet worden naar een vrije index.<br><br>

- V get(K key): Zoekt naar de value die hoort bij de gegeven key. In het beste geval O(1) wanneer er geen collisions zijn. In het slechtste geval O(n) wanneer er veel collisions zijn en de hashtable bijna vol is, waardoor er veel lineair gezocht moet worden naar de juiste index.<br><br>

- V remove(K key): Zoekt naar de key K en verwijdert de value die daar bij hoort. In het beste geval O(1) wanneer er geen collisions zijn. In het slechtste geval O(n) wanneer er veel collisions zijn en de hashtable bijna vol is, waardoor er veel lineair gezocht moet worden naar de juiste index.<br><br>

- containsKey(K key): Controleert of de hashtable de opgegeven key heeft. In het beste geval O(1) wanneer er geen collisions zijn. In het slechtste geval O(n) wanneer er veel collisions zijn en de hashtable bijna vol is, waardoor er veel lineair gezocht moet worden naar de juiste index.<br><br>

- int size(): Geeft het aantal key-value paren in de hashtable terug. Dit is O(1) omdat de grootte wordt bijgehouden als een variabele.


### Verbeterpunten
- Momenteel groeit de hashtable niet wanneer deze vol raakt. Dit kan worden opgelost met rezising waar een nieuwe grotere array wordt aangemaakt en alle bestaande key-value paren worden overgezet. Dit is in deze implmenentatie niet gedaan om het als een zuivere hashtable implementatie te houden.
- De hashtable gebruikt linear probing om collisions op te lossen, wat kan leiden tot clustering en verminderde prestaties bij veel collisions. Andere methodes zijn bijvoorbeeld separate chaining (het linkedList idee) of quadratic probing waarin er een kwadratische sprong wordt gemaakt bij een collision.

