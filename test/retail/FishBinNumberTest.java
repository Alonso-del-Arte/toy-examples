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
    void testConstructorRejectsNegativeNumber() {
        int badNum = -RANDOM.nextInt(Integer.MAX_VALUE) - 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            FishBinNumber badFishBin = new FishBinNumber(badNum);
            System.out.println("Should not have been able to create "
                    + badFishBin + " with negative parameter " + badNum);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsExcessiveNumber() {
        int badNum = 10_00000_000 + RANDOM.nextInt(Short.MAX_VALUE);
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            FishBinNumber badFishBin = new FishBinNumber(badNum);
            System.out.println("Should not have been able to create "
                    + badFishBin + " with excessive parameter " + badNum);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}
