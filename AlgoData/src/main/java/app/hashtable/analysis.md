## Hashtable
Een hashtable slaat gegevens op als keys en value paren. De key wordt gebruikt om de bijbehorende value snel op te zoeken. 
Deze hashtable implementeerd een hashtable waarin er een array is voor keys, en een array voor values.

Er wordt gebruik gemaakt van linear probing om collisions op te lossen. Dit betekent dat als er een collision optreedt (wanneer twee keys dezelfde hashwaarde hebben), de hashtable zoekt naar de volgende beschikbare index in de array om het nieuwe key-value paar op te slaan.

Er zijn generics gebruikt om de hashtable flexibel te maken voor verschillende typen keys en values.

### Time complexity
In het beste geval is de time complexity O(1), in het slechtste geval is het O(n).

Per functie: 
- hash(K): Maakt een geldige hashwaarde voor een gegeven key. Dit is O(1). 


- FindIndex(Key): In het beste geval is de time complexity O(1) wanneer de key direct op de berekende hash-index staat.
  In het slechtste geval is de time complexity O(n) wanneer er veel collisions zijn en er lineair door de array moet worden gezocht.


- findInsertIndex(K key): 
In het beste geval is de time complexity O(1) wanneer de berekende hash-index direct beschikbaar is.
In het slechtste geval is de time complexity O(n) bij veel collisions, waardoor meerdere opeenvolgende index moeten worden gecontroleerd.


- put(K key, V value): In het beste geval is de time complexity O(1) wanneer er geen collisions optreden en geen resize nodig is.
  In het slechtste geval is de time complexity O(n). Dit kan optreden wanneer er veel collisions zijn of wanneer de load factor groter wordt dan 0.75, waardoor de hashtable wordt vergroot en alle bestaande elementen opnieuw gehasht moeten worden.


- V get(K key): Zoekt naar de value die hoort bij de gegeven key. In het beste geval O(1) wanneer er geen collisions zijn. In het slechtste geval O(n) wanneer er veel collisions zijn en de hashtable bijna vol is, waardoor er veel lineair gezocht moet worden naar de juiste index.<br><br>

- V remove(K key): Zoekt naar de key K en verwijdert de value die daar bij hoort. In het beste geval O(1) wanneer de key meteen gevonden wordt. In het slechtste geval O(n) wanneer er veel collisions zijn en de hashtable bijna vol is, waardoor er veel lineair gezocht moet worden naar de juiste index.Na resize moet de linear-probe keten worden gerepareerd, dus alle opeenvolgende elementen na het verwijderde element worden opnieuw gehashed totdat een lege plek is bereikt.

- containsKey(K key): Controleert of de hashtable de opgegeven key heeft. In het beste geval O(1) wanneer er geen collisions zijn. In het slechtste geval O(n) wanneer er veel collisions zijn en de hashtable bijna vol is, waardoor er veel lineair gezocht moet worden naar de juiste index.<br><br>

- int size(): Geeft het aantal key-value paren in de hashtable terug. Dit is O(1) omdat de grootte wordt bijgehouden als een variabele.


### Verbeterpunten
- De hashtable gebruikt linear probing om collisions op te lossen, wat kan leiden tot primary clustering en verminderde prestaties bij veel collisions. Andere methodes zijn bijvoorbeeld separate chaining (het linkedList idee) of quadratic probing waarin er een kwadratische sprong wordt gemaakt bij een collision.
- De hashtable groeit automatisch wanneer de load factor groter is dan 0.75. Maar de table verkleint niet als er veel waarden worden removed. Dus een verbeterpunt zou zijn ook een grens te hebben voor het verkleinen van de hashtable wanneer de load factor onder een drempel komt, zoals 0.25, met rehashing.
- De load factor is nu hardcoded maar dit kan dus ook een parameter zijn die de gebruiker kan instellen bij het maken van de hashtable.
