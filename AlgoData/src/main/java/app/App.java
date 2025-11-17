package app;

import app.Data.Dataset;
import app.Data.Person;

public class App {
    public static void main(String[] args) {
        Person[] ps = Dataset.generatePeople();
        for (Person p : ps) {
            System.out.println(p.getName() + " - " + p.getAge());
        }

        System.out.println("Hello from AlgoData (package app)");
    }
}

