# Graph Analyse
- Adjacency List: bewaart de graaf in een LinkedList van VertexEntry objecten.
  - Elke VertexEntry bevat een vertex en een lijst van edges die van die vertex vertrekken.
  - Ik doe dit omdat we elementen zo kunnen toevoegen/verwijderen zonder de elementen te moeten kopieren, zoals bij een array.
  - We gebruiken het om te itereren over alle vertices en hun edges, niet voor random access.

- Inner Class: VertexEntry
- . De blauwdruk van hoe een element in de Adjacency List eruitziet.
  - Attributen:
    - Een vertex
    - Lijst van edges die van die vertex vertrekken

Methoden:
- private VertexEntry findEntry(int vertex)
  - Loopt door alle nodes in de Adjacency List. Zo lang er entries zijn, controleert het of de vertex van de huidige entry gelijk is aan de gezochte vertex.
  - Als dat zo is, retourneert het die entry.
  - ObjecctEquals wordt gebruikt om de vertexwaarden te vergelijken.
  - Als het niet gevonden wordt, retourneert het null. Door naar AddVertex.

- private indexOfVertex(int vertex)
  - Loopt door de Adjacency List en vergelijkt elke entry's vertex met de gegeven vertex.
  - Als een match wordt gevonden, retourneert het de index van die entry.
  - Als geen match wordt gevonden, retourneert het -1.
  - 

- private getOrCreateEntry(int vertex) (anti-duplicate mechanisme)
      - Roept findEntry aan om te controleren of de vertex al bestaat in de graaf.
      - Als de vertex bestaat, retourneert het die entry.
      - Als de vertex niet bestaat, maakt het een nieuwe VertexEntry aan met de gegeven vertex en een lege lijst van edges.
      - Voegt de nieuwe entry toe aan de Adjacency List en retourneert die.

- public void addVertex(int vertex) - publieke methode om een vertex toe te voegen aan de graaf
    - roep getOrCreateEntry aan om de vertex toe te voegen als die nog niet bestaat.
    - Deze is publiek omdat we willen dat gebruikers van de graaf zelf vertices kunnen toevoegen en getOrCreateEntry is private omdat die alleen intern gebruikt wordt (encapsulation)

- public void addEdge(int fromVertex, int toVertex)
    - VertexEntry srcEntry = getOrCreateEntry(fromVertex); Haal of maak de source vertex entry
    - addVertex(targetVertex);Zorg dat de target vertex ook bestaat in de graaf
    - src.edges.add(new Edge(1.0, toVertex)); Voeg een nieuwe edge toe van fromVertex naar toVertex met gewicht
    - Dit voegt een edge toe aan de uitgaande van source vertex naar de target vertex.

- public void removeVertex(int fromVertex, int toVertex)
    - 1. Verwijder de vertex.
        Loop door de adjacencyList en verwijder de entry waarvan de entry gelijk is aan entry.vertex == vertex
    - 2. Verwijder alle edges die naar deze vertex wijzen.
        Loop door alle overgebleven nodes in de adjacencyList en verwijder alle edges die naar de verwijderde vertex wijzen. Dit gebeurt achterstevoren om index problemen te voorkomen.
  
- public double[] dijkstraFromIndex(int startIndex)
 - Check of startIndex geldig is
 - Initialiseer een afstandsarray met oneindige waarden
 - Zet de afstand van de startIndex naar zichzelf op 0.0
 - Kies de startIndex als huidige vertex
 - Roep de private recursive dijkstra functie aan met de huidige index
 - Retourneer de afstandsarray distance[].

- private void dijkstraRecursive(int currentIndex, double[] distance, boolean[] visited)
 - Base case: als currentIndex -1 is, return. Er is geen geldige vertex meer om te bezoeken.
 - Pak de huidige vertex entry uit de adjacencyList met currentIndex VertexEntry currentEntry = adjacencyList.get(currentIndex);
 - We staan nu op currentEntry.vertex, welke edges heeft die? Welke buren heeft die?
 - Loop door de uitgaande edges van currentEntry
   - Voor elke directe verbinding (edge) van currentEntry:
     - Bepaal de target vertex en het gewicht van de edge
     - Vind de index van de target vertex in de adjacencyList
     - Als de targetIndex geldig is en de target vertex nog niet bezocht is:
       -double candidate = distance[currentIndex] + edge.weight; Bereken de potentiële nieuwe afstand naar de target vertex via currentEntry
     -  If candidate < distance[targetIndex]: Als deze nieuwe afstand korter is dan de huidige bekende afstand:
         - Update de afstand naar de target vertex in de distance array
    - Markeer de huidige vertex als bezocht
    - Dan kijken we welke vertex we nu als volgende moeten bezoeken
    - int nextIndex = findClosestNotVisited(distance, visited); Vind de dichtstbijzijnde niet-bezochte vertex
    - Roep dijkstraRecursive aan met nextIndex, distance, visited om verder te gaan
 

- private int findClosestNotVisited(double[] distances, boolean[] visited)
    - int bestIndex = -1; // Houdt de index bij van de dichtstbijzijnde niet-bezochte vertex, begin met -1 omdat er nog geen is gevonden
    - double bestDistance = Double.POSITIVE_INFINITY; // Houdt de beste afstand bij
    - for (int i = 0; i < distances.length; i++) { // Loop door alle vertices
        - if (!visited[i] && distances[i] < bestDistance) { // Als de vertex niet bezocht is en de afstand kleiner is dan de beste afstand
            - bestDistance = distances[i]; // Update de beste afstand. Dit is het enige moment dat bestDistance wordt aangepast en is Dijkstra's kern.
            - bestIndex = i; // Update de index van de beste vertex
        -  return bestIndex; // Retourneer de index van de dichtstbijzijnde niet-bezochte vertex



# Edge Analyse
Blauwdruk van hoe een Edge eruitziet in de graaf
- Attributen:
  - double weight: het gewicht van de edge
  - int target: de target vertex van de edge
- Constructor:
  - Edge(double weight, int target): initialiseert de edge met het gegeven gewicht en target

De Edge weet niet waar hij vandadan komt, alleen waar hij naartoe gaat (target) en wat zijn gewicht is.
De Source vertex wordt bepaald door de VertexEntry
waarin de Edge zich bevindt.
Dit voorkomt redundantie en houdt de structuur van de graaf overzichtelijk.

# Vertex Analyse
Bevat alleen de data van de vertex zelf.

- function boolean equals(Object obj)
  - Controleert of het huidige object gelijk is aan een ander object.
  - Als ze hetzelfde object zijn, retourneert het true.
  - Als het andere object null is of niet van hetzelfde type, retourneert het false.


# Verbeterpunten
- Gebruik een Priority Queue voor Dijkstra's algoritme om de efficiëntie te verbeteren.
- Voeg foutafhandeling toe voor ongeldige inputs bij het toevoegen/verwijderen van vertices en edges.