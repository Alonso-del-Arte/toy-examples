package fractions;

import calculators.ExtendedMath;
import calculators.IntegerMath;

import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    private static final Random RANDOM = new Random();

    @Test
    void testGetNumerator() {
        System.out.println("getNumerator");
        int expected = RANDOM.nextInt(1024) + 3;
        int denominator = 2 * expected + 1;
        Fraction fraction = new Fraction(expected, denominator);
        long actual = fraction.getNumerator();
        assertEquals(expected, actual);
    }

    @Test
    void testGetDenominator() {
        System.out.println("getDenominator");
        int numerator = RANDOM.nextInt(1024) + 3;
        int expected = 2 * numerator + 1;
        Fraction fraction = new Fraction(numerator, expected);
        long actual = fraction.getDenominator();
        assertEquals(expected, actual);
    }

    @Test
    void testGetNumericApproximation() {
        System.out.println("getNumericApproximation");
        int denominator = RANDOM.nextInt(512) + 8;
        int numerator = RANDOM.nextInt(2 * denominator) - denominator;
        Fraction fraction = new Fraction(numerator, denominator);
        double expected = ((double) numerator) / denominator;
        double actual = fraction.getNumericApproximation();
        double delta = 0.00000001;
        String msg = "Numeric approximation of " + fraction
                + " should be roughly " + expected;
        assertEquals(expected, actual, delta, msg);
    }

    @Test
    void testToString() {
        System.out.println("toString");
        int numerator = RANDOM.nextInt(1024) + 3;
        int denominator = 2 * numerator + 1;
        Fraction fraction = new Fraction(numerator, denominator);
        String expected = numerator + "/" + denominator;
        String actual = fraction.toString().replace(" ", "");
        assertEquals(expected, actual);
    }

    @Test
    void testToStringOmitsDenominatorOne() {
        int numerator = RANDOM.nextInt();
        Fraction wholeNumber = new Fraction(numerator, 1);
        String expected = Integer.toString(numerator);
        String actual = wholeNumber.toString().replace(" ", "");
        assertEquals(expected, actual);
    }

    private Fraction chooseSomeFraction() {
        int numerator = RANDOM.nextInt(32768) - 16384;
        int denominator = RANDOM.nextInt(32764) + 4;
        return new Fraction(numerator, denominator);
    }

    @Test
    void testReferentialEquality() {
        Fraction someFraction = this.chooseSomeFraction();
        assertEquals(someFraction, someFraction);
    }

    @Test
    void testNotEqualsNull() {
        Fraction someFraction = this.chooseSomeFraction();
        assertNotEquals(someFraction, null);
    }

    @Test
    void testNotEqualsDiffClass() {
        int numerator = RANDOM.nextInt(256) + 4;
        int denominator = RANDOM.nextInt(512) + 8;
        Fraction fraction = new Fraction(numerator, denominator);
        Fraction anonClassFraction = new Fraction(numerator, denominator) {

            @Override
            public String toString() {
                return super.toString().replace("/", ":");
            }

        };
        assertNotEquals(fraction, anonClassFraction);
    }

    @Test
    void testNotEqualsDifferentNumerator() {
        int numeratorA = 2 * RANDOM.nextInt(2048) + 1;
        int numeratorB = numeratorA + 2;
        int denominator = numeratorA + 1;
        Fraction fractionA = new Fraction(numeratorA, denominator);
        Fraction fractionB = new Fraction(numeratorB, denominator);
        assertNotEquals(fractionA, fractionB);
    }

    @Test
    void testEqualsSameNumeratorSameDenominator() {
        int numerator = RANDOM.nextInt(8192) + 1;
        int denominator = numerator + RANDOM.nextInt(1024) + 1;
        Fraction someFraction = new Fraction(numerator, denominator);
        Fraction sameFraction = new Fraction(numerator, denominator);
        assertEquals(someFraction, sameFraction);
    }

    @Test
    void testNotEqualsDifferentDenominator() {
        int numerator = 2 * RANDOM.nextInt(4096) + 3;
        int denominatorA = numerator - 1;
        int denominatorB = numerator + 1;
        Fraction fractionA = new Fraction(numerator, denominatorA);
        Fraction fractionB = new Fraction(numerator, denominatorB);
        assertNotEquals(fractionA, fractionB);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        int numerator = RANDOM.nextInt(1024) + 16;
        int denominator = 2 * numerator + 1;
        int multiplier = RANDOM.nextInt(16) + 4;
        Fraction someFraction = new Fraction(numerator, denominator);
        Fraction sameFraction = new Fraction(multiplier * numerator,
                multiplier * denominator);
        assertEquals(someFraction, sameFraction);
    }

    @Test
    void testHashCode() {
        System.out.println("hashCode");
        Set<Fraction> fractions = new HashSet<>();
        Set<Integer> hashes = new HashSet<>();
        for (int numer = -16; numer < 16; numer++) {
            for (int denom = 1; denom < 16; denom++) {
                Fraction fraction = new Fraction(numer, denom);
                fractions.add(fraction);
                hashes.add(fraction.hashCode());
            }
        }
        int expected = fractions.size();
        int actual = hashes.size();
        String msg = "Given " + expected + " distinct fractions, " + expected
                + " distinct hash codes are expected";
        assertEquals(expected, actual, msg);
    }

    @Test
    void testCompareTo() {
        List<Fraction> expected = new ArrayList<>();
        for (int numer = -63; numer < 64; numer++) {
            expected.add(new Fraction(numer, 64));
        }
        List<Fraction> actual = new ArrayList<>();
        for (int denom = 64; denom > 0; denom /= 2) {
            int start = -denom + 1;
            for (int numer = start; numer < denom; numer += 2) {
                actual.add(new Fraction(numer, denom));
            }
        }
        Collections.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testPlusSameDenominator() {
        int start = RANDOM.nextInt(128) + 32;
        int end = 2 * start + RANDOM.nextInt(64) + 16;
        int denominator = IntegerMath.randomPrime(start, end);
        int numeratorA = RANDOM.nextInt(denominator / 2) + 1;
        int numeratorB = RANDOM.nextInt(denominator / 2) + 1;
        Fraction fractionA = new Fraction(numeratorA, denominator);
        Fraction fractionB = new Fraction(numeratorB, denominator);
        Fraction expected = new Fraction(numeratorA + numeratorB, denominator);
        Fraction actual = fractionA.plus(fractionB);
        assertEquals(expected, actual);
    }

    @Test
    void testPlus() {
        System.out.println("plus");
        int n = RANDOM.nextInt(64) + 16;
        Fraction fraction = new Fraction(n, n + 1);
        Fraction reciprocal = new Fraction(n + 1, n);
        Fraction expected = new Fraction(2 * n * n + 2 * n + 1, n * n + n);
        Fraction actual = fraction.plus(reciprocal);
        String msg = "Expecting " + fraction + " plus its reciprocal to be "
                + expected;
        assertEquals(expected, actual, msg);
    }

    @Test
    void testNegate() {
        System.out.println("negate");
        int numerator = RANDOM.nextInt();
        int denominator = RANDOM.nextInt(2048) + 2;
        Fraction fraction = new Fraction(numerator, denominator);
        Fraction expected = new Fraction(-numerator, denominator);
        Fraction actual = fraction.negate();
        String msg = fraction + " negated should be " + expected;
        assertEquals(expected, actual, msg);
    }

    @Test
    void testMinus() {
        System.out.println("minus");
        Fraction expected = ExtendedMath.random();
        Fraction subtrahend = ExtendedMath.random();
        Fraction minuend = expected.plus(subtrahend);
        Fraction actual = minuend.minus(subtrahend);
        String msg = minuend + " minus " + subtrahend + " expected to be "
                + expected;
        assertEquals(expected, actual, msg);
    }

    @Test
    void testTimes() {
        System.out.println("times");
        int numeratorA = RANDOM.nextInt(128) - 64;
        int denominatorA = RANDOM.nextInt(64) + 16;
        int numeratorB = RANDOM.nextInt(128) - 64;
        int denominatorB = RANDOM.nextInt(64) + 16;
        Fraction multiplicandA = new Fraction(numeratorA, denominatorA);
        Fraction multiplicandB = new Fraction(numeratorB, denominatorB);
        Fraction expected = new Fraction(numeratorA * numeratorB,
                denominatorA * denominatorB);
        Fraction actual = multiplicandA.times(multiplicandB);
        String msg = "Expecting " + multiplicandA + " times " + multiplicandB
                + " to be " + expected;
        assertEquals(expected, actual, msg);
    }

    @Test
    void testNoReciprocalForZero() {
        Fraction zero = new Fraction(0, 1);
        Throwable exc = assertThrows(ArithmeticException.class, () -> {
            Fraction badReciprocal = zero.reciprocal();
            System.out.println("Reciprocal of " + zero.toString() + " is said to be "
                    + badReciprocal.toString());
        });
        String excMsg = exc.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testReciprocal() {
        Fraction fraction = ExtendedMath.random();
        Fraction expected = new Fraction(fraction.getDenominator(),
                fraction.getNumerator());
        Fraction actual = fraction.reciprocal();
        assertEquals(expected, actual);
    }

    @Test
    void testDivisionByZero() {
        Fraction fraction = ExtendedMath.random();
        Fraction zero = new Fraction(0, 1);
        Throwable exc = assertThrows(ArithmeticException.class, () -> {
            Fraction badResult = fraction.divides(zero);
            System.out.println(fraction + " divided by " + zero
                    + " is said to be " + badResult.toString());
        });
        String excMsg = exc.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testDivides() {
        System.out.println("divides");
        Fraction expected = ExtendedMath.random();
        Fraction divisor = ExtendedMath.random().plus(new Fraction(1, 2));
        Fraction dividend = expected.times(divisor);
        Fraction actual = dividend.divides(divisor);
        String msg = dividend + " divided by " + divisor + " expected to be "
                + expected;
        assertEquals(expected, actual, msg);
    }

    @Test
    void testConstructorChangesNegativeDenominatorToPositive() {
        int denominator = IntegerMath.randomPrime(-128);
        int expected = -denominator;
        int numerator = expected - 1;
        Fraction fraction = new Fraction(numerator, denominator);
        long actual = fraction.getDenominator();
        assertEquals(expected, actual);
    }

    @Test
    void testConstructorRejectsDenominatorZero() {
        int numerator = IntegerMath.randomPrime(128);
        int denominator = 0;
        Throwable t = assertThrows(ArithmeticException.class, () -> {
            Fraction badFraction = new Fraction(numerator, denominator);
            System.out.println("Should not have been able to create "
                    + badFraction + " with denominator " + denominator);
        });
        System.out.println("Denominator " + denominator
                + " correctly caused ArithmeticException");
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsDenominatorLongMinValue() {
        int numerator = IntegerMath.randomPrime(128);
        long denominator = Long.MIN_VALUE;
        Throwable t = assertThrows(ArithmeticException.class, () -> {
            Fraction badFraction = new Fraction(numerator, denominator);
            System.out.println("Should not have been able to create "
                    + badFraction + " with denominator " + denominator);
        });
        System.out.println("Denominator " + denominator
                + " correctly caused ArithmeticException");
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}
