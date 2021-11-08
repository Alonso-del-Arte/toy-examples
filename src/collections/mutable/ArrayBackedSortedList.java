package collections.mutable;

public class ArrayBackedSortedList<E extends Comparable<E>>
        extends ArrayBackedList<E> {

//    @Override
//    public boolean add(E element) {
//        return false;
//    }

    // TODO: Write tests for this
    @Override
    public E get(int index) {
        return null;
    }

    // TODO: Write tests for this
    @Override
    public boolean add(E element, int index) {
        return true;
    }

    // TODO: Write tests for this
    @Override
    public boolean contains(E element) {
        return false;
    }

    // TODO: Write tests for this
    @Override
    int indexOf(E element) {
        return Integer.MAX_VALUE;
    }

    // TODO: Write tests for this
    @Override
    public boolean remove(E element) {
        return false;
    }

    // TODO: Write tests for this
    @Override
    public E remove(int index) {
        return null;
    }

    // TODO: Write tests for this
    @Override
    public int size() {
        return Integer.MIN_VALUE;
    }

    // TODO: Write tests for this
    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void clear() {
        // TODO: Write tests for this
    }

    // TODO: Write tests for this
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    // TODO: Write tests for this
    @Override
    public int hashCode() {
        return 0;
    }

    // TODO: Write tests for this
    @Override
    public String toString() {
        return "Sorry, not implemented yet";
    }

    public ArrayBackedSortedList(int initialCapacity) {
        super(initialCapacity);
    }

}
