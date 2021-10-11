package collections;

import java.util.Arrays;

public class ArrayBackedSet<E> extends ArrayBackedCollection<E> {

    @Override
    public boolean add(E element) {
        if (element == null) {
            return false;
        }
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
        this(ArrayBackedCollection.DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayBackedSet(int initialCapacity) {
        super(initialCapacity);
    }

}
