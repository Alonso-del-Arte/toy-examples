package measures.imperial;

import fractions.Fraction;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class MileTest {

    @Test
    public void testGetSingularWord() {
        System.out.println("getSingularWord");
        Fraction fraction = ExtendedRandom.nextFraction();
        Mile instance = new Mile(fraction);
        String expected = "mile";
        String actual = instance.getSingularWord();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPluralWord() {
        System.out.println("getPluralWord");
        Fraction fraction = ExtendedRandom.nextFraction();
        Mile instance = new Mile(fraction);
        String expected = "miles";
        String actual = instance.getPluralWord();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAbbreviation() {
        System.out.println("getAbbreviation");
        Fraction fraction = ExtendedRandom.nextFraction();
        Mile instance = new Mile(fraction);
        String expected = "m";
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
            Mile mile = new Mile(n);
            String expected = n + " miles";
            String actual = mile.toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testToStringSingular() {
        Fraction one = new Fraction(1);
        Mile oneMile = new Mile(one);
        String expected = "1 mile";
        String actual = oneMile.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testToStringSingularNegative() {
        Fraction negOne = new Fraction(-1);
        Mile negOneMile = new Mile(negOne);
        String expected = "-1 mile";
        String actual = negOneMile.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testReferentialEquality() {
        Mile someMile = new Mile(ExtendedRandom.nextFraction());
        assertEquals(someMile, someMile);
    }

    @Test
    void testNotEqualsNull() {
        Mile someMile = new Mile(ExtendedRandom.nextFraction());
        assertNotEquals(someMile, null);
    }

    @Test
    void testNotEqualsDiffClass() {
        Fraction fraction = ExtendedRandom.nextFraction();
        Mile someMile = new Mile(fraction);
        LengthMeasure someLength
                = new LengthMeasureTest.LengthMeasureImpl(fraction);
        assertNotEquals(someMile, someLength);
    }

    @Test
    void testNotEqualsDiffMeasure() {
        Fraction fractionA = ExtendedRandom.nextFraction();
        Fraction fractionB = fractionA.plus(1);
        Mile milesA = new Mile(fractionA);
        Mile milesB = new Mile(fractionB);
        assertNotEquals(milesA, milesB);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        Fraction fraction = ExtendedRandom.nextFraction();
        Mile someMiles = new Mile(fraction);
        Mile sameMiles = new Mile(fraction);
        assertEquals(someMiles, sameMiles);
    }

    @Test
    void testHashCode() {
        System.out.println("hashCode");
        int capacity = ExtendedRandom.nextInt(1024) + 16;
        Set<Mile> mileSet = new HashSet<>(capacity);
        Set<Integer> hashes = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            Mile mile = new Mile(ExtendedRandom.nextFraction());
            mileSet.add(mile);
            hashes.add(mile.hashCode());
        }
        int expected = mileSet.size();
        int actual = hashes.size();
        System.out.println("Created " + expected + " Mile instances with "
                + actual + " distinct hash codes");
        String msg = expected
                + " Mile instances should have as many distinct hash codes";
        assertEquals(expected, actual, msg);
    }

    @Test
    void testConstructorRejectsNullFraction() {
        Throwable t = assertThrows(NullPointerException.class, () -> {
            Mile badMile = new Mile(null);
            System.out.println("Created " + badMile + " with null number");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}