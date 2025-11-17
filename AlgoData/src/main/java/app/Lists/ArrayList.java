package app.Lists;

import java.util.Arrays;

/**
 * ArrayList implementation of iList interface.
 * @param <T>
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
        elements[--size] = null; // clear to let GC do its work
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

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

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - elements.length > 0) {
            int oldCapacity = elements.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1); // 1.5x
            if (newCapacity - minCapacity < 0)
                newCapacity = minCapacity;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
