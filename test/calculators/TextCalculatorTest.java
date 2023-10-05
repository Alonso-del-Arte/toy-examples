package calculators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class TextCalculatorTest {

    private final static String[] ADJECTIVES = {"Associated", "Ballistic",
            "Consolidated", "Diversified", "Equestrian", "Federal", "Global",
            "Historical", "International", "Local", "National", "State"};

    private static final String[] SINGULAR_NOUNS = {"Academy", "Bureau",
            "Chamber", "Department"};

    public static final String[] PLURAL_NOUNS = {"Armadillos", "Bullfrogs",
            "Clerks", "Investigations", "Jokes", "Kangaroos", "Llamas"};

    @Test
    void testIsHighSurrogate() {
        System.out.println("isHighSurrogate");
        int span = 0xDC00 - 0xD800;
        char ch = (char) (0xD800 + ExtendedRandom.nextInt(span));
        String msg = "Character " + Character.getName(ch)
                + " should be found to be a high surrogate";
        assert TextCalculator.isHighSurrogate(ch) : msg;
    }

    @Test
    void testIsNotHighSurrogatePrecede0xD800() {
        char ch = (char) (ExtendedRandom.nextInt(0xD800));
        String msg = "Character " + Character.getName(ch)
                + " should not be found to be a high surrogate";
        assert !TextCalculator.isHighSurrogate(ch) : msg;
    }

    @Test
    void testLowSurrogateIsNotHighSurrogate() {
        int span = 0xE000 - 0xDC00;
        char ch = (char) (0xDC00 + ExtendedRandom.nextInt(span));
        String msg = "Character " + Character.getName(ch)
                + " should not be found to be a high surrogate";
        assert !TextCalculator.isHighSurrogate(ch) : msg;
    }

    @Test
    void testIsNotHighSurrogateFollow0xDFFF() {
        int span = 0x10000 - 0xE000;
        char ch = (char) (0xE000 + ExtendedRandom.nextInt(span));
        String msg = "Character " + Character.getName(ch)
                + " should not be found to be a high surrogate";
        assert !TextCalculator.isHighSurrogate(ch) : msg;
    }

}
