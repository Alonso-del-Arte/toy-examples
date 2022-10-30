package calculators;

import fractions.Fraction;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class ExtendedMathTest {

    private static final Random RANDOM = new Random();

    @Test
    void testPrimePiLegendreEstimate() {
        double x = Math.random() * 10000;
        double expected = x / (Math.log(x) - 1.08366);
        double actual = ExtendedMath.primePiLegendreEstimate(x);
        assertEquals(expected, actual, 0.0001);
    }

    // TODO: Replace RANDOM.nextDouble() with ExtendedRandom.nextDouble()
    @Test
    void testPrimePiBelowTwo() {
        double x = 1.0 + RANDOM.nextDouble();
        assertEquals(0, ExtendedMath.primePi(x));
    }

    @Test
    void testPrimePi() {
        int expected = 0;
        for (int i = 0; i < 1024; i++) {
            if (IntegerMath.isPrime(i)) {
                expected++;
            }
            int actual = ExtendedMath.primePi(i);
            String msg = "pi(" + i + ") ought to be " + expected;
            assertEquals(expected, actual, msg);
        }
    }

    @Test
    void testAbsTheSameForPositive() {
        int denominator = ExtendedRandom.nextInt(256) + 4;
        int numerator = 2 * denominator + ExtendedRandom.nextInt(denominator)
                + 1;
        Fraction expected = new Fraction(numerator, denominator);
        Fraction actual = ExtendedMath.abs(expected);
        String msg = "abs(" + expected + ") should be " + expected;
        assertEquals(expected, actual, msg);
    }

    @Test
    void testAbs() {
        System.out.println("abs");
        int denominator = ExtendedRandom.nextInt(256) + 4;
        int numerator = 2 * denominator + ExtendedRandom.nextInt(denominator) + 1;
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
        int numerator = -ExtendedRandom.nextInt(4096) - 1;
        int denominator = ExtendedRandom.nextInt(256) + 2;
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

    @Test
    void testSqrt() {
        System.out.println("sqrt");
        int denominator = ExtendedRandom.nextInt(256) + 4;
        int numerator = 2 * denominator + 1;
        Fraction expected = new Fraction(numerator, denominator);
        Fraction square = expected.times(expected);
        Fraction actual = ExtendedMath.sqrt(square);
        String msg = "sqrt(" + square + ") expected to be " + expected;
        assertEquals(expected, actual, msg);
    }

    /**
     * Another test of the sqrt function of the ExtendedMath class. The square
     * root of 7/8 is not 1, but it's close.
     */
    @Test
    void testSqrtSevenEighths() {
        Fraction sevenEighths = new Fraction(7, 8);
        Fraction squareRootApprox = ExtendedMath.sqrt(sevenEighths);
        double expected = Math.sqrt(sevenEighths.getNumericApproximation());
        double actual = squareRootApprox.getNumericApproximation();
        double delta = 0.00000001;
        assertEquals(expected, actual, delta);
    }

    /**
     * Another test of the sqrt function of the ExtendedMath class. The square
     * root 2/9 is the irrational number sqrt(2)/3, and so the function can only
     * give a rational approximation, the same as java.lang.Math.sqrt().
     */
    @Test
    void testSqrtTwoNinths() {
        Fraction twoNinths = new Fraction(2, 9);
        Fraction squareRootApprox = ExtendedMath.sqrt(twoNinths);
        double expected = Math.sqrt(twoNinths.getNumericApproximation());
        double actual = squareRootApprox.getNumericApproximation();
        double delta = 0.00000001;
        assertEquals(expected, actual, delta);
    }

    @Test
    void testRandom() {
        System.out.println("random");
        Fraction zero = new Fraction(0, 1);
        Fraction one = new Fraction(1, 1);
        int size = ExtendedRandom.nextInt(512) + 128;
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
