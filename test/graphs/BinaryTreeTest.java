package graphs;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class BinaryTreeTest {

    @Test
    void testNodeGetElement() {
        System.out.println("getElement");
        int length = ExtendedRandom.nextInt(16) + 4;
        String expected = ExtendedRandom.alphanumeric(length);
        BinaryTree.Node<String> node = new BinaryTree.Node<>(expected);
        String actual = node.getElement();
        assertEquals(expected, actual);
    }

    @Test
    void testGetRoot() {
        System.out.println("getRoot");
        int expected = ExtendedRandom.nextInt();
        BinaryTree<Integer> tree = new BinaryTree<>(expected);
        BinaryTree.Node<Integer> root = tree.getRoot();
        assert root != null : "Root should never be null";
        int actual = root.getElement();
        assertEquals(expected, actual);
    }

    @Test
    void testNodeGetLeftIsNullForNewNode() {
        int bitLength = 64 + ExtendedRandom.nextInt(64);
        BinaryTree.Node<BigInteger> node
                = new BinaryTree.Node<>(ExtendedRandom.nextBigInt(bitLength));
        BinaryTree.Node<BigInteger> left = node.getLeft();
        assertNull(left);
    }

    @Test
    void testNodeGetRightIsNullForNewNode() {
        int bitLength = 64 + ExtendedRandom.nextInt(64);
        BinaryTree.Node<BigInteger> node
                = new BinaryTree.Node<>(ExtendedRandom.nextBigInt(bitLength));
        BinaryTree.Node<BigInteger> right = node.getRight();
        assertNull(right);
    }

}
