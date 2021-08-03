package collections;

import java.util.Iterator;

class ArrayIterator<E> implements Iterator<E> {

    private Node<E> current;

    private boolean ranOut = false;

    @Override
    public boolean hasNext() {
        return !this.ranOut;
    }

    @Override
    public E next() {
        if (this.ranOut) {
            String excMsg = "There are no more elements to iterate through";
            throw new RuntimeException(excMsg);
        }
        if (this.current.nextElement == null) {
            this.ranOut = true;
        } else {
            this.current = this.current.nextElement;
        }
        return null;
    }

    private E ascertainNotNull(Object obj, int index) {
        if (obj == null) {
            String excMsg = "Node element at " + index + " must not be null";
            throw new NullPointerException(excMsg);
        }
        return (E) obj;
    }

    ArrayIterator(Object[] elements, int lastFreeIndex) {
        if (elements == null) {
            String excMsg = "Elements array must not be null";
            throw new NullPointerException(excMsg);
        }
        if (lastFreeIndex < 0 || lastFreeIndex > elements.length) {
            String excMsg = "Last free index " + lastFreeIndex
                    + " is out of bounds (at least 0 but not more than "
                    + elements.length + " needed)";
            throw new IllegalArgumentException(excMsg);
        }
        if (lastFreeIndex == 0) {
            this.ranOut = true;
        } else {
            E elem = this.ascertainNotNull(elements[0], 0);
            this.current = new Node<>(elem);
            Node<E> node = this.current;
            Node<E> follower;
            for (int i = 1; i < lastFreeIndex; i++) {
                elem = this.ascertainNotNull(elements[i], i);
                follower = new Node<>(elem);
                node.attachNext(follower);
                node = follower;
            }
        }
    }

    private static class Node<E> {

        final E element;

        Node<E> nextElement = null;

        void attachNext(Node<E> next) {
            this.nextElement = next;
        }

        Node(E elem) {
            this.element = elem;
        }

    }

}
