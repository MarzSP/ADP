package app.graph;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraDemoTest {

    // De class DijkstraDemo laat al zien dat Dijkstra werkt met Person objects.
    // Hier testen we alleen of de main methode draait
    @Test
    void mainRunsPrintOutput() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        try {
            DijkstraDemo.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }

        String output = buffer.toString();
        assertTrue(output.contains("Startpersoon:"), "Demo should print a start persoon");
        assertTrue(output.contains("Execution time"), "Demo should print execution time");
    }
}
