package measures.imperial;

import fractions.Fraction;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FootTest {

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
        Foot instance = new Foot(fraction);
        String expected = "foot";
        String actual = instance.getSingularWord();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPluralWord() {
        System.out.println("getPluralWord");
        Fraction fraction = makeFraction();
        Foot instance = new Foot(fraction);
        String expected = "feet";
        String actual = instance.getPluralWord();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAbbreviation() {
        System.out.println("getAbbreviation");
        Fraction fraction = makeFraction();
        Foot instance = new Foot(fraction);
        String expected = "ft";
        String actual = instance.getAbbreviation();
        assertEquals(expected, actual);
    }

}