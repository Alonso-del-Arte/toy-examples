package retail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static retail.BarcodeNumberWithCheckDigitTest.RANDOM;

class ISBNTest {

    @Test
    void testToString() {
        System.out.println("toString");
        final long num = 978_0_00000_000L + RANDOM.nextInt(10_00000_000);
        ISBN isbn = new ISBN(num);
        StringBuilder intermediate = new StringBuilder(Long.toString(num));
        intermediate.insert(9, '-');
        intermediate.insert(4, '-');
        intermediate.insert(3, '-');
        String expected = intermediate.toString() + '-' + isbn.getCheckDigit();
        String actual = isbn.toString();
        assertEquals(expected, actual);
    }

}
