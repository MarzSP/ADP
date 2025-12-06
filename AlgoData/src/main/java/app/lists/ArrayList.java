package app.lists;

import java.util.Arrays;
/**
 * ArrayList implementatie van iList<T> interface
 * SuppressWarnings("unchecked") = Java kan niet controleren of de cast veilig is bij runtime (Type erasure)
 * @param <T> - Generic type
 */
public class ArrayList<T> implements iList<T> {
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[10];
        this.size = 0;
    }

    /**
     * Check of er ruimte is en voeg een element toe aan het einde van de lijst
     * TC: best case O(1), worst case O(n)
     * @param value - waarde to add
     */
    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size++] = value;
    }

    /**
     * Voegt een element toe op de die index plek
     * TC: best case O(1), worst case O(n)
     * @param index plek waarop element toegevoegd moet worden
     * @param element toe te voegen element
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity(size + 1);
        int numMoved = size - index;
        if (numMoved > 0) {
            System.arraycopy(elements, index, elements, index + 1, numMoved);
        }
        elements[index] = element;
        size++;
    }

    /**
     * Geeft het element met die index terug
     * TC: O(1)
     * @param index index van dat element
     * @return element op die index
     */
    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elements[index];
    }

    /**
     * Zet het element op de opgegeven index en return het originele element
     * TC: O(1)
     * @param index plaats van het element
     * @param element nieuwe element
     * @return originele element
     */
    @SuppressWarnings("unchecked")
    @Override
    public T set(int index, T element) {
        rangeCheck(index);
        T old = (T) elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * Verwijderd het element op de opgegeven index en return deze
     * TC: best case O(1), worst case O(n)
     * @param index plaats van het te verwijderen element
     * @return verwijderd element
     */
    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        rangeCheck(index);
        T removed = (T) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return removed;
    }
//TODO : analyse from here and update anaysis.md
    /**
     * Geeft de grootte van de lijst terug
     * @return grootte
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Leegt de lijst
     */
    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    /**
     * Controleerd of de lijst het opgegeven element bevat
     * @param value te zoeken waarde
     * @return boolean true als gevonden
     */
    @Override
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    /**
     * Controleerd of de lijst leeg is
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Vind de index van de eerste keer dat het opgegeven element in de lijst is
     * @param element te zoeken element
     * @return index van het element (-1 als not found)
     */
    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null) {
                if (element == null) return i;
            } else if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checked of de interne array genoeg capaciteit heeft
     * TC Best case O(1), worst case O(n)
     * @param minCapacity minimale capaciteit van de array
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity - elements.length > 0) {
            int oldCapacity = elements.length;
            int newCapacity = oldCapacity + (oldCapacity / 2);
            if (newCapacity - minCapacity < 0)
                newCapacity = minCapacity;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Controlleerd of de index binnen de grenzen van de lijst valt
     * @param index te controleren index
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
