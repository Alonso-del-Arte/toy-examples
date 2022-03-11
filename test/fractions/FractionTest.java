package fractions;

import calculators.IntegerMath;

import java.util.Random;

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

//    @Test
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
