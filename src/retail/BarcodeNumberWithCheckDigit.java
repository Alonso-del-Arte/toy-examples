package retail;

import java.io.Serializable;

/**
 * Represents a number for a barcode that has a check digit appended at the end.
 * Examples of such numbers include UPCs (such as GTIN-12 and GTIN-13) and ISBNs
 * (such as ISBN-10 and ISBN-13).
 * @author Alonso del Arte
 */
abstract class BarcodeNumberWithCheckDigit implements Serializable {

    public static final long serialVersionUID = 4553372319738301440L;

    final long digits;

    final byte checkDigit;

    static byte calculateCheckDigit(long num) {
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
        return (byte) sum;
    }

    public byte getCheckDigit() {
        return this.checkDigit;
    }

    @Override
    public String toString() {
        return this.digits + "-" + this.checkDigit;
    }

    // TODO: Write toBarcodeString()

    // TODO: Write tests for this
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    // TODO: Write tests for this
    @Override
    public int hashCode() {
        return 0;
    }

    BarcodeNumberWithCheckDigit(long num) {
        this.digits = num;
        this.checkDigit = calculateCheckDigit(num);
    }

}
