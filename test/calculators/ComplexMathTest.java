package calculators;

import numerics.ComplexNumber;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class ComplexMathTest {

    private static final double TEST_DELTA = 0.00000001;

    private static void assertEqualsWithinDelta(ComplexNumber expected,
                                                ComplexNumber actual,
                                                String expLabel) {
        String msgPart = " part of " + expLabel + " expected to be ";
        String msgRe = "Real" + msgPart + expected.getRealPart();
        assertEquals(expected.getRealPart(), actual.getRealPart(), TEST_DELTA,
                msgRe);
        String msgIm = "Imaginary" + msgPart + expected.getImagPart();
        assertEquals(expected.getImagPart(), actual.getImagPart(), TEST_DELTA,
                msgIm);
    }

    @Test
    void testSqrtOfNegativeOne() {
        ComplexNumber negOne = new ComplexNumber(-1.0, 0.0);
        ComplexNumber expected = new ComplexNumber(0.0, 1.0);
        ComplexNumber actual = ComplexMath.sqrt(negOne);
        assertEqualsWithinDelta(expected, actual, "sqrt(-1)");
    }

    @Test
    void testSqrt() {
        System.out.println("sqrt");
        double expectedRe = ExtendedRandom.nextDouble();
        double expectedIm = ExtendedRandom.nextDouble();
        ComplexNumber expected = new ComplexNumber(expectedRe, expectedIm);
        ComplexNumber square = expected.times(expected);
        ComplexNumber actual = ComplexMath.sqrt(square);
        String expLabel = "sqrt(" + square + ")";
        assertEqualsWithinDelta(expected, actual, expLabel);
    }

    @Test
    void testSqrtPurelyRealPositive() {
        double expectedRe = ExtendedRandom.nextDouble() + ExtendedRandom.nextInt(100);
        ComplexNumber expected = new ComplexNumber(expectedRe, 0);
        ComplexNumber square = expected.times(expected);
        ComplexNumber actual = ComplexMath.sqrt(square);
        String expLabel = "sqrt(" + square + ")";
        assertEqualsWithinDelta(expected, actual, expLabel);
    }

}
