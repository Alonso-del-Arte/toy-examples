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

    @Test
    void testGetStart() {
        System.out.println("getStart");
        int expected = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int step = RANDOM.nextInt(2, 128);
        int multiplier = RANDOM.nextInt(2, 128);
        int end = expected + multiplier * step;
        Range instance = new Range(expected, end, step);
        int actual = instance.getStart();
        String message = "Getting start of " + instance;
        assertEquals(expected, actual, message);
    }

    // begin separator -----------------------------------------
    // TODO: Remove this separator once all functions are tested
    // ------------------------------------------- end separator

    @Test
    void testToStringAuxConstructor() {
        int start = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int end = start + RANDOM.nextInt(1, 128);
        Range instance = new Range(start, end);
        String expected = start + " to " + end;
        String actual = instance.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testGetStartAuxConstructor() {
        int expected = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int end = expected + RANDOM.nextInt(1, 128);
        Range instance = new Range(expected, end);
        int actual = instance.getStart();
        String message = "Getting start of " + instance;
        assertEquals(expected, actual, message);
    }

    // TODO: Test toString() equivalence for aux constructor and main
    //  constructor with explicit step 1

    // TODO: Test equals()

    // TODO: Test hashCode()

}
