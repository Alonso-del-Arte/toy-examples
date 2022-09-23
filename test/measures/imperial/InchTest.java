package measures.imperial;

import fractions.Fraction;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InchTest {

    private static final Random RANDOM = new Random();

    private static Fraction makeFraction() {
        int numer = RANDOM.nextInt(768) - 384;
        int denom = RANDOM.nextInt(5280) + 10;
        return new Fraction(numer, denom);
    }

    @Test
    public void testGetSingularWord() {
        System.out.println("getSingularWord");
        Fraction fraction = makeFraction();
        Inch instance = new Inch(fraction);
        String expected = "inch";
        String actual = instance.getSingularWord();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPluralWord() {
        System.out.println("getPluralWord");
        Fraction fraction = makeFraction();
        Inch instance = new Inch(fraction);
        String expected = "inches";
        String actual = instance.getPluralWord();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAbbreviation() {
        System.out.println("getAbbreviation");
        Fraction fraction = makeFraction();
        Inch instance = new Inch(fraction);
        String expected = "in";
        String actual = instance.getAbbreviation();
        assertEquals(expected, actual);
    }

}