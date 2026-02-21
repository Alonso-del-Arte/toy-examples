package retail.books;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static retail.books.ISBNTest.RANDOM;

class ISBN13Test {

    private static final long MIN_ISBN_13_NO_CHECK_DIGIT = 978000000000L;

    private static final long MAX_ISBN_13_NO_CHECK_DIGIT = 979987654321L;

    private static byte reckonCheckDigit(long digits) {
        int sum = 0;
        long curr = digits;
        for (int i = 12; i > 0; i--) {
            long digit = curr % 10;
            int weight = (i % 2 == 0) ? 3 : 1;
            long weighted = digit * weight;
            sum += weighted;
            curr /= 10;
        }
        int remainder = sum % 10;
        if (remainder == 0) {
            return 0;
        }
        return (byte) (10 - remainder);
    }

    private static long chooseDigits(byte checkDigit) {
        long propNum = RANDOM.nextLong(MIN_ISBN_13_NO_CHECK_DIGIT,
                MAX_ISBN_13_NO_CHECK_DIGIT);
        while (reckonCheckDigit(propNum) != checkDigit) {
            propNum++;
        }
        return propNum;
    }

    @Test
    void testOriginalPrefixConstant() {
        short expected = 978;
        short actual = ISBN13.ORIGINAL_ISBN_13_PREFIX;
        assertEquals(expected, actual);
    }

    @Test
    void testSecondPrefixConstant() {
        short expected = 979;
        short actual = ISBN13.SECOND_ISBN_13_PREFIX;
        assertEquals(expected, actual);
    }

}
