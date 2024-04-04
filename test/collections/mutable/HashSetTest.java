package collections.mutable;

import java.math.BigInteger;
import java.sql.NClob;
import java.sql.ResultSet;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class HashSetTest {

    @Test
    void testIsEmpty() {
        System.out.println("isEmpty");
        HashSet<ResultSet> set = new HashSet<>();
        assert set.isEmpty() : "Newly created set should be empty";
    }

    @Test
    void testSizeZeroAtFirst() {
        HashSet<NClob> set = new HashSet<>();
        int expected = 0;
        int actual = set.size();
        assertEquals(expected, actual,
                "Newly created set should have zero elements");
    }

    @Test
    void testIsNotEmpty() {
        HashSet<LocalDateTime> set = new HashSet<>();
        LocalDateTime dateTime = LocalDateTime.now();
        set.add(dateTime);String msg = "After adding " + dateTime
                + " to set of times, set should not be empty";
        assert !set.isEmpty() : msg;
    }

    @Test
    void testSize() {
        System.out.println("size");
        int maxCap = ExtendedRandom.nextInt(64) + 16;
        HashSet<Integer> set = new HashSet<>();
        for (int expected = 0; expected < maxCap; expected++) {
            int actual = set.size();
            String msg = "Element should have " + expected
                    + " element(s) so far";
            assertEquals(expected, actual, msg);
            set.add(expected);
        }
    }

    @Test
    void testAdd() {
        System.out.println("add");
        HashSet<BigInteger> set = new HashSet<>();
        BigInteger number = BigInteger.valueOf(System.currentTimeMillis());
        boolean opResult = set.add(number);
        String msg = "Should have been able to add " + number + " to empty set";
        assert opResult : msg;
    }

}
