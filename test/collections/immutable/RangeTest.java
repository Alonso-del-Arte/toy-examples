package collections.immutable;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RangeTest {

    private static final Random RANDOM = new Random();

    @Test
    void testGetBegin() {
        System.out.println("getBegin");
        int expected = RANDOM.nextInt();
        int finish = expected + 1;
        int step = 1;
        Range range = new Range(expected, finish, step);
        int actual = range.getStart();
        assertEquals(expected, actual);
    }

}
