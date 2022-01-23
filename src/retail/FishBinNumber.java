package retail;

/**
 * Represents a fictional ISBN (FISBN or Fish Bin number). A FISBN is a lot like
 * an ISBN-13, except that 555 is inserted after the initial 978. Therefore, the
 * check digit of a FISBN is guaranteed to be different from the check digit of
 * the corresponding ISBN-13. For example, compare the FISBN
 * 978-555-0-32177-409-0, which can be for whatever fictional book you want it
 * to be, to the ISBN-13 978-0-32177-409-5, which is for the real book <i>Scala
 * for the Impatient</i>, 1<sup>st</sup> Edition, by Cay Horstmann.
 * @author Alonso del Arte
 */
public class FishBinNumber extends BarcodeNumberWithCheckDigit {

    public static final long serialVersionUID = 4553372319738313728L;

    private static long validateNumber(long num) {
        if (num < 0 || num > 9_99999_999) {
            String excMsg = "Number " + num
                    + " is outside the range 0-00000-000 to 9-99999-999";
            throw new IllegalArgumentException(excMsg);
        }
        return 978_555_0_00000_000L + num;
    }

    /**
     * Provides the FISBN formatted with dashes for human readability.
     * @return The number formatted with dashes. For example, if this instance
     * was constructed with 32177409, this function would return
     * "978-555-0-32177-409-0".
     */
    @Override
    public String toString() {
        String digits = Long.toString(this.digits) + this.checkDigit;
        StringBuilder intermediate = new StringBuilder(digits);
        intermediate.insert(15, '-');
        intermediate.insert(12, '-');
        intermediate.insert(7, '-');
        intermediate.insert(6, '-');
        intermediate.insert(3, '-');
        return intermediate.toString();
    }

    /**
     * Constructor. Automatically fills in the 978-555 prefix and the check
     * digit.
     * @param num The number for the Fish Bin Number. Omit the 978-555 prefix
     *            and omit the check digit.
     * @throws IllegalArgumentException If <code>num</code> is negative or in
     * excess of 999999999.
     */
    public FishBinNumber(int num) {
        super(validateNumber(num));
    }

}
