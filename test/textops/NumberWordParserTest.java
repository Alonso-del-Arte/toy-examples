package textops;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests of the NumberWordParser class. For now, all these tests pertain to
 * parsing English number words.
 * @author Alonso del Arte
 */
class NumberWordParserTest {

    @Test
    void testInterpretZeroToNine() {
        String[] numberWords = {"zero", "one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine"};
        for (int expected = 0; expected < 10; expected++) {
            int actual = NumberWordParser.interpret(numberWords[expected]);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testInterpretTenAndTeens() {
        String[] numberWords = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
                "sixteen", "seventeen", "eighteen", "nineteen"};
        for (int expected = 10; expected < 20; expected++) {
            int actual = NumberWordParser.interpret(numberWords[expected - 10]);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testInterpretTwentyThroughNinety() {
        String[] numberWords = {"twenty", "thirty", "forty", "fifty", "sixty",
                "seventy", "eighty", "ninety"};
        for (int expected = 20; expected < 100; expected += 10) {
            int actual = NumberWordParser.interpret(numberWords[expected / 10 - 2]);
            assertEquals(expected, actual);
        }
    }

}
