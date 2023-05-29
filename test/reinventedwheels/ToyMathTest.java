package reinventedwheels;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of the ToyMath class.
 * @author Alonso del Arte
 */
class ToyMathTest {

    @Test
    void testAbsPositive() {
        long millis = System.currentTimeMillis();
        double expected = 2.5E16 / millis + 1.3E10 / millis;
        double actual = ToyMath.abs(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testAbsPositiveZero() {
        assertEquals(0.0, ToyMath.abs(0.0));
    }

    @Test
    void testAbsNegativeZero() {
        double negativeZero = Double.longBitsToDouble(Long.MIN_VALUE);
        long actual = Double.doubleToLongBits(ToyMath.abs(negativeZero));
        assertEquals(0, actual);
    }

    @Test
    void testAbsNegative() {
        long millis = System.currentTimeMillis();
        double expected = 2.5E16 / millis + 1.3E10 / millis;
        double actual = ToyMath.abs(-expected);
        String msg = "Absolute value of " + (-expected) + " should be "
                + expected;
        assertEquals(expected, actual, msg);
    }

    /**
     * Test of the random function, of the ToyMath class. The return value of
     * random() should be at least 0.0 but less than 1.0.
     */
    @Test
    void testRandomIsInUnitInterval() {
        double runningTotal = 0.0;
        double additionalIncrement = 0.1;
        double adjusted = additionalIncrement;
        while (adjusted < 100.0) {
            double x = ToyMath.random();
            String msg = "Pseudorandom number " + x
                    + " should be at least 0.0 and less than 1.0";
            assert x >= 0.0 : msg;
            assert x < 1.0 : msg;
            runningTotal += x;
            adjusted += x + additionalIncrement;
        }
        System.out.println("Pseudorandom numbers added up to " + runningTotal);
    }

    /**
     * Another test of the random function, of the ToyMath class. Called a
     * hundred times, random() should give at least ninety distinct values.
     */
    @Test
    void testRandomGivesEnoughDistinctValues() {
        int capacity = 100;
        Set<Double> numbers = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            numbers.add(ToyMath.random());
        }
        int expected = 9 * capacity / 10;
        int actual = numbers.size();
        String msg = "random() gave " + actual
                + " distinct number(s), should've given at least " + expected;
        assert actual >= expected : msg;
    }

    /**
     * Another test of the random function, of the ToyMath class. If the test
     * divides the unit interval into sixteen "zones" and calls random() 256
     * times, it should receive at least one number in each of the sixteen
     * zones. However, this test does not measure the uniformity of the
     * distribution, so, hypothetically, the test might receive one number in
     * each of fifteen zones, and all the rest in a single zone, and it would
     * still be considered passing.
     */
    @Test
    void testRandomDistributesWellEnoughAcrossUnitInterval() {
        int expected = 16;
        Set<Integer> numbers = new HashSet<>(expected);
        int attempts = expected * expected;
        for (int i = 0; i < attempts; i++) {
            numbers.add((int) (ToyMath.random() * expected));
        }
        int actual = numbers.size();
        assertEquals(expected, actual);
    }

}
