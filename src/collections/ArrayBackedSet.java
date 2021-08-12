package collections;

import collections.comparators.HashCodeComparator;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayBackedSet<E> implements Iterable<E> {

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

    public boolean add(E element) {
        if (this.contains(element)) {
            return false;
        }
        if (this.nextUp == this.elements.length) {
            this.expandCapacity();
        }
        this.elements[this.nextUp] = element;
        this.nextUp++;
        return true;
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

    public boolean remove(E element) {
        int index = this.indexOf(element);
        if (index == -1) {
            return false;
        } else {
            int lastIndex = --this.nextUp;
            if (index < lastIndex) {
                this.elements[index] = this.elements[lastIndex];
            }
            this.elements[lastIndex] = null;
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

    private boolean backingArrayMatches(ArrayBackedSet<?> other) {
        int index = 0;
        boolean foundInBoth = true;
        while (foundInBoth && index < this.nextUp) {
            int otherIndex = 0;
            boolean foundInOther = false;
            while (!foundInOther && otherIndex < other.nextUp) {
                foundInOther = this.elements[index]
                        .equals(other.elements[otherIndex]);
                otherIndex++;
            }
            foundInBoth = foundInOther;
            index++;
        }
        return foundInBoth;
    }

    // TODO: Write tests for this
    @Override
    public String toString() {
        return "ArrayBackedSet{" +
                "elements=" + Arrays.toString(elements) +
                ", nextUp=" + nextUp +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;
        final ArrayBackedSet<?> other = (ArrayBackedSet<?>) obj;
        if (this.nextUp == other.nextUp) {
            return this.backingArrayMatches(other);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < this.nextUp; i++) {
            hash += this.elements[i].hashCode();
        }
        return hash;
    }

    public ArrayBackedSet() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayBackedSet(int initialCapacity) {
        if (initialCapacity < 0) {
            String excMsg = "Initial capacity " + initialCapacity
                    + " is invalid, needs to be greater than 0";
            throw new IllegalArgumentException(excMsg);
        }
        this.elements = new Object[initialCapacity];
    }

}
