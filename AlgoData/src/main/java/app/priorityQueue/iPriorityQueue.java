package app.priorityQueue;

public interface iPriorityQueue<T extends Comparable<T>> {


    /**
     * Voeg een nieuw element toe met prioriteit
     * @param value de waarde om op te slaan
     * @param priority  prioriteit (lager = meer prioriteit)
     */
    void enqueue(T value, int priority);

    /**
     * Haal het element met de hoogste prioriteit
     * @return de waarde (of null bij lege queue)
     */
    T dequeue();

    /**
     * Kijk naar het element met de hoogste prio zonder verwijderen
     * @return de waarde (of null bij lege queue)
     */
    T peek();

    /**
     * Controleer of een waarde in de queue zit
     */
    boolean contains(T value);

    /**
     * Verwijder het eerste voorkomen van de waarde uit de queue
     * @return true als het element gevonden en verwijderd is
     */
    boolean remove(T value);

    /**
     * Pas de prioriteit aan van een element.
     * @return true als de waarde gevonden/bijwerkt is
     */
    boolean updatePriority(T value, int newPriority);
}