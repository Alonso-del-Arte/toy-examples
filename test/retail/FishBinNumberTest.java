package retail;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static retail.BarcodeNumberWithCheckDigitTest.RANDOM;

/**
 * Tests of the FishBinNumber class.
 * @author Alonso del Arte
 */
class FishBinNumberTest {

    @Test
    void testToString() {//978_555_0_00000_000L
        System.out.println("toString");
        final int num = RANDOM.nextInt(10_00000_000);
        FishBinNumber fishBin = new FishBinNumber(num);
        StringBuilder intermediate = new StringBuilder(Integer.toString(num));
        intermediate.insert(6, '-');
        intermediate.insert(1, '-');
        String expected = "978-555-" + intermediate.toString() + '-'
                + fishBin.getCheckDigit();
        String actual = fishBin.toString();
        assertEquals(expected, actual);
    }

}
