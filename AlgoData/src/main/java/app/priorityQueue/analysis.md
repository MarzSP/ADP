## Priority Queue
In mijn implementatie van de Priority Queue heb ik gebruik gemaakt van een binaire heap.
Een binaire heap betekend dat elke parent node maximaal twee child nodes heeft.
Daarnaast is een binaire heap een complete boom, wat betekent dat alle niveaus van de boom
Een boom betekend dat elke node een waarde heeft en dat er een hiërarchie is tussen de nodes.

In deze implementatie heb ik gekozen voor een min-heap, wat betekent dat de parent node altijd
kleiner is dan of gelijk is aan zijn child nodes. Dit maakt het gemakkelijk om het element met de
hoogste prioriteit (de kleinste waarde) snel te vinden en te verwijderen. Deze wordt opgeslagen in een generieke array (T[] heap).

### Operaties
De belangrijkste operaties die ik heb geïmplementeerd in de Priority Queue zijn:
- **Enqueue** (invoegen van een element): een element wordt achteraan de array toegevoegd.
- **Dequeue** (verwijderen van het element met de hoogste prioriteit): Ik verwijder het element aan het begin van de array (de root van de heap, index 0) en vervang deze door het laatste element in de array. Vervolgens herstructureer ik de heap om de heap-eigenschap te houden met de heapifyDown method.
- **Peek** (bekijken van het element met de hoogste prioriteit zonder het te verwijderen) met heap[0].
- **IsEmpty** (controleren of de queue leeg is) met size == 0.
- **Size** (teruggeven van het aantal elementen in de queue) met size.
- **Clear** (leegmaken van de queue) met size = 0.
- **ensureCapacity** (controleren of de array genoeg capaciteit heeft en als nodig deze te vergroten) met Arrays.copyOf().
- **heapifyUp** (herstructureren van de heap na het toevoegen van een element) door het nieuwe element omhoog te verplaatsen totdat de heap-eigenschap is hersteld.
- **heapifyDown** (herstructureren van de heap na het verwijderen van het element met de hoogste prioriteit) door het nieuwe root-element omlaag te verplaatsen totdat de heap-eigenschap is hersteld.
- **swap** (wisselen van twee elementen in de array) door gebruik te maken van een tijdelijke variabele T temp.

### Complexiteit
De tijdscomplexiteit van de belangrijkste operaties in mijn Priority Queue is als volgt:
- **Enqueue**: O(log n) in het slechtste geval, omdat het nieuwe element mogelijk helemaal naar boven moet worden verplaatst.
- **Dequeue**: O(log n) in het slechtste geval, omdat het nieuwe root element mogelijk helemaal naar beneden moet worden verplaatst.
- **Peek**: O(1), omdat het element met de hoogste prioriteit direct toegankelijk is.
- **IsEmpty**: O(1), omdat het alleen een vergelijking is.
- **Size**: O(1), omdat het alleen een variabele returned.
- **Clear**: O(1), omdat het alleen de size variable reset.
- **ensureCapacity**: O(n) in het slechtste geval, wanneer de array groter moet worden gemaakt.
- **heapifyUp**: O(log n) in het slechtste geval, omdat het element mogelijk helemaal naar boven moet worden verplaatst.
- **heapifyDown**: O(log n) in het slechtste geval, omdat het element mogelijk helemaal naar beneden moet worden verplaatst.
- **swap**: O(1), omdat het alleen drie assignments zijn.

## Verbeterpunten
Hoewel mijn implementatie van de Priority Queue functioneel is, zijn er enkele verbeterpunten die ik zou kunnen overwegen:
- ensureCapacity() zou ik ook 1.5x kunnen vergroten in plaats van 2x om geheugen efficiënter te gebruiken. Scheelt overhead bij grote hoeveelheden data.
- Minder compareTo calls aanroep door deze op te slaan in een variabele in de methods heapifyUp en heapifyDown. (housekeeping variables)



