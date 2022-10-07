package collections.immutable;

import java.util.Iterator;

public class ArrayBackedList<E> implements Iterable<E> {

    int count = 0;

    // TODO: Write test for this
    public ArrayBackedList<E> add(E element) {
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

    // TODO: Write test for this
    public boolean contains(E element) {
        return false;
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

    public ArrayBackedList() {
        // TODO: Write tests for this
    }

    // TODO: Determine if this needs SafeVarargs annotation
    public ArrayBackedList(E... elements) {
        // TODO: Write tests for this
    }

}
