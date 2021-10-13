package collections;

public class ArrayBackedQueue<E> extends ArrayBackedCollection<E> {

    // TODO: Write tests for this
    public boolean contains(E element) {
        return false;
    }

    // TODO: Write tests for this
    public E peek() {
        return null;
    }

    // TODO: Write tests for this
    public E poll() {
        return null;
    }

    // TODO: Write tests for this
    public int remainingCapacity() {
        return Integer.MIN_VALUE;
    }

    @Override
    public void clear() {
        // TODO: Write tests for this
    }

    // TODO: Write tests for this
    public int drainTo(ArrayBackedCollection<? super E> collection) {
        return Integer.MIN_VALUE;
    }

    // TODO: Write tests for this
    public int drainTo(ArrayBackedCollection<? super E> collection, int max) {
        return Integer.MIN_VALUE;
    }

    public ArrayBackedQueue(int capacity) {
        super(capacity);
    }

}
