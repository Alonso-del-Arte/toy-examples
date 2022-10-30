package collections.mutable;

import java.math.BigInteger;
import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import javax.naming.ldap.Rdn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayBackedBagTest {

    private static final Random RANDOM = new Random();

    @Test
    void testGetCountOfAbsentElement() {
        ArrayBackedBag<LocalDateTime> bag = new ArrayBackedBag<>();
        String msg = "Count of element in empty bag should always be 0";
        assertEquals(0, bag.getCount(LocalDateTime.now()), msg);
    }

    @Test
    void testGetCount() {
        System.out.println("getCount");
        ArrayBackedBag<BigInteger> bag = new ArrayBackedBag<>();
        BigInteger number = new BigInteger(72, RANDOM);
        bag.add(number);
        String msg = "Since " + number
                + " was added to the bag, its count should be 1";
        assertEquals(1, bag.getCount(number), msg);
    }

    @Test
    void testGetCountOfElementWithDuplicates() {
        ArrayBackedBag<Integer> bag = new ArrayBackedBag<>();
        int bound = RANDOM.nextInt(256) + 4;
        for (int n = 2; n < bound; n++) {
            for (int i = 0; i < n; i++) {
                bag.add(n);
            }
        }
        for (int expected = 2; expected < bound; expected++) {
            int actual = bag.getCount(expected);
            assertEquals(expected, actual);
        }
    }

    @Test
    void uniqueSet() {
        System.out.println("uniqueSet");
        fail("Haven't written test yet");
    }

}
