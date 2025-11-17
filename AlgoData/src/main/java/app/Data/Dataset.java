package app.Data;

import java.util.Random;

public class Dataset {
    public static Integer[] generateIntData(int size) {
        Random r = new Random();
        Integer[] data = new Integer[size];

        for (int i = 0; i < size; i++) {
            data[i] = r.nextInt(100000);
        }
        return data;
    }

    public static String[] getNames() {
        return new String[]{
                "UncleBob", "Lovelace", "McCarthy", "Gosling", "Turing",
                "Torvalds", "Ritchie", "van Rossum", "Dijsktra", "Knuth",
                "Hopper", "Berners-Lee", "Kay", "Wozniak", "Jobs", "Beck",
                "Cox", "Thompson", "Zuse", "Cerf", "Minsky", "Gates", "Allen",
                "Page", "Brin", "Hawking", "Einstein", "Curie", "Newton",
                "Galilei", "Feynman", "Franklin", "Bohr", "Heisenberg",
                "Hinton", "Diffie", "Merkle", "Shamir", "Rivest", "Adleman"
        };
    }

    public static Person[] generatePeople() {
        Random r = new Random();
        String[] names = getNames();
        Person[] people = new Person[names.length];

        for (int i = 0; i < names.length; i++) {
            int age = 20 + r.nextInt(61); // random age 20–80
            people[i] = new Person(names[i], age);
        }

        return people;
    }
}


