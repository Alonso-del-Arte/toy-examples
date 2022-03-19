package calculators;

import fractions.Fraction;

import java.util.HashSet;
import java.util.List;
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

    @Test
    void testPrimePiBelowTwo() {
        double x = 1.0 + RANDOM.nextDouble();
        assertEquals(0, ExtendedMath.primePi(x));
    }

    @Test
    void testPrimePi() {
        fail("Haven't written test yet");
    }

    @Test
    void testAbsTheSameForPositive() {
        int denominator = RANDOM.nextInt(256) + 4;
        int numerator = 2 * denominator + RANDOM.nextInt(denominator) + 1;
        Fraction expected = new Fraction(numerator, denominator);
        Fraction actual = ExtendedMath.abs(expected);
        String msg = "abs(" + expected + ") should be " + expected;
        assertEquals(expected, actual, msg);
    }

    @Test
    void testAbs() {
        System.out.println("abs");
        int denominator = RANDOM.nextInt(256) + 4;
        int numerator = 2 * denominator + RANDOM.nextInt(denominator) + 1;
        Fraction negative = new Fraction(-numerator, denominator);
        Fraction expected = new Fraction(numerator, denominator);
        Fraction actual = ExtendedMath.abs(negative);
        String msg = "abs(" + negative + ") should be " + expected;
        assertEquals(expected, actual, msg);
    }

    /**
     * Another test of the sqrt function of the ExtendedMath class. The square
     * root of a negative purely real number is an imaginary number, but the
     * Fraction class can't represent nonzero imaginary numbers like half the
     * imaginary unit. Therefore, the function should throw ArithmeticException
     * for negative fractions.
     */
    @Test
    void testNoSquareRootForNegativeFractions() {
        int numerator = -RANDOM.nextInt(4096) - 1;
        int denominator = RANDOM.nextInt(256) + 2;
        Fraction negative = new Fraction(numerator, denominator);
        Throwable exc = assertThrows(ArithmeticException.class, () -> {
            Fraction badResult = ExtendedMath.sqrt(negative);
            System.out.println("Square root of " + negative + " is said to be "
                    + badResult + "?");
        });
        String excMsg = exc.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    /**
     * Another test of the sqrt function of the ExtendedMath class. The square
     * root 2/9 is the irrational number sqrt(2)/3, and so the function can only
     * give a rational approximation, the same as java.lang.Math.sqrt().
     */
    @Test
    void testSqrt() {
        Fraction twoNinths = new Fraction(2, 9);
        Fraction squareRootApprox = ExtendedMath.sqrt(twoNinths);
        double expected = Math.sqrt(twoNinths.getNumericApproximation());
        double actual = squareRootApprox.getNumericApproximation();
        double delta = 0.00000001;
        assertEquals(expected, actual, delta);
    }

    @Test
    void testSqrtTwoNinths() {}

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
