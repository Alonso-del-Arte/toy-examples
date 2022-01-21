package retail;

import java.io.Serializable;

public abstract class BarcodeNumberWithCheckDigit implements Serializable {

    public static final long serialVersionUID = 4553372319738301440L;

    final long digits;

    final byte checkDigit;

    static byte calculateCheckDigit(long num) {
        switch ((int) (num % 10)) {//0, 7, 4, 1, 8, 5, 2, 9, 6, 3
            case 0: return 0;
            case 1: return 7;
            case 2: return 4;
            case 3: return 1;
            case 4: return 8;
            case 5: return 5;
            case 6: return 2;
            case 7: return 9;
            case 8: return 6;
            case 9: return 3;
            default: return -1;
        }
    }

    BarcodeNumberWithCheckDigit(long num) {
        this.digits = num;
        this.checkDigit = calculateCheckDigit(num);
    }

}
