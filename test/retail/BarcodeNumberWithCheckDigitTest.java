package retail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarcodeNumberWithCheckDigitTest {

    @Test
    void testCalculateCheckDigit() {
        System.out.println("calculateCheckDigit");
        byte[] expected = {0, 7, 4, 1, 8, 5, 2, 9, 6, 3};
        int len = expected.length;
        byte[] actual = new byte[len];
        for (byte b = 0; b < 10; b++) {
            actual[b] = BarcodeNumberWithCheckDigit.calculateCheckDigit(b);
        }
        assertArrayEquals(expected, actual);
    }

}
