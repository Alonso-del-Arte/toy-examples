package collections;

import java.util.Arrays;

public class ArrayBackedList<E> extends ArrayBackedCollection<E> {

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

    public boolean add(E element, int index) {
        if (element == null) {
            return false;
        }
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

    public E remove(int index) {
        if (index < 0 || index >= this.nextUp) {
            String excMsg = "Index " + index + " is out of bounds";
            throw new IndexOutOfBoundsException(excMsg);
        }
        Object holder = this.elements[index];
        this.shiftElementsLeft(index);
        return (E) holder;
    }

    @Override
    public boolean remove(E element) {
        int index = this.indexOf(element);
        if (index < 0) {
            return false;
        } else {
            this.shiftElementsLeft(index);
            return true;
        }
    }

    // TODO: Write tests for this
    @Override
    public String toString() {
        return "ArrayBackedList{" +
                "elements=" + Arrays.toString(this.elements) +
                ", nextUp=" + this.nextUp +
                '}';
    }

    public ArrayBackedList() {
        this(ArrayBackedCollection.DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayBackedList(int initialCapacity) {
        super(initialCapacity);
    }

}
