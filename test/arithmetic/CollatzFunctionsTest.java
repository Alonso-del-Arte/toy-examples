package arithmetic;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CollatzFunctionsTest {

    private static final Random RANDOM = new Random();

    @Test
    void testClassicOdd() {
        int start = 2 * RANDOM.nextInt(1024) + 1;
        int end = start + 2 * RANDOM.nextInt(512) + 1;
        for (int n = start; n < end; n += 2) {
            int expected = 3 * n + 1;
            int actual = CollatzFunctions.CLASSIC.applyAsInt(n);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testClassicEven() {
        int start = 2 * RANDOM.nextInt(1024);
        int end = start + 2 * RANDOM.nextInt(512);
        for (int n = start; n < end; n += 2) {
            int expected = n / 2;
            int actual = CollatzFunctions.CLASSIC.applyAsInt(n);
            assertEquals(expected, actual);
        }
    }

}