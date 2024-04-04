package collections.mutable;

import java.util.Iterator;

public class HashSet<E> implements Iterable<E> {

    private boolean empty = true;

    private int count = 0;

    public boolean isEmpty() {
        return this.empty;
    }

    public int size() {
        return this.count;
    }

    public boolean add(E element) {
        this.empty = false;
        this.count++;
        return true;
    }

    // TODO: Write tests for this
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

    public HashSet() {
        //
    }

}
