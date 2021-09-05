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

    private void shiftElementsLeft(int index) {
        int lastIndex = this.nextUp - 1;
        int length = lastIndex - index;
        System.arraycopy(this.elements, index + 1, this.elements, index,
                length);
        this.elements[lastIndex] = null;
        this.nextUp--;
    }

    private void shiftElementsRight(int index) {
        int length = this.nextUp - index;
        System.arraycopy(this.elements, index, this.elements, index + 1,
                length);
        this.nextUp++;
    }

    public boolean add(E element) {
        if (this.nextUp == this.elements.length) {
            this.expandCapacity();
        }
        this.elements[this.nextUp] = element;
        this.nextUp++;
        return true;
    }

    public boolean add(E element, int index) {
        if (index < 0 || index > this.nextUp) {
            String excMsg = "Index " + index + " is out of bounds";
            throw new IndexOutOfBoundsException(excMsg);
        }
        if (this.nextUp == this.elements.length) {
            this.expandCapacity();
        }
        if (index == this.nextUp) {
            return this.add(element);
        }
        this.shiftElementsRight(index);
        this.elements[index] = element;
        return true;
    }

    public E get(int index) {
        if (index < 0 || index >= this.nextUp) {
            String excMsg = "Index " + index + " is out of bounds";
            throw new IndexOutOfBoundsException(excMsg);
        }
        return (E) this.elements[index];
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

    public boolean contains(E element) {
        return this.indexOf(element) > -1;
    }

    public E remove(int index) {
        if (index < 0 || index >= this.nextUp) {
            String excMsg = "Index " + index + " is out of bounds";
            throw new IndexOutOfBoundsException(excMsg);
        }
        Object holder = this.elements[index];
        this.shiftElementsLeft(index);
        return (E) holder;
    }

    public boolean remove(E element) {
        int index = this.indexOf(element);
        if (index == -1) {
            return false;
        } else {
            this.shiftElementsLeft(index);
            return true;
        }
    }

    public int size() {
        return this.nextUp;
    }

    public boolean isEmpty() {
        return this.nextUp == 0;
    }

    public void clear() {
        for (int i = 0; i < this.nextUp; i++) {
            this.elements[i] = null;
        }
        this.nextUp = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(this.elements, this.nextUp);
    }

    // TODO: Write tests for this
    @Override
    public String toString() {
        return "ArrayBackedList{" +
                "elements=" + Arrays.toString(this.elements) +
                ", nextUp=" + this.nextUp +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!obj.getClass().equals(this.getClass())) return false;
        ArrayBackedList<?> other = (ArrayBackedList<?>) obj;
        if (this.nextUp != other.nextUp) return false;
        return Arrays.equals(this.elements, other.elements);
    }

    @Override
    public int hashCode() {
        int hash = Integer.MIN_VALUE;
        for (int i = 0; i < this.nextUp; i++) {
            hash <<= 1;
            hash += this.elements[i].hashCode();
        }
        return hash;
    }

    public ArrayBackedList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayBackedList(int initialCapacity) {
        if (initialCapacity < 0) {
            String excMsg = "Negative initial capacity " + initialCapacity
                    + " is invalid";
            throw new IllegalArgumentException(excMsg);
        }
        this.elements = new Object[initialCapacity];
    }

}
