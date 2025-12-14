## Priority Queue

In mijn implementatie van de Priority Queue maak ik gebruik van buckets op basis van prioriteit, opgeslagen in een linked-list.  
Elke bucket heeft een prioriteit en bevat een LinkedList met values. Dit zorgt voor FIFO/queue gedrag.: als meerdere elementen dezelfde prioriteit hebben, worden ze in de volgorde waarin ze zijn toegevoegd ook weer verwijderd.

- **Lagere priority-waarde = hogere prioriteit**
- De head wijst altijd naar de bucket met de hoogste prioriteit
- De buckets zijn onderling gekoppeld via een linked list

---
### Bucket (inner class)
- `int priority`
- `LinkedList<T> values`  FIFO binnen dezelfde prioriteit
- `Bucket<T> next`  verwijzing naar de volgende bucket

### PriorityQueue
- `Bucket<T> head` bucket met hoogste prioriteit
- `int size` totaal aantal elementen (n)

---

## Operaties

### Enqueue 
Bij het toevoegen van een element wordt de juiste bucket gezocht of aangemaakt.

- Als de queue leeg is wordt een nieuwe bucket aangemaakt.
- Als de nieuwe prioriteit hoger is dan de huidige head, wordt de nieuwe bucket vooraan geplaatst.
- Anders wordt door de buckets gelopen:
    - Bestaat de prioriteit al dan wordt het element achteraan toegevoegd (FIFO).
    - Bestaat de prioriteit nog niet dan wordt een nieuwe bucket op de juiste plek ingevoegd.

### Dequeue 
- Verwijdert het eerste element uit de bucket met de hoogste prioriteit.
- Als deze bucket leeg raakt, wordt deze uit de linked list verwijderd.

### Peek 
- Geeft het eerste element van de hoogste prioriteit terug zonder deze te verwijderen.

### Contains
- Loopt door alle buckets en hun lijsten om te controleren of een waarde voorkomt.

### Remove
- Zoekt de waarde in alle buckets en verwijdert het eerste voorkomen.
- Als de bijbehorende bucket leeg raakt, wordt deze opgeruimd.

### UpdatePriority
- Verwijdert eerst de waarde uit de queue.
- Voegt deze daarna opnieuw toe met de nieuwe prioriteit.

---

## Complexiteit

### Time complexity
- **Enqueue**  
  In het slechtste geval moet de hele bucket-lijst doorlopen. **O(n)**

- **Dequeue**  
  Verwijderen van het eerste element uit de head-bucket.**O(1)**

- **Peek**  
  Directe toegang tot het eerste element. **O(1)**

- **Contains**  
  In het slechtste geval wordt elk element gecontroleerd. **O(n)**

- **Remove**  
  In het slechtste geval wordt elk element gecontroleerd.  **O(n)**

- **UpdatePriority**  
  Bestaat uit `remove` + `enqueue` dus **O(n)**

### Space complexity
- **O(n)** voor het opslaan van alle elementen
Extra overhead voor buckets en linked list-structuur

---

## Verbeterpunten

- **Zoekoperaties: lineair**  
  Operaties zoals `contains`, `remove` en `updatePriority` zijn **O(n)**.  
  Dit kan sneller met een extra `HashMap`, maar dat maakt het wel complexer.

- **Comparable kan gewenst zijn in specifieke use cases**  
  `Comparable<T>` kan handig zijn wanneer elementen met dezelfde prioriteit onderling ook gesorteerd moeten worden,  
  bijvoorbeeld op naam, tijdstip of een andere eigenschap.

- **Veel verschillende prioriteiten**  
  Bij veel verschillende prioriteiten wordt `enqueue` trager.  
  In dat geval kan een Map van prioriteiten naar buckets de prestaties verbeteren. Zie voorbeeldtest enqueueManyDifferentPrioritiesCanBeSlow()
Bij het invoegen van veel elementen met dezelfde prioriteit duurt enqueue
  gemiddeld ongeveer 13 ms. Wanneer elk element een unieke prioriteit krijgt,
  loopt deze tijd op tot gemiddeld ongeveer 13868 ms (gemeten over 5 runs).
Dit verschil ontstaat omdat de implementatie bij elke enqueue lineair door
de bestaande buckets moet lopen om de juiste plek te vinden.
