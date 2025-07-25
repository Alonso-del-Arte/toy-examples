package fake.java.lang;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static randomness.ExtendedRandom.*;

class ArrayTest {

    @Test
    void testLength() {
        System.out.println("length");
        int expected = nextInt(16) + 4;
        BigInteger[] bigInts = new BigInteger[expected];
        for (int i = 0; i < expected; i++) {
            bigInts[i] = nextBigInt(32 + i);
        }
        Array<BigInteger> instance = new Array<>(bigInts);
        int actual = instance.length;
        assertEquals(expected, actual);
    }

    @Test
    void testToString() {
        System.out.println("toString");
        int capacity = nextInt(8) + 2;
        LocalDate[] dates = new LocalDate[capacity];
        LocalDate today = LocalDate.now();
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < capacity; i++) {
            int daysToAdd = nextInt(365) + i;
            LocalDate date = today.plusDays(daysToAdd);
            dates[i] = date;
            s.append(date);
            s.append(',');
        }
        s.append(']');
        Array<LocalDate> instance = new Array<>(dates);
        String expected = s.toString().replace(",]", "]");
        String actual = instance.toString().replace(" ", "");
        assertEquals(expected, actual);
    }

    private static Object passThrough(Object obj) {
        return obj;
    }

    @Test
    void testReferentialEquality() {
        int capacity = nextInt(8) + 2;
        String[] alphaNumSeqs = new String[capacity];
        for (int i = 0; i < capacity; i++) {
            alphaNumSeqs[i] = alphanumeric(i + 1);
        }
        Array<String> instance = new Array<>(alphaNumSeqs);
        Object obj = passThrough(instance);
        assertEquals(instance, obj);
    }

    @Test
    void testNotEqualsNull() {
        int capacity = nextInt(16) + 4;
        BigInteger[] bigInts = new BigInteger[capacity];
        for (int i = 0; i < capacity; i++) {
            bigInts[i] = nextBigInt(32 + i);
        }
        Array<BigInteger> instance = new Array<>(bigInts);
        Object obj = passThrough(null);
        assertNotEquals(instance, obj);
    }

    @Test
    void testNotEqualsDiffClass() {
        int capacity = nextInt(8) + 2;
        String[] alphaNumSeqs = new String[capacity];
        for (int i = 0; i < capacity; i++) {
            alphaNumSeqs[i] = alphanumeric(i + 3);
        }
        Array<String> instance = new Array<>(alphaNumSeqs);
        for (String s : alphaNumSeqs) {
            Object obj = passThrough(s);
            assertNotEquals(instance, obj);
        }
    }

    @Test
    void testNotEqualsDiffLength() {
        int capacityA = nextInt(8) + 2;
        BigInteger[] bigIntsA = new BigInteger[capacityA];
        int capacityB = capacityA + nextInt(8) + 1;
        BigInteger[] bigIntsB = new BigInteger[capacityB];
        Random random = new Random();
        for (int i = 0; i < capacityA; i++) {
            BigInteger num = new BigInteger(64 + i, random);
            bigIntsA[i] = num;
            bigIntsB[i] = num;
        }
        for (int j = capacityA; j < capacityB; j++) {
            BigInteger num = new BigInteger(64 + j, random);
            bigIntsB[j] = num;
        }
        Array<BigInteger> arrayA = new Array<>(bigIntsA);
        Array<BigInteger> arrayB = new Array<>(bigIntsB);
        assertNotEquals(arrayA, arrayB);
    }

    @Test
    void testNotEqualsDiffElems() {
        int capacity = nextInt(8) + 2;
        String[] stringsA = new String[capacity];
        String[] stringsB = new String[capacity];
        for (int i = 0; i < capacity; i++) {
            String s = alphanumeric(4 + i);
            stringsA[i] = s;
            stringsB[i] = s;
        }
        int changeIndex = nextInt(capacity);
        stringsB[changeIndex] = "CHANGED " + stringsA[changeIndex];
        Array<String> arrayA = new Array<>(stringsA);
        Array<String> arrayB = new Array<>(stringsB);
        assertNotEquals(arrayA, arrayB);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        int capacity = nextInt(8) + 2;
        String[] strings = new String[capacity];
        for (int i = 0; i < capacity; i++) {
            strings[i] = alphanumeric(4 + i);
        }
        Array<String> someArray = new Array<>(strings);
        Array<String> sameArray = new Array<>(strings);
        assertEquals(someArray, sameArray);
    }

    @Test
    void testNullElemEqualsThrowsNoException() {
        assertDoesNotThrow(() -> {
            int capacity = nextInt(8) + 2;
            BigInteger[] bigIntsA = new BigInteger[capacity];
            BigInteger[] bigIntsB = new BigInteger[capacity];
            Random random = new Random();
            for (int i = 0; i < capacity; i++) {
                BigInteger num = new BigInteger(64 + i, random);
                bigIntsA[i] = num;
                bigIntsB[i] = num;
            }
            int changeIndex = nextInt(capacity);
            bigIntsA[changeIndex] = null;
            Array<BigInteger> arrayA = new Array<>(bigIntsA);
            Array<BigInteger> arrayB = new Array<>(bigIntsB);
            assertNotEquals(arrayA, arrayB);
        });
    }

    @Test
    void testConstructorCopiesArrayValues() {
        int capacity = nextInt(8) + 2;
        String[] strings = new String[capacity];
        String[] copied = new String[capacity];
        for (int i = 0; i < capacity; i++) {
            String s = alphanumeric(5 + i);
            strings[i] = s;
            copied[i] = s;
        }
        Array<String> expected = new Array<>(strings);
        Array<String> actual = new Array<>(copied);
        int changeIndex = nextInt(capacity);
        copied[changeIndex] = strings[changeIndex].toUpperCase() + " changed";
        assertEquals(expected, actual);
    }

}
