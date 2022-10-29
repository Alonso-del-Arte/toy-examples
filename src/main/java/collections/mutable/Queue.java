package collections.mutable;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class Queue<E> implements Iterable<E> {

    private Object[] elements;

    // TODO: Write tests for this
    public boolean add(E element) {
        return false;
    }

    // TODO: Write tests for this
    public boolean contains(E element) {
        return false;
    }

    // TODO: Write tests for this
    // This one's supposed to retrieve but not remove the element at the front
    // of the queue. Should return null only if the queue is empty.
    public E peek() {
        return null;
    }

    // TODO: Write tests for this
    // This one's supposed to retrieve _and_ remove the element at the front of
    // the queue. Should return null only if the queue is empty.
    public E poll() {
        return null;
    }

    // TODO: Write tests for this
    public boolean remove(E element) {
        return false;
    }

    // TODO: Write tests for this
    public boolean isEmpty() {
        return false;
    }

    // TODO: Write tests for this
    public boolean isFull() {
        return true;
    }

    public void clear() {
        // TODO: Write tests for this
    }

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

    public Queue(int capacity) {
        if (capacity < 0) {
            String excMsg = "Capacity " + capacity
                    + " not valid, should be positive";
            throw new IllegalArgumentException(excMsg);
        }
        this.elements = new Object[capacity];
    }

}
