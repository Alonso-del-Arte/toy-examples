package collections.immutable;

import java.math.BigInteger;
import java.sql.ResultSet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class ArrayBackedListTest {

    @Test
    void testSize() {
        System.out.println("size");
        int max = 128 + ExtendedRandom.nextInt(32);
        ArrayBackedList<Integer> list = new ArrayBackedList<>();
        for (int expected = 0; expected < max; expected++) {
            int actual = list.size();
            assertEquals(expected, actual);
            list = list.add(expected);
        }
    }

    @Test
    void testContains() {
        System.out.println("contains");
        int firstElem = ExtendedRandom.nextInt(Short.MAX_VALUE);
        int lastElem = firstElem + ExtendedRandom.nextInt(128) + 32;
        ArrayBackedList<Integer> list = new ArrayBackedList<>();
        for (int elem = firstElem; elem <= lastElem; elem++) {
            String beforeMsg = "List should not contain " + elem
                    + " before it's added to the list";
            assert !list.contains(elem) : beforeMsg;
            list.add(elem);
            String afterMsg = "List should contain " + elem
                    + " after it's added to the list";
            assert list.contains(elem) : afterMsg;
        }
    }

    @Test
    void testReferentialEquality() {
        ArrayBackedList<ResultSet> someList = new ArrayBackedList<>();
        assertEquals(someList, someList);
    }

    @Test
    void testConstructorRejectsNullElement() {
        String msg = "Constructor should reject null elements";
        Throwable t = assertThrows(NullPointerException.class, () -> {
            ArrayBackedList<ResultSet> list
                    = new ArrayBackedList<>(null, null, null);
            System.out.println("Should not have been able to create " + list
                    + " with null elements");
        }, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}
