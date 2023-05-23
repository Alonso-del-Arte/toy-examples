package calculators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class ToyCalculatorTest {

    @Test
    void testOnePlusOne() {
        int expected = 2;
        int actual = ToyCalculator.plus(1, 1);
        assertEquals(expected, actual);
    }

    @Test
    void testPlus() {
        System.out.println("plus");
        int addendA = ExtendedRandom.nextInt(1024) - 512;
        int addendB = ExtendedRandom.nextInt(1024) - 512;
        int expected = addendA + addendB;
        int actual = ToyCalculator.plus(addendA, addendB);
        String msg = "Calculating " + addendA + " + " + addendB;
        assertEquals(expected, actual, msg);
    }

    void testDivisionByZero() {
        fail();
    }

}