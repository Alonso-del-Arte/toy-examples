package collections;

import java.util.Iterator;

class ArrayIterator<E> implements Iterator<E> {

    private Node<E> current;

    private boolean ranOut = true;

    // TODO: Write tests for this
    @Override
    public boolean hasNext() {
        return this.ranOut;
    }

    // TODO: Write tests for this
    @Override
    public E next() {
        return null;
    }

    private E ascertainNotNull(Object obj, int index) {
        if (obj == null) {
            String excMsg = "Node element at " + index + " must not be null";
            throw new NullPointerException(excMsg);
        }
        return (E) obj;
    }

    // TODO: Write tests for this
    ArrayIterator(Object[] elements, int lastFreeIndex) {
        // TODO: Check elements array is not null, throw NPE if not
        // TODO: Check lastIndex > -1, throw exception if not
        // TODO: Check lastIndex <= elements.length, exc if not
        E elem = (E) elements[0];
        this.current = new Node<>(elem);
//        Node<E> node = this.current;
//        Node<E> follower;
//        for (int i = 1; i < lastIndex; i++) {
//            elem = (E) elements[i];
//            follower = new Node<>(elem);
//            node.attachNext(follower);
//            node = follower;
//        }
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
