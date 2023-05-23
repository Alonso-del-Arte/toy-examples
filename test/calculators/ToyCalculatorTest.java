package calculators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToyCalculatorTest {

    @Test
    void testOnePlusOne() {
        int expected = 2;
        int actual = ToyCalculator.plus(1, 1);
        assertEquals(expected, actual);
    }

}