package collections;

import java.util.Iterator;

abstract class ArrayBackedCollection<E> implements Iterable<E> {

    public static final int DEFAULT_INITIAL_CAPACITY = 16;

    private Object[] elements;

    private int nextUp = 0;

    final void expandCapacity() {
        int largerSize = 3 * this.elements.length / 2;
        Object[] largerArray = new Object[largerSize];
        System.arraycopy(this.elements, 0, largerArray, 0,
                this.elements.length);
        this.elements = largerArray;
    }

    // TODO: Write tests for this
    public boolean add(E element) {
        return false;
    }

    // TODO: Write tests for this
    public boolean contains(E element) {
        return false;// this.indexOf(element) > -1;
    }

    // TODO: Write tests for this
    public boolean remove(E element) {
        return false;
    }

    // TODO: Write tests for this
    public int size() {
        return Integer.MIN_VALUE;
    }

    // TODO: Write tests for this
    public boolean isEmpty() {
        return false;
    }

    // TODO: Write tests for this
    public void clear() {
        //
    }

    // TODO: Write tests for this
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(this.elements, 0);
    }

    public ArrayBackedCollection(int initialCapacity) {
//        if (initialCapacity < 0) {
//            String excMsg = "Initial capacity " + initialCapacity
//                    + " is invalid, needs to be greater than 0";
//            throw new IllegalArgumentException(excMsg);
//        }
        this.elements = new Object[initialCapacity];
    }

    private class ArrayBackedIterator<E> implements Iterator<E> {

        // TODO: Write tests for this
        @Override
        public boolean hasNext() {
            return false;
        }

        // TODO: Write tests for this
        @Override
        public E next() {
            return null;
        }

    }

}
