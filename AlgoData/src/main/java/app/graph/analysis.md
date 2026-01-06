## Graph
De graph is geimplementeerd als een gewogen adjacency list.
Vertices worden opgeslagen in een Map, waarbij elke vertex een lijst van uitgaande edges met gewichten heeft

Time complexity:

addVertex: O(1) in de hashmap

addEdge: Best case O(1), Worst case O(E) omdat er gecontroleerd wordt of de edge al bestaat.

getNeighbours: Best case O(1), Worst case O(E) als er veel edges zijn.

Space complexity: O(Vertex + Edge)

## Dijkstra's Algorithm
Lineaire implemenatie - directed

Dijkstra’s algoritme berekent de kortste afstanden vanaf een start vertex.
De dichtstbijzijnde onbezochte vertex wordt lineair gezocht.

### Time complexity

Lineair zoeken: O(N) per iteratie dus N iteraties is O(V²)

Kijken of je via de huidige vertex een kortere afstand naar een buur kunt vinden: O(E). Waar E aantal edges is.

Totaal: O(V² + E)

### Space complexity:

Afstanden + visited set: O(V)

### Execution time

Execution time is gemeten met System.nanoTime() rond de Dijkstra-aanroep.
De looptijd groeit snel bij grotere graphs door de O(N²) zoekstrategie.

## Verbeterpunten
- Bottleneck is momenteel linear zoeken naar dichtbijzijnde vertex. Dit zou ook een min-heap of priority queue kunnen zijn voor betere prestaties.
Een min-heap implementatie zou O((N + E) log N) tijdcomplexiteit opleveren in plaats van O(N² + E).
Een min-heap is een datastructuur die altijd snel het kleinste element kan vinden en verwijderen.

