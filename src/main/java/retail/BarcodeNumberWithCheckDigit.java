package retail;

import java.io.Serializable;

/**
 * Represents a number for a barcode that has a check digit appended at the end.
 * Examples of such numbers include UPCs (such as GTIN-12 and GTIN-13) and ISBNs
 * (such as ISBN-10 and ISBN-13). This abstract class provides check digit
 * calculation but no validation of the rest of the number. That's up to the
 * subclasses.
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        return this.digits == ((BarcodeNumberWithCheckDigit) obj).digits;
    }

    @Override
    public int hashCode() {
        int hash = this.getClass().getSimpleName().hashCode();
        hash += this.digits;
        return (this.checkDigit + 1) * hash;
    }

    BarcodeNumberWithCheckDigit(long num) {
        this.digits = num;
        this.checkDigit = calculateCheckDigit(num);
    }

}
