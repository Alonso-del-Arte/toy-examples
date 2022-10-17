package collections.immutable;

import java.util.Arrays;
import java.util.Iterator;

// TODO: Enforce immutability
public class ArrayBackedList<E> implements Iterable<E> {

    private int count = 0;

    private final Object[] elements = new Object[256];

    // TODO: Write test for this
    public ArrayBackedList<E> add(E element) {
        this.elements[this.count] = element;
        this.count++;
        return this;
    }

    // TODO: Write test for this
    public ArrayBackedList<E> add(int index, E element) {
        return this;
    }

    // TODO: Write test for this
    public ArrayBackedList<E> remove(int index) {
        return this;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) this.elements[index];
    }

    // TODO: Write test for this
    public int indexOf(E element) {
        return Integer.MAX_VALUE;
    }

    public boolean contains(E element) {
        boolean found = false;
        int index = 0;
        while (!found && index < this.elements.length) {
            found = element.equals(this.elements[index]);
            index++;
        }
        return found;
    }

    public int size() {
        return this.count;
    }

    // TODO: Write test for this
    ArrayBackedList<E> clear() {
        return this;
    }

    // TODO: Write test for this
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }

        };
    }

    // TODO: Override toString()

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        ArrayBackedList<?> other = (ArrayBackedList<?>) obj;
        if (this.count != other.count) {
            return false;
        }
        return Arrays.equals(this.elements, other.elements);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < this.count; i++) {
            hash = (hash << 1) + this.elements[i].hashCode();
        }
        return hash;
    }

    public ArrayBackedList() {
        // TODO: Write tests for this
    }

    // TODO: Determine if this ought to have SafeVarargs annotation
    public ArrayBackedList(E... elems) {
        for (E element : elems) {
            if (element == null) {
                throw new NullPointerException("Element should not be null");
            }
        }
        for (int i = 0; i < elems.length; i++) {
            this.elements[i] = elems[i];
        }
        // TODO: Write tests for this
    }

}
