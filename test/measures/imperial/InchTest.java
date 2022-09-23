package measures.imperial;

import fractions.Fraction;

import java.util.Random;

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