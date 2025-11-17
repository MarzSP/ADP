package app.lists;

import java.util.Arrays;
/**
 * ArrayList implementatie van iList<T> interface
 * @param <T> - Generic type
 */
public class ArrayList<T> implements iList<T> {
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[10];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size++] = value;
    }

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

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elements[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public T set(int index, T element) {
        rangeCheck(index);
        T old = (T) elements[index];
        elements[index] = element;
        return old;
    }


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

    /**
     * Geeft de grootte van de lijst terug
     * @return
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
     * @param value
     * @return
     */
    @Override
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    /**
     * Controleerd of de lijst leeg is
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Vind de index van de eerste keer dat het opgegeven element in de lijst is
     * @param element
     * @return
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
     * Zorgd er voor dat de interne array genoeg capaciteit heeft
     * @param minCapacity
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity - elements.length > 0) {
            int oldCapacity = elements.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - minCapacity < 0)
                newCapacity = minCapacity;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Controlleerd of de index binnen de grenzen van de lijst valt
     * @param index
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
