package fake.java.lang;

import java.math.BigInteger;

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

}
