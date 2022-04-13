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

    @Test
    void testClassicOddMultiplyExact() {
        int minimum = Integer.MAX_VALUE / 3 + 1;
        int span = minimum / 3;
        int n = minimum + RANDOM.nextInt(span);
        Throwable t = assertThrows(ArithmeticException.class, () -> {
            int badResult = CollatzFunctions.CLASSIC.applyAsInt(n);
            System.out.println("f(" + n + ") said to be " + badResult);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}