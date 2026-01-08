package app.graph;

import app.Data.Dataset;
import app.Data.Person;

import java.util.Map;

/**
 * Demo class om Dijkstra's algoritme te testen met Person objects in een graph
*/
public class DijkstraDemo {

    public static void main(String[] args) {

        // Maak graph
        Graph<Person> graph = buildGraph();

        // Kies start
        Person start = graph.getVertices().iterator().next();

        System.out.println("Startpersoon: " + start);

        //execution time
        long startTime = System.nanoTime();
        Map<Person, Integer> distances = Dijkstra.calculateShortestPaths(graph, start);
        long endTime = System.nanoTime();

        //Print resultaat
        for (Map.Entry<Person, Integer> entry : distances.entrySet()) {
            System.out.println( "Afstand van " + start + " naar " + entry.getKey() + " = " + entry.getValue());
        }
        System.out.println("Execution time (ns): " + (endTime - startTime));
    }

    /**
     * Bouwt een simple graph op basis van class Dataset
     * Personen worden lineair verbonden met gewichten gebaseerd op leeftijdsverschil(random)
     */
    private static Graph<Person> buildGraph() {
        Graph<Person> graph = new Graph<>();
        Person[] people = Dataset.generatePeople();

        //Voer persons toe als vertices
        for (Person p : people) {
            graph.addVertex(p);
        }

        // Connect elke persoon met de volgende (undirectional)
        for (int i = 0; i < people.length - 1; i++) {
            Person a = people[i];
            Person b = people[i + 1];

            // Gewicht gebaseerd op leeftijdsverschil
            int weight = Math.abs(a.getAge() - b.getAge()) + 1;

            graph.addEdge(a, b, weight);
            graph.addEdge(b, a, weight);
        }

        return graph;
    }
}

/** Voorbeeldoutput:
 * Output voorbeeld:
 * Startpersoon: Franklin (69)
 * Afstand van Franklin (69) naar Franklin (69) = 0
 * Afstand van Franklin (69) naar Lovelace (23) = 614
 * Afstand van Franklin (69) naar Minsky (66) = 192
 * Afstand van Franklin (69) naar McCarthy (77) = 559
 * Afstand van Franklin (69) naar Cerf (72) = 199
 * Afstand van Franklin (69) naar Thompson (63) = 260
 * Afstand van Franklin (69) naar Hinton (65) = 45
 * Afstand van Franklin (69) naar Diffie (79) = 60
 * Afstand van Franklin (69) naar Hawking (45) = 114
 * Afstand van Franklin (69) naar Curie (48) = 103
 * Afstand van Franklin (69) naar Bohr (47) = 23
 * Afstand van Franklin (69) naar Rivest (80) = 166
 * Afstand van Franklin (69) naar Torvalds (72) = 471
 * Afstand van Franklin (69) naar Brin (57) = 127
 * Afstand van Franklin (69) naar Dijkstra (34) = 394
 * Afstand van Franklin (69) naar Feynman (26) = 44
 * Afstand van Franklin (69) naar Hopper (35) = 369
 * Afstand van Franklin (69) naar Turing (32) = 512
 * Afstand van Franklin (69) naar Hyponnen (71) = 213
 * Afstand van Franklin (69) naar van Rossum (80) = 441
 * Afstand van Franklin (69) naar UncleBob (77) = 669
 * Afstand van Franklin (69) naar Berners-Lee (61) = 342
 * Afstand van Franklin (69) naar Ritchie (62) = 460
 * Afstand van Franklin (69) naar Gosling (64) = 545
 * Afstand van Franklin (69) naar Beck (32) = 293
 * Afstand van Franklin (69) naar Merkle (30) = 110
 * Afstand van Franklin (69) naar Galilei (53) = 72
 * Afstand van Franklin (69) naar Knuth (46) = 381
 * Afstand van Franklin (69) naar Cox (54) = 270
 * Afstand van Franklin (69) naar Newton (36) = 90
 * Afstand van Franklin (69) naar Kay (52) = 332
 * Afstand van Franklin (69) naar Zuse (38) = 234
 * Afstand van Franklin (69) naar Adleman (53) = 194
 * Afstand van Franklin (69) naar Jobs (41) = 303
 * Afstand van Franklin (69) naar Gates (45) = 170
 * Afstand van Franklin (69) naar Shamir (28) = 113
 * Afstand van Franklin (69) naar Heisenberg (66) = 43
 * Afstand van Franklin (69) naar Allen (31) = 155
 * Afstand van Franklin (69) naar Wozniak (60) = 323
 * Afstand van Franklin (69) naar Page (57) = 128
 * Afstand van Franklin (69) naar Einstein (51) = 107
 * Execution time (ns): 1391900
 */

