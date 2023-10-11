package graphs;

public class BinaryTree<E extends Comparable<E>> {

    // TODO: Write tests for this
    public Node<E> getRoot() {
        return null;
    }

    public BinaryTree(E root) {}

    public static class Node<E> {

        private final E element;

        public E getElement() {
            return this.element;
        }

        // TODO: Write tests for this
        public Node<E> addLeft(E element) {
            return null;
        }

        // TODO: Write tests for this
        public Node<E> addRight(E element) {
            return null;
        }

        Node(E elem) {
            this.element = elem;
        }

    }

}
