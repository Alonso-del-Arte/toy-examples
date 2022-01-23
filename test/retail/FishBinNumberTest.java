package retail;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static retail.BarcodeNumberWithCheckDigitTest.RANDOM;

/**
 * Tests of the FishBinNumber class.
 * @author Alonso del Arte
 */
class FishBinNumberTest {

    @Test
    void testToString() {
        System.out.println("toString");
        final int num = RANDOM.nextInt(10_00000_000);
        FishBinNumber fishBin = new FishBinNumber(num);
        StringBuilder intermediate = new StringBuilder(Integer.toString(num));
        intermediate.insert(6, '-');
        intermediate.insert(1, '-');
        String expected = "978-555-" + intermediate + '-'
                + fishBin.getCheckDigit();
        String actual = fishBin.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testISBNAndFishBinCheckDigitsDiffer() {
        long prefix = 978_00000_000L;
        int someDigits = 59327_000;
        ISBN isbn;
        FishBinNumber fishBin;
        long num;
        for (int i = 0; i < 1000; i++) {
            num = someDigits + i;
            fishBin = new FishBinNumber(num);
            num += prefix;
            isbn = new ISBN(num);
            assertNotEquals(fishBin.getCheckDigit(), isbn.getCheckDigit());
        }
    }

}
