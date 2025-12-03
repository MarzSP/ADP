package app.graph;

import app.Data.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class dat een gewogen Graph maakt met adjacency list. Maakt gebruik van Person nodes.
 * Het is een map van maps, waarbij de key van de buitenste map een node is,
 * en de value een map is van de neighbours (keys) en hun gewichten (values)
 * Time complexity: O(1) voor toevoegen en opvragen van buren
 * Space complexity: O(V + E) waarbij V het aantal knopen is en E het aantal randen
 */
public class Graph {
    public Map<Person, Map<Person, Integer>> adjacency = new HashMap<>();

    public Graph(Person[] nodes) {
        for (Person node : nodes) {
            adjacency.put(node, new HashMap<>());
        }
    }

    public void addEdge(Person from, Person to, int weight) {
        if(!adjacency.containsKey(from) || !adjacency.containsKey(to)) {
            return;
        }
        adjacency.get(from).put(to, weight);
        adjacency.get(to).put(from, weight);
    }

    public Map<Person, Integer > getNeighbours(Person node) {
        return adjacency.get(node);
    }

    public Set<Person> getNodes() {
        return adjacency.keySet();
    }

}
