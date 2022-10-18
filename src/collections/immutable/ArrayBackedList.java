package collections.immutable;

import java.util.Arrays;
import java.util.Iterator;

// TODO: Enforce immutability
public class ArrayBackedList<E> implements Iterable<E> {

    private final Object[] elements;

    @SuppressWarnings("unchecked")
    public ArrayBackedList<E> add(E element) {
        Object[] elems = new Object[this.elements.length + 1];
        System.arraycopy(this.elements, 0, elems, 0, this.elements.length);
        elems[this.elements.length] = element;
        return new ArrayBackedList<>((E[]) elems);
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
        return this.elements.length;
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
        if (this.elements.length != other.elements.length) {
            return false;
        }
        return Arrays.equals(this.elements, other.elements);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Object element : this.elements) {
            hash = (hash << 1) + element.hashCode();
        }
        return hash;
    }

    public ArrayBackedList() {
        this.elements = new Object[]{};
    }

    // TODO: Determine if this ought to have SafeVarargs annotation
    public ArrayBackedList(E... elems) {
        for (E element : elems) {
            if (element == null) {
                throw new NullPointerException("Element should not be null");
            }
        }
        this.elements = new Object[elems.length];
        System.arraycopy(elems, 0, this.elements, 0, elems.length);
    }

}
