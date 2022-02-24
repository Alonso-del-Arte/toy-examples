package fractions;

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
        fail("Haven't written test yet");
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
    void testConstructorRejectsDenominatorZero() {
        fail("Haven't written test yet");
    }

    @Test
    void testConstructorRejectsDenominatorLongMinValue() {
        fail("Haven't written test yet");
    }

}
