package retail;

import java.io.Serializable;

public abstract class BarcodeNumberWithCheckDigit implements Serializable {

    public static final long serialVersionUID = 4553372319738301440L;

    final long digits;

    final byte checkDigit;

    // TODO: Write tests for this
    static byte calculateCheckDigit(long num) {
        return 0;
    }

    BarcodeNumberWithCheckDigit(long num) {
        this.digits = num;
        this.checkDigit = calculateCheckDigit(num);
    }

}
