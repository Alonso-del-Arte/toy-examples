package arithmetic;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RangeTest {

    public static final Random RANDOM = new Random(~System.currentTimeMillis());

    @Test
    void testToString() {
        System.out.println("toString");
        int start = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int step = RANDOM.nextInt(2, 128);
        int multiplier = RANDOM.nextInt(2, 128);
        int end = start + multiplier * step;
        Range instance = new Range(start, end, step);
        String expected = start + " to " + end + " by " + step;
        String actual = instance.toString();
        assertEquals(expected, actual);
    }

}
