package retail.books;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static randomness.ExtendedRandom.alphanumeric;
import static randomness.ExtendedRandom.nextInt;

class CheckDigitExceptionTest {

    public static final int DEFAULT_TEST_MESSAGE_LENGTH = 20;

    @Test
    void testGetMessage3ParamConstructorA() {
        byte badCheckDigit = (byte) nextInt();
        int num = nextInt();
        String expected = alphanumeric(DEFAULT_TEST_MESSAGE_LENGTH);
        Throwable instance = new CheckDigitException(badCheckDigit, num,
                expected);
        String actual = instance.getMessage();
        assertEquals(expected, actual);
    }

}
