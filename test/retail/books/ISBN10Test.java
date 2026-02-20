package retail.books;

import static org.junit.jupiter.api.Assertions.*;

import static retail.books.ISBNTest.RANDOM;

class ISBN10Test {

    private static byte reckonCheckDigit(int digits) {
        int sum = 0;
        int curr = digits;
        for (int i = 9; i > 0; i--) {
            int digit = curr % 10;
            int weighted = digit * i;
            sum += weighted;
            curr /= 10;
        }
        return (byte) sum;
    }

//    private static long chooseDigits(byte checkDigit)

}
