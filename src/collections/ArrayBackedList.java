package collections;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayBackedList<E> implements Iterable<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private Object[] elements;

    private int nextUp = 0;

    private void expandCapacity() {
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
    public boolean add(E element, int index) {
        return false;
    }

    private int indexOf(E element) {
        boolean flag = false;
        int index = 0;
        while (!flag && index < this.nextUp) {
            flag = element.equals(this.elements[index]);
            index++;
        }
        if (flag) {
            return index - 1;
        } else {
            return -1;
        }
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

    // TODO: Write tests for this
    @Override
    public String toString() {
        return "ArrayBackedSet{" +
                "elements=" + Arrays.toString(this.elements) +
                ", nextUp=" + this.nextUp +
                '}';
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

    public ArrayBackedList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayBackedList(int initialCapacity) {
        this.elements = new Object[initialCapacity];
    }

}
