package calculators;

import fractions.Fraction;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExtendedMathTest {

    private static final Random RANDOM = new Random();

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

    @Test
    void testRandom() {
        System.out.println("random");
        Fraction zero = new Fraction(0, 1);
        Fraction one = new Fraction(1, 1);
        int size = RANDOM.nextInt(512) + 128;
        Set<Fraction> fractions = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            Fraction fraction = ExtendedMath.random();
            String msg = "Fraction x = " + fraction
                    + " expected to be in range 0 <= x < 1";
            assert (zero.compareTo(fraction) <= 0)
                    && (fraction.compareTo(one) < 0) : msg;
            fractions.add(fraction);
        }
        int expected = (int) Math.floor(0.9 * size);
        int actual = fractions.size();
        System.out.println("Successfully got " + size
                + " fractions x in the range 0 <= x < 1; " + actual
                + " distinct");
        String msg = "Expected at least " + expected
                + " distinct fractions, got " + actual;
        assert actual >= expected : msg;
    }

}
