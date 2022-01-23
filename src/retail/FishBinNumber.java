package retail;

/**
 * Represents a fictional ISBN (FISBN or Fish Bin number).
 */
public class FishBinNumber extends BarcodeNumberWithCheckDigit {

    public static final long serialVersionUID = 4553372319738313728L;

    private static long validateNumber(long num) {
//        if (num < 0 || num > 9_99999_999) {
//            String excMsg = "Number " + num
//                    + " is outside the range 0-00000-000 to 9-99999-999";
//            throw new IllegalArgumentException(excMsg);
//        }
        return 978_555_0_00000_000L + num;
    }

    public FishBinNumber(long num) {
        super(validateNumber(num));
    }

}
