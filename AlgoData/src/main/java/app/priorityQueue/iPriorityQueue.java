package app.priorityQueue;

public interface iPriorityQueue<T extends Comparable<T>> {

    /**
     * Voeg een nieuw element toe aan de queue met een prio
     * Lagere waarde = hogere prioriteit
     * @param value de waarde om op te slaan
     * @param priority de prioriteit
     */
    void enqueue(T value, int priority);

    /**
     * Haalt en verwijdert het element met de hoogste prioriteit.
     * Bij gelijke prioriteit wordt FIFO-volgorde gebruikt.
     *
     * @return de waarde, of null als de queue leeg is
     */
    T dequeue();

    /**
     * Geeft het element met de hoogste prioriteit terug
     * zonder het te verwijderen.
     *
     * @return de waarde, of null als de queue leeg is
     */
    T peek();

    /**
     * Controleert of een waarde ergens in de queue voorkomt.
     *
     * @param value de gezochte waarde
     * @return true als de waarde aanwezig is
     */
    boolean contains(T value);

    /**
     * Verwijdert het eerste voorkomen van de waarde uit de queue.
     *
     * @param value de te verwijderen waarde
     * @return true als het element gevonden en verwijderd is
     */
    boolean remove(T value);

    /**
     * Past de prioriteit aan van een bestaande waarde.
     * De FIFO-volgorde binnen de nieuwe prioriteit wordt gerespecteerd.
     *
     * @param value de waarde waarvan de prioriteit wordt aangepast
     * @param newPriority de nieuwe prioriteit
     * @return true als de waarde gevonden en bijgewerkt is
     */
    boolean updatePriority(T value, int newPriority);
}
