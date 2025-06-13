package fake.java.lang;

import java.math.BigInteger;
import java.time.LocalDate;

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

}
