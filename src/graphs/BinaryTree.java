package graphs;

/**
 * Represents a binary tree. This class is mutable, though the root element
 * can't be changed after construction.
 * @param <E> The type of the root, branches and leaves. Should implement the
 *           <code>Comparable&lt;E&gt;</code> interface. For example,
 *           <code>BigInteger</code>.
 */
public class BinaryTree<E extends Comparable<E>> {

    private final Node<E> rootNode;

    /**
     * Gives the root node of this binary tree.
     * @return A node containing the root element.
     */
    public Node<E> getRoot() {
        return this.rootNode;
    }

    /**
     * Sole constructor. Initializes a tree with only a root node.
     * @param root The root element. This can't be changed after construction.
     *             All other nodes can be changed.
     */
    public BinaryTree(E root) {
        this.rootNode = new Node<>(root);
    }

    /**
     * Represents a node in the tree. It may be the root node, a branch node or
     * a leaf node.
     * @param <E> The type of elements in the tree, must be the same as the one
     *           the tree was declared to be of, though of course this is not
     *           checked at runtime.
     */
    public static class Node<E> {

        private final E element;

        /**
         * Gives this node's element.
         * @return The element that was passed to the node constructor.
         */
        public E getElement() {
            return this.element;
        }

        // TODO: Write tests for this
        public Node<E> getLeft(E element) {
            return new Node<>((E) new Object());
        }

        // TODO: Write tests for this
        public Node<E> getRight(E element) {
            return new Node<>((E) new Object());
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
