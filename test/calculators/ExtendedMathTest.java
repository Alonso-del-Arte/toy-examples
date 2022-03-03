package calculators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExtendedMathTest {

    @Test
    void testPrimePiLegendreEstimate() {
        double x = Math.random() * 10000;
        double expected = x / (Math.log(x) - 1.08366);
        double actual = ExtendedMath.primePiLegendreEstimate(x);
        assertEquals(expected, actual, 0.0001);
    }

    void testSqrtFractions() {
        fail("Haven't written test yet");
    }

}
