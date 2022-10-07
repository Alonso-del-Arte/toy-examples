package collections.immutable;

import java.math.BigInteger;

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
            list.add(expected);
        }
    }

}
