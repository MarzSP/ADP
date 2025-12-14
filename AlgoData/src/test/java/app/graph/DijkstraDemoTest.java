package app.graph;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraDemoTest {

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
        assertTrue(output.contains("Startpersoon:"), "Demo should print a start person");
        assertTrue(output.contains("Execution time"), "Demo should print execution time");
    }
}
