package retail;

import java.math.BigInteger;
import java.util.Random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of the BarcodeNumberWithCheckDigit class.
 * @author Alonso del Arte
 */
class BarcodeNumberWithCheckDigitTest {

    public static final Random RANDOM = new Random();

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

    @Test
    void testCalculateCheckDigitForRandomNumber() {
        final int withoutCheckDigit = RANDOM.nextInt(Short.MAX_VALUE);
        int num = withoutCheckDigit;
        boolean needsTripling = true;
        long sum = 0;
        long digit;
        while (num > 0) {
            digit = num % 10;
            if (needsTripling) digit *= 3;
            sum += digit;
            needsTripling = !needsTripling;
            num /= 10;
        }
        sum *= -1;
        sum %= 10;
        if (sum < 0) sum += 10;
        byte expected = (byte) sum;
        byte actual = BarcodeNumberWithCheckDigit
                .calculateCheckDigit(withoutCheckDigit);
        String msg = "Expecting check digit " + expected + " for number "
                + withoutCheckDigit;
        assertEquals(expected, actual, msg);
    }

    @Test
    void testGetCheckDigit() {
        System.out.println("getCheckDigit");
        int num = RANDOM.nextInt(Short.MAX_VALUE);
        BarcodeNumberWithCheckDigit barcodeNumber
                = new BarcodeNumberWithCheckDigitImpl(num);
        byte expected = BarcodeNumberWithCheckDigit.calculateCheckDigit(num);
        byte actual = barcodeNumber.getCheckDigit();
        String msg = "Expecting check digit " + expected + " for " + num;
        assertEquals(expected, actual, msg);
    }

    @Test
    void testToString() {
        System.out.println("toString");
        int num = RANDOM.nextInt(Short.MAX_VALUE);
        BarcodeNumberWithCheckDigit barcodeNumber
                = new BarcodeNumberWithCheckDigitImpl(num);
        String expected = num + "-" + barcodeNumber.getCheckDigit();
        String actual = barcodeNumber.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testReferentialEquality() {
        int num = RANDOM.nextInt(Short.MAX_VALUE);
        BarcodeNumberWithCheckDigit someNumber
                = new BarcodeNumberWithCheckDigitImpl(num);
        assertEquals(someNumber, someNumber);
    }

    @Test
    void testNotEqualsNull() {
        int num = RANDOM.nextInt(Short.MAX_VALUE);
        BarcodeNumberWithCheckDigit someNumber
                = new BarcodeNumberWithCheckDigitImpl(num);
        assertNotEquals(someNumber, null);
    }

    @Test
    void testNotEqualsUnrelatedClass() {
        int num = RANDOM.nextInt(Short.MAX_VALUE);
        BarcodeNumberWithCheckDigit barcodeNumber
                = new BarcodeNumberWithCheckDigitImpl(num);
        BigInteger integer = BigInteger.valueOf(num);
        String msg = barcodeNumber + " should not equal BigInteger " + integer;
        assert !barcodeNumber.equals(integer) : msg;
    }

    private static class BarcodeNumberWithCheckDigitImpl
            extends BarcodeNumberWithCheckDigit {

        public static final long serialVersionUID = 4553372319738315776L;

        BarcodeNumberWithCheckDigitImpl(long num) {
            super(num);
        }

    }

}
