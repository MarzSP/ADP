## Analysis van ArrayList en LinkedList implementaties
Ik heb twee reeksen geimplementeerd: een ArrayList en een LinkedList. De ArrayList is een dynamische array, en de LinkedList is een lijst waarin Nodes naar elkaar wijzen met pointers.

### ArrayList
In mijn ArrayList-implementatie heb ik een generieke array gebruikt om de elementen op te slaan.
- **add(T value)**: voegt een element toe aan het einde van de lijst. Als de array vol is, wordt de capaciteit bijna verdubbeld (1.5x) met behulp van Arrays.copyOf().
- **get(int index)**: returns het element op de opgegeven index.
- **remove(int index)**: verwijdert het element op de opgegeven index en verschuift de resterende elementen naar links.
- **size()**: returns het aantal elementen in de lijst.
- **clear()**: maakt de lijst leeg door de size op 0 te zetten.
- **ensureCapacity()**: controleert of de array genoeg capaciteit heeft en vergroot deze indien nodig.
- **indexOf(T value)**: returns de index van het eerste voorkomen van de opgegeven waarde, of -1 als de waarde niet wordt gevonden.
- **contains(T value)**: controleert of de lijst de opgegeven waarde bevat.
- **rangeCheck(int index)**: controleert of de opgegeven index binnen de geldige grenzen ligt.

### LinkedList
In mijn LinkedList-implementatie heb ik een Node-klasse gemaakt die een generiek type waarde bevat en een pointer naar de volgende Node in de lijst.
- **add(T value)**: roept intern add(size, value) aan, wat betekent dat het element aan het einde komt van de lijst.
- **add(int index, T element)**: voegt een element toe op de opgegeven index:
  - Drie gevallen:
    index == 0 -> toevoegen aan het begin (head).
    index == size -> toevoegen aan het einde (tail).
    anders -> door de lijst heen lopen tot de juiste plek en tussen twee nodes insteken.
- **get(int index)**: returns het element op de opgegeven index door de lijst te doorlopen vanaf de head Node.
- **remove(int index)**: verwijdert het element op de opgegeven index door de pointers van de omliggende Nodes aan te passen.
- **size()**: returns het aantal elementen in de lijst.
- **clear()**: maakt de lijst leeg door de head op null te zetten en de size op 0 te zetten.
- **indexOf(T value)**: returns de index van het eerste voorkomen van de opgegeven waarde, of -1 als de waarde niet wordt gevonden.
- **contains(T value)**: controleert of de lijst de opgegeven waarde bevat.
- **isEmpty()**: controleert of de lijst leeg is door te kijken of de size 0 is.

### Complexiteit
De tijdscomplexiteit van de belangrijkste operaties in mijn ArrayList en LinkedList implementaties is als volgt:
**ArrayList**:
- add(T value): O(1) gemiddeld, O(n) in het slechtste geval bij resizing.
- get(int index): O(1) omdat je direct naar deze index kunt gaan.
- remove(int index): O(n) vanwege het verschuiven van elementen.
- size(): O(1) omdat de size wordt bijgehouden.
- clear(): O(1) omdat je alleen de size op 0 zet.
- ensureCapacity(): O(n) in het slechtste geval bij resizing.
- indexOf(T value): O(n) omdat het de lijst moet doorlopen.
- contains(T value): O(n) omdat het de lijst moet doorlopen.
- rangeCheck(int index): O(1) omdat het een simpele vergelijking is.

**LinkedList**:
- add(T value): O(1)
- add(int index, T element): O(n) in het slechtste geval. omdat je door de lijst moet lopen om de juiste plek te vinden.
- get(int index): O(n) omdat je door de lijst moet lopen om het element te vinden.
- remove(int index): O(n) omdat je door de lijst moet lopen om het element te vinden.
- size(): O(1) omdat de size wordt bijgehouden.
- clear(): O(1) omdat je alleen de head op null zet.
- indexOf(T value): O(n) omdat je door de lijst moet lopen.
- contains(T value): O(n) omdat je door de lijst moet lopen.
- isEmpty(): O(1) omdat het een simpele vergelijking is.

### Verbeterpunten
Hoewel mijn implementaties van de ArrayList en LinkedList functioneel zijn, zijn er enkele verbeterpunten die ik zou kunnen overwegen:
- In de ArrayList zou ik kunnen overwegen om de capaciteit met een andere hoeveelheid te vergroten in plaats van 1.5x. Use case gebonden
- In de LinkedList zou ik een doubly linked list kunnen implementeren om het verwijderen van elementen efficiënter te maken.
- Beide implementaties zouden extra functionaliteiten kunnen krijgen, zoals het reversen van de lijst of het sorteren van de elementen.


