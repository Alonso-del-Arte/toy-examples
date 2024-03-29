package collections.mutable;

import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class ArrayBackedCollection<E> implements Iterable<E> {

    public static final int DEFAULT_INITIAL_CAPACITY = 16;

    Object[] elements;

    int nextUp = 0;

    final void expandCapacity() {
        int largerSize = 3 * this.elements.length / 2;
        Object[] largerArray = new Object[largerSize];
        System.arraycopy(this.elements, 0, largerArray, 0,
                this.elements.length);
        this.elements = largerArray;
    }

    public boolean add(E element) {
        if (element == null) {
            return false;
        }
        if (this.nextUp == this.elements.length) {
            this.expandCapacity();
        }
        this.elements[this.nextUp] = element;
        this.nextUp++;
        return true;
    }

    int indexOf(E element) {
        boolean found = false;
        int counter = 0;
        while (!found && counter < this.nextUp) {
            found = element.equals(this.elements[counter]);
            counter++;
        }
        if (found) {
            return counter - 1;
        } else {
            return -1;
        }
    }

    public boolean contains(E element) {
        if (element == null) return false;
        return this.indexOf(element) > -1;
    }

    public boolean remove(E element) {
        int index = this.indexOf(element);
        if (index < 0) {
            return false;
        } else {
            this.nextUp--;
            if (index < this.nextUp) {
                this.elements[index] = this.elements[this.nextUp];
            }
            this.elements[this.nextUp] = null;
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
        this.elements = new Object[this.elements.length];
        this.nextUp = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        ArrayBackedCollection<?> other = (ArrayBackedCollection<?>) obj;
        if (this.nextUp != other.nextUp) {
            return false;
        }
        boolean matchesSoFar = true;
        int index = 0;
        while (matchesSoFar && index < this.nextUp) {
            matchesSoFar = this.elements[index].equals(other.elements[index]);
            index++;
        }
        return matchesSoFar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < this.nextUp; i++) {
            hash += (i + 1) * this.elements[i].hashCode();
        }
        return hash;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < ArrayBackedCollection.this.nextUp;
            }

            @Override
            public E next() {
                if (this.index == ArrayBackedCollection.this.nextUp) {
                    String excMsg = "Collection has no more elements";
                    throw new NoSuchElementException(excMsg);
                }
                @SuppressWarnings("unchecked")
                E element = (E) ArrayBackedCollection.this.elements[this.index++];
                return element;
            }

        };
    }

    public ArrayBackedCollection(ArrayBackedCollection<?
            extends E> collection) {
        this.elements = new Object[collection.elements.length];
        this.nextUp = collection.nextUp;
        System.arraycopy(collection.elements, 0, this.elements, 0, this.nextUp);
    }

    public ArrayBackedCollection(int initialCapacity) {
        if (initialCapacity < 0) {
            String excMsg = "Initial capacity " + initialCapacity
                    + " is invalid, needs to be greater than 0";
            throw new IllegalArgumentException(excMsg);
        }
        this.elements = new Object[initialCapacity];
    }

}
