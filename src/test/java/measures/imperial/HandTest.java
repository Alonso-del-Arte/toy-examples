package measures.imperial;

import fractions.Fraction;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class HandTest {

    @Test
    public void testGetSingularWord() {
        System.out.println("getSingularWord");
        Fraction fraction = ExtendedRandom.nextFraction();
        Hand instance = new Hand(fraction);
        String expected = "hand";
        String actual = instance.getSingularWord();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPluralWord() {
        System.out.println("getPluralWord");
        Fraction fraction = ExtendedRandom.nextFraction();
        Hand instance = new Hand(fraction);
        String expected = "hands";
        String actual = instance.getPluralWord();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAbbreviation() {
        System.out.println("getAbbreviation");
        Fraction fraction = ExtendedRandom.nextFraction();
        Hand instance = new Hand(fraction);
        fail("Not sure what the proper abbreviation is");
        String expected = "??? h ?????";
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
            Hand hand = new Hand(n);
            String expected = n + " hands";
            String actual = hand.toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testToStringSingular() {
        Fraction one = new Fraction(1);
        Hand oneHand = new Hand(one);
        String expected = "1 hand";
        String actual = oneHand.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testToStringSingularNegative() {
        Fraction negOne = new Fraction(-1);
        Hand negOneHand = new Hand(negOne);
        String expected = "-1 hand";
        String actual = negOneHand.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testReferentialEquality() {
        Hand someHand = new Hand(ExtendedRandom.nextFraction());
        assertEquals(someHand, someHand);
    }

    @Test
    void testNotEqualsNull() {
        Hand someHand = new Hand(ExtendedRandom.nextFraction());
        assertNotEquals(someHand, null);
    }

    @Test
    void testNotEqualsDiffClass() {
        Fraction fraction = ExtendedRandom.nextFraction();
        Hand someHand = new Hand(fraction);
        LengthMeasure someLength
                = new LengthMeasureTest.LengthMeasureImpl(fraction);
        assertNotEquals(someHand, someLength);
    }

    @Test
    void testNotEqualsDiffMeasure() {
        Fraction fractionA = ExtendedRandom.nextFraction();
        Fraction fractionB = fractionA.plus(1);
        Hand handsA = new Hand(fractionA);
        Hand handsB = new Hand(fractionB);
        assertNotEquals(handsA, handsB);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        Fraction fraction = ExtendedRandom.nextFraction();
        Hand someHands = new Hand(fraction);
        Hand sameHands = new Hand(fraction);
        assertEquals(someHands, sameHands);
    }

    @Test
    void testHashCode() {
        System.out.println("hashCode");
        int capacity = ExtendedRandom.nextInt(1024) + 16;
        Set<Hand> handSet = new HashSet<>(capacity);
        Set<Integer> hashes = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            Hand hand = new Hand(ExtendedRandom.nextFraction());
            handSet.add(hand);
            hashes.add(hand.hashCode());
        }
        int expected = handSet.size();
        int actual = hashes.size();
        System.out.println("Created " + expected + " Hand instances with "
                + actual + " distinct hash codes");
        String msg = expected
                + " Hand instances should have as many distinct hash codes";
        assertEquals(expected, actual, msg);
    }

    @Test
    void testConstructorRejectsNullFraction() {
        Throwable t = assertThrows(NullPointerException.class, () -> {
            Hand badHand = new Hand(null);
            System.out.println("Created " + badHand + " with null number");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}
