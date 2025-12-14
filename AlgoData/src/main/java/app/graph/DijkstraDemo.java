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

        //Kies start
        Person start = graph.getVertices().iterator().next();

        System.out.println("Startpersoon: " + start);

        //execution time
        long startTime = System.nanoTime();
        Map<Person, Integer> distances =
                Dijkstra.calculateShortestPaths(graph, start);
        long endTime = System.nanoTime();

        // 4. Print resultaat
        for (Map.Entry<Person, Integer> entry : distances.entrySet()) {
            System.out.println(
                    "Afstand van " + start + " naar " + entry.getKey() + " = " + entry.getValue()
            );
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

        //Add persons als vertices
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
