package measures.imperial;

import fractions.Fraction;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class YardTest {

    @Test
    public void testGetSingularWord() {
        System.out.println("getSingularWord");
        Fraction fraction = ExtendedRandom.nextFraction();
        Yard instance = new Yard(fraction);
        String expected = "yard";
        String actual = instance.getSingularWord();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPluralWord() {
        System.out.println("getPluralWord");
        Fraction fraction = ExtendedRandom.nextFraction();
        Yard instance = new Yard(fraction);
        String expected = "yards";
        String actual = instance.getPluralWord();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAbbreviation() {
        System.out.println("getAbbreviation");
        Fraction fraction = ExtendedRandom.nextFraction();
        Yard instance = new Yard(fraction);
        String expected = "yd";
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
            Yard yard = new Yard(n);
            String expected = n + " yards";
            String actual = yard.toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testToStringSingular() {
        Fraction one = new Fraction(1);
        Yard oneYard = new Yard(one);
        String expected = "1 yard";
        String actual = oneYard.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testReferentialEquality() {
        Yard someYard = new Yard(ExtendedRandom.nextFraction());
        assertEquals(someYard, someYard);
    }

    @Test
    void testNotEqualsNull() {
        Yard someYard = new Yard(ExtendedRandom.nextFraction());
        assertNotEquals(someYard, null);
    }

    @Test
    void testNotEqualsDiffClass() {
        Fraction fraction = ExtendedRandom.nextFraction();
        Yard someYard = new Yard(fraction);
        LengthMeasure someLength
                = new LengthMeasureTest.LengthMeasureImpl(fraction);
        assertNotEquals(someYard, someLength);
    }

    @Test
    void testNotEqualsDiffMeasure() {
        Fraction fractionA = ExtendedRandom.nextFraction();
        Fraction fractionB = fractionA.plus(1);
        Yard yardsA = new Yard(fractionA);
        Yard yardsB = new Yard(fractionB);
        assertNotEquals(yardsA, yardsB);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        Fraction fraction = ExtendedRandom.nextFraction();
        Yard someYards = new Yard(fraction);
        Yard sameYards = new Yard(fraction);
        assertEquals(someYards, sameYards);
    }

    @Test
    void testHashCode() {
        System.out.println("hashCode");
        int capacity = ExtendedRandom.nextInt(1024) + 16;
        Set<Yard> yardSet = new HashSet<>(capacity);
        Set<Integer> hashes = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            Yard yard = new Yard(ExtendedRandom.nextFraction());
            yardSet.add(yard);
            hashes.add(yard.hashCode());
        }
        int expected = yardSet.size();
        int actual = hashes.size();
        System.out.println("Created " + expected + " Yard instances with "
                + actual + " distinct hash codes");
        String msg = expected
                + " Yard instances should have as many distinct hash codes";
        assertEquals(expected, actual, msg);
    }

    @Test
    void testConstructorRejectsNullFraction() {
        Throwable t = assertThrows(NullPointerException.class, () -> {
            Yard badYard = new Yard(null);
            System.out.println("Created " + badYard + " with null number");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}