package measures.imperial;

import fractions.Fraction;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class InchTest {

    @Test
    public void testGetSingularWord() {
        System.out.println("getSingularWord");
        Fraction fraction = ExtendedRandom.nextFraction();
        Inch instance = new Inch(fraction);
        String expected = "inch";
        String actual = instance.getSingularWord();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPluralWord() {
        System.out.println("getPluralWord");
        Fraction fraction = ExtendedRandom.nextFraction();
        Inch instance = new Inch(fraction);
        String expected = "inches";
        String actual = instance.getPluralWord();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAbbreviation() {
        System.out.println("getAbbreviation");
        Fraction fraction = ExtendedRandom.nextFraction();
        Inch instance = new Inch(fraction);
        String expected = "in";
        String actual = instance.getAbbreviation();
        assertEquals(expected, actual);
    }

    @Test
    void testToString() {
        System.out.println("toString");
        Fraction two = new Fraction(2);
        Fraction stop = new Fraction(129);
        for (Fraction n = new Fraction(-128); n.compareTo(stop) < 0;
             n = n.plus(two)) {
            Inch inch = new Inch(n);
            String expected = n + " inches";
            String actual = inch.toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testToStringSingular() {
        Fraction one = new Fraction(1);
        Inch oneInch = new Inch(one);
        String expected = "1 inch";
        String actual = oneInch.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testReferentialEquality() {
        Inch someInch = new Inch(ExtendedRandom.nextFraction());
        assertEquals(someInch, someInch);
    }

    @Test
    void testNotEqualsNull() {
        Inch someInch = new Inch(ExtendedRandom.nextFraction());
        assertNotEquals(someInch, null);
    }

    @Test
    void testNotEqualsDiffClass() {
        Fraction fraction = ExtendedRandom.nextFraction();
        Inch someInch = new Inch(fraction);
        LengthMeasure someLength
                = new LengthMeasureTest.LengthMeasureImpl(fraction);
        assertNotEquals(someInch, someLength);
    }

    @Test
    void testNotEqualsDiffMeasure() {
        Fraction fractionA = ExtendedRandom.nextFraction();
        Fraction fractionB = fractionA.plus(1);
        Inch inchesA = new Inch(fractionA);
        Inch inchesB = new Inch(fractionB);
        assertNotEquals(inchesA, inchesB);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        Fraction fraction = ExtendedRandom.nextFraction();
        Inch someInches = new Inch(fraction);
        Inch sameInches = new Inch(fraction);
        assertEquals(someInches, sameInches);
    }

    @Test
    void testHashCode() {
        System.out.println("hashCode");
        int capacity = ExtendedRandom.nextInt(1024) + 16;
        Set<Inch> inchSet = new HashSet<>(capacity);
        Set<Integer> hashes = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            Inch inch = new Inch(ExtendedRandom.nextFraction());
            inchSet.add(inch);
            hashes.add(inch.hashCode());
        }
        int expected = inchSet.size();
        int actual = hashes.size();
        System.out.println("Created " + expected + " Inch instances with "
                + actual + " distinct hash codes");
        String msg = expected
                + " Inch instances should have as many distinct hash codes";
        assertEquals(expected, actual, msg);
    }

    @Test
    void testConstructorRejectsNullFraction() {
        Throwable t = assertThrows(NullPointerException.class, () -> {
            Inch badInch = new Inch(null);
            System.out.println("Created " + badInch + " with null number");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}