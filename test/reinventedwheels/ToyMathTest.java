package reinventedwheels;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToyMathTest {

    @Test
    void testAbsPositive() {
        long millis = System.currentTimeMillis();
        double expected = 1.0 / millis + (millis >> 32);
        double actual = ToyMath.abs(expected);
        assertEquals(expected, actual);
    }

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

    @Test
    void testRandomDistributesWellAcrossUnitInterval() {
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
