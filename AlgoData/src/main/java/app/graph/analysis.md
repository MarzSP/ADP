# Graph Analyse
- Adjacency List: bewaart de graaf als een lijst van Nodes.

- Inner Class: AdjacencyNode. De blauwdruk van hoe een element in de Adjacency List eruitziet.
  - Attributen:
    - Een vertex
    - Lijst van edges die van die vertex vertrekken

Methoden:
- private AdjacencyNode findNode(int vertex)
  - Loopt door alle nodes in de Adjacency List. Zo lang er nodes zijn, controleert het of de vertex van de huidige node gelijk is aan de gezochte vertex.
  - Als dat zo is, retourneert het die node.
  - ObjecctEquals wordt gebruikt om de vertexwaarden te vergelijken.
  - Als het niet gevonden wordt, retourneert het null. Door naar AddVertex.


- public void addVertex(int vertex)
  - Controleert of de vertex al bestaat door findNode aan te roepen.
  - Als de vertex niet bestaat, maakt het een nieuwe AdjacencyNode aan met de gegeven vertex en een lege lijst van edges.
  - Voegt de nieuwe node toe achteraan aan de Adjacency List.


- public void addEdge(int fromVertex, int toVertex)
- Wanneer je een edge toevoegt, controleert het eerst of beide vertices al bestaan in de graaf.
  - Als een van de vertices niet bestaat, roept het addVertex aan om die toe te voegen.
  - Vervolgens vindt het de node voor source en voegt target toe aan de lijst van edges van die node.
  - Dan voeg je een New Edge toe met gewicht en target


- public double[] dijkstra(int startVertex)
  - int n = adjacencyList.size(); // Aantal vertices in de graaf
  - double[] distances = new double[n]; // Array om de kortste afstanden op te slaan
  - boolean[] visited = new boolean[n]; // Array om bij te houden welke vertices bezocht zijn
  - Arrays.fill(distance, double Positive.infinity) Initialiseer alle afstanden naar oneindig, behalve de startVertex die is 0.0
  - int start = findClosestNotVisited(distance, visited); // Vind de dichtstbijzijnde niet-bezochte vertex
  - dijkstraRecursive(start, distance, visited); // Roep de recursieve Dijkstra-methode aan
  - return distances; // Retourneer de array met kortste afstanden


- private void dijkstraRecursive(int currentVertex, double[] distances, boolean[] visited)
    - Base case: Als currentVertex -1 is, betekent dit dat er geen niet-bezochte vertices meer zijn, dus retourneer.
    -  AdjacencyNode currentNode = adjacencyList.get(currentVertex); - Haal de huidige node op uit de Adjacency List
    -  Int (e = 0 ; e < currentNode.edges.size(); e++) - Bekijk alle edges vanaf de huidige node
       - Edge edge = currentNode.edges.get(e); - Haal de huidige edge op
       - int neighborVertex = edge.target; - pak de target vertex van de edge
       - Nu gaan we in de adjacencyList zoeken naar de index van de neighborVertex
       - Deze slaan we op in neighborIndex
       -  Nu berekenen we de nieuwe afstand naar de neighborVertex via currentVertex
       - double candidate = distance[currentIndex] + edge.getWeight(); - Als deze nieuwe afstand kleiner is dan de huidige opgeslagen afstand naar neighborVertex, updaten we die afstand
       - distances[neighborIndex] = candidate;
       -    if (candidate < distance[neighborIndex]) {
            distance[neighborIndex] = candidate;
            } - Markeer currentVertex als bezocht door visited[currentVertex] op true te zetten
       - visited[currentVertex] = true;
       - int nextVertex = findClosestNotVisited(distances, visited); - Vind de volgende dichtstbijzijnde niet-bezochte vertex
       - dijkstraRecursive(nextVertex, distances, visited); - Roep de methode recurssief aan met de volgende vertex


- private int findClosestNotVisited(double[] distances, boolean[] visited)
    - int bestIndex = -1; // Houdt de index bij van de dichtstbijzijnde niet-bezochte vertex, begin met -1 omdat er nog geen is gevonden
    - double bestDistance = Double.POSITIVE_INFINITY; // Houdt de beste afstand bij
    - for (int i = 0; i < distances.length; i++) { // Loop door alle vertices
        - if (!visited[i] && distances[i] < bestDistance) { // Als de vertex niet bezocht is en de afstand kleiner is dan de beste afstand
            - bestDistance = distances[i]; // Update de beste afstand. Dit is het enige moment dat bestDistance wordt aangepast en is Dijkstra's kern.
            - bestIndex = i; // Update de index van de beste vertex
        -  return bestIndex; // Retourneer de index van de dichtstbijzijnde niet-bezochte vertex


- public void removeVertex(int vertex)
  - 1 loop door de adjacencyList om de node met de gegeven vertex te vinden en te verwijderen.
  - 2 loop door alle overgebleven nodes in de adjacencyList en verwijder alle edges die naar de verwijderde vertex wijzen.


# Edge Analyse
Blauwdruk van hoe een Edge eruitziet in de graaf
- Attributen:
  - double weight: het gewicht van de edge
  - int target: de target vertex van de edge
- Constructor:
  - Edge(double weight, int target): initialiseert de edge met het gegeven gewicht en target

De Edge weet niet waar hij vandadan komt, alleen waar hij naartoe gaat (target) en wat zijn gewicht is.
De Source vertex wordt bepaald door de AdjacencyNode waarin de Edge zich bevindt.
Dit voorkomt redundantie en houdt de structuur van de graaf overzichtelijk.

# Vertex Analyse
Bevat alleen de data van de vertex zelf.

- function boolean equals(Object obj)
  - Controleert of het huidige object gelijk is aan een ander object.
  - Als ze hetzelfde object zijn, retourneert het true.
  - Als het andere object null is of niet van hetzelfde type, retourneert het false.


