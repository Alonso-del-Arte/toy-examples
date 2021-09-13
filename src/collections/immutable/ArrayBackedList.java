package collections.immutable;

import java.util.Iterator;

public class ArrayBackedList<E> implements Iterable<E> {

    private static final int DEFAULT_CAPACITY_INITIAL = 16;

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

}
