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

    @Test
    void testDivisionByZero() {
        int dividend = ExtendedRandom.nextInt();
        Throwable t = assertThrows(ArithmeticException.class, () -> {
            int badDivision = ToyCalculator.divides(dividend, 0);
            System.out.println(dividend + " divided by 0 is said to be "
                    + badDivision);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}