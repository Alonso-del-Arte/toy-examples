package graphs;

import org.junit.jupiter.api.Test;
import randomness.ExtendedRandom;

import static org.junit.jupiter.api.Assertions.*;

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

    //    @Test
    void testGetRoot() {fail("Haven't written test yet");
        System.out.println("getRoot");
        BinaryTree<Integer> tree;
    }

}
