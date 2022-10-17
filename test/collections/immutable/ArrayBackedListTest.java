package collections.immutable;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.time.LocalDateTime;

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
    void testGet() {
        System.out.println("get");
        ArrayBackedList<BigInteger> list = new ArrayBackedList<>();
        for (int index = 0; index < 64; index++) {
            BigInteger expected = ExtendedRandom.nextBigInt(64 + index);
            list = list.add(expected);
            BigInteger actual = list.get(index);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testReferentialEquality() {
        ArrayBackedList<ResultSet> someList = new ArrayBackedList<>();
        assertEquals(someList, someList);
    }

    @Test
    void testNotEqualsNull() {
        ArrayBackedList<ResultSet> someList = new ArrayBackedList<>();
        assertNotEquals(someList, null);
    }

    @Test
    void testNotEqualsDiffClass() {
        ArrayBackedList<ResultSet> someList = new ArrayBackedList<>();
        Object obj = new Object();
        assertNotEquals(someList, obj);
    }

    @Test
    void testNotEqualsDiffSizeList() {
        int size = ExtendedRandom.nextInt(64) + 32;
        ArrayBackedList<BigInteger> listA = new ArrayBackedList<>();
        ArrayBackedList<BigInteger> listB = new ArrayBackedList<>();
        for (int i = 0; i < size; i++) {
            BigInteger number = ExtendedRandom.nextBigInt(i);
            listA = listA.add(number);
            listB = listB.add(number);
        }
        listA.add(ExtendedRandom.nextBigInt(size));
        assertNotEquals(listA, listB);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        int size = ExtendedRandom.nextInt(8) + 2;
        ArrayBackedList<BigInteger> someList = new ArrayBackedList<>();
        ArrayBackedList<BigInteger> sameList = new ArrayBackedList<>();
        for (int i = 0; i < size; i++) {
            int n = ExtendedRandom.nextInt();
            BigInteger number = BigInteger.valueOf(n);
            someList.add(number);
            sameList.add(number);
        }
        assertEquals(someList, sameList);
    }

    @Test
    void testNotEqualsDiffElemsList() {
        int size = ExtendedRandom.nextInt(16) + 4;
        ArrayBackedList<BigInteger> listA = new ArrayBackedList<>();
        ArrayBackedList<BigInteger> listB = new ArrayBackedList<>();
        for (int i = 0; i < size; i++) {
            BigInteger number = ExtendedRandom.nextBigInt(64 + i);
            listA.add(number);
            listB.add(number.add(BigInteger.ONE));
        }
        assertNotEquals(listA, listB);
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

    @Test
    void testConstructorTakesElements() {
        BigInteger firstElement = ExtendedRandom.nextBigInt(72);
        BigInteger secondElement = ExtendedRandom.nextBigInt(78);
        BigInteger thirdElement = ExtendedRandom.nextBigInt(84);
        ArrayBackedList<BigInteger> list = new ArrayBackedList<>(firstElement,
                secondElement, thirdElement);
        BigInteger[] expected = {firstElement, secondElement, thirdElement};
        for (int index = 0; index < expected.length; index++) {
            BigInteger actual = list.get(index);
            assertEquals(expected[index], actual);
        }
    }

}
