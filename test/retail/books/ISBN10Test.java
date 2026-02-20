package retail.books;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static retail.books.ISBNTest.RANDOM;

class ISBN10Test {

    private static final int MAX_ISBN_10_NO_CHECK_DIGIT = 987654321;

    private static byte reckonCheckDigit(int digits) {
        int sum = 0;
        int curr = digits;
        for (int i = 9; i > 0; i--) {
            int digit = curr % 10;
            int weighted = digit * i;
            sum += weighted;
            curr /= 10;
        }
        return (byte) (sum % 11);
    }

    private static int chooseDigits(byte checkDigit) {
        int propNum = RANDOM.nextInt(MAX_ISBN_10_NO_CHECK_DIGIT);
        while (reckonCheckDigit(propNum) != checkDigit) {
            propNum++;
        }
        return propNum;
    }

    @Test
    void testCheckDigitZero() {
        byte expected = 0;
        int num = chooseDigits(expected);
        byte actual = ISBN10.checkDigit(num);
        String message = "Getting check digit for " + num;
        assertEquals(expected, actual, message);
    }

    @Test
    void testCheckDigitOne() {
        byte expected = 1;
        int num = chooseDigits(expected);
        byte actual = ISBN10.checkDigit(num);
        String message = "Getting check digit for " + num;
        assertEquals(expected, actual, message);
    }

    @Test
    void testCheckDigitTwo() {
        byte expected = 2;
        int num = chooseDigits(expected);
        byte actual = ISBN10.checkDigit(num);
        String message = "Getting check digit for " + num;
        assertEquals(expected, actual, message);
    }

    @Test
    void testCheckDigitThree() {
        byte expected = 3;
        int num = chooseDigits(expected);
        byte actual = ISBN10.checkDigit(num);
        String message = "Getting check digit for " + num;
        assertEquals(expected, actual, message);
    }

}
