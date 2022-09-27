package measures.imperial;

import fractions.Fraction;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class FootTest {

    @Test
    public void testGetSingularWord() {
        System.out.println("getSingularWord");
        Fraction fraction = ExtendedRandom.nextFraction();
        Foot instance = new Foot(fraction);
        String expected = "foot";
        String actual = instance.getSingularWord();
        assertEquals(expected, actual);
    }

    @Test
    void testToStringSingularNegative() {
        Fraction negOne = new Fraction(-1);
        Foot negOneFoot = new Foot(negOne);
        String expected = "-1 foot";
        String actual = negOneFoot.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPluralWord() {
        System.out.println("getPluralWord");
        Fraction fraction = ExtendedRandom.nextFraction();
        Foot instance = new Foot(fraction);
        String expected = "feet";
        String actual = instance.getPluralWord();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAbbreviation() {
        System.out.println("getAbbreviation");
        Fraction fraction = ExtendedRandom.nextFraction();
        Foot instance = new Foot(fraction);
        String expected = "ft";
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
            Foot foot = new Foot(n);
            String expected = n + " feet";
            String actual = foot.toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testToStringSingular() {
        Fraction one = new Fraction(1);
        Foot oneFoot = new Foot(one);
        String expected = "1 foot";
        String actual = oneFoot.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testReferentialEquality() {
        Foot someFoot = new Foot(ExtendedRandom.nextFraction());
        assertEquals(someFoot, someFoot);
    }

    @Test
    void testNotEqualsNull() {
        Foot someFoot = new Foot(ExtendedRandom.nextFraction());
        assertNotEquals(someFoot, null);
    }

    @Test
    void testNotEqualsDiffClass() {
        Fraction fraction = ExtendedRandom.nextFraction();
        Foot someFoot = new Foot(fraction);
        LengthMeasure someLength
                = new LengthMeasureTest.LengthMeasureImpl(fraction);
        assertNotEquals(someFoot, someLength);
    }

    @Test
    void testNotEqualsDiffMeasure() {
        Fraction fractionA = ExtendedRandom.nextFraction();
        Fraction fractionB = fractionA.plus(1);
        Foot feetA = new Foot(fractionA);
        Foot feetB = new Foot(fractionB);
        assertNotEquals(feetA, feetB);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        Fraction fraction = ExtendedRandom.nextFraction();
        Foot someFeet = new Foot(fraction);
        Foot sameFeet = new Foot(fraction);
        assertEquals(someFeet, sameFeet);
    }

    @Test
    void testHashCode() {
        System.out.println("hashCode");
        int capacity = ExtendedRandom.nextInt(1024) + 16;
        Set<Foot> footSet = new HashSet<>(capacity);
        Set<Integer> hashes = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            Foot foot = new Foot(ExtendedRandom.nextFraction());
            footSet.add(foot);
            hashes.add(foot.hashCode());
        }
        int expected = footSet.size();
        int actual = hashes.size();
        System.out.println("Created " + expected + " Foot instances with "
                + actual + " distinct hash codes");
        String msg = expected
                + " Foot instances should have as many distinct hash codes";
        assertEquals(expected, actual, msg);
    }

    @Test
    void testConstructorRejectsNullFraction() {
        Throwable t = assertThrows(NullPointerException.class, () -> {
            Foot badFoot = new Foot(null);
            System.out.println("Created " + badFoot + " with null number");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}