## Graph
De graph is geimplementeerd als een gewogen adjacency list.
Vertices worden opgeslagen in een Map, waarbij elke vertex een lijst van uitgaande edges met gewichten heeft

Time complexity:

addVertex: O(1)

addEdge: O(1)

getNeighbours: O(1)

Space complexity: O(Vertex + Edge)

## Dijkstra's Algorithm
Lineaire implemenatie - directed

Dijkstra’s algoritme berekent de kortste afstanden vanaf een start vertex.
De dichtstbijzijnde onbezochte vertex wordt lineair gezocht.

### Time complexity

Lineair zoeken: O(N) per iteratie dus N iteraties is O(N²)

Kijken of je via de huidige vertex een kortere afstand naar een buur kunt vinden: O(E). Waar E aantal edges is.

Totaal: O(N² + E)

### Space complexity:

Afstanden + visited set: O(N)

### Execution time

Execution time is gemeten met System.nanoTime() rond de Dijkstra-aanroep.
De looptijd groeit snel bij grotere graphs door de O(N²) zoekstrategie.