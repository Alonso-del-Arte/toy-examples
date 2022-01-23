package retail;

// TODO: Make this class support both ISBN-10 and ISBN-13, with appropriate
//  validation for both
/**
 * Represents an International Standard Book Number (ISBN). None of the
 * constructors take a check digit.
 * @author Alonso del Arte
 */
public class ISBN extends BarcodeNumberWithCheckDigit {

    public static final long serialVersionUID = 4553372319738311680L;

    // TODO: Write tests for this
    public static ISBN parseISBN(String s) {
        return new ISBN(978_0_00000_000L);
    }

    @Override
    public String toString() {
        String digits = Long.toString(this.digits) + this.checkDigit;
        StringBuilder intermediate = new StringBuilder(digits);
        intermediate.insert(12, '-');
        intermediate.insert(9, '-');
        intermediate.insert(4, '-');
        intermediate.insert(3, '-');
        return intermediate.toString();
    }

    /**
     * Primary constructor.
     * @param num The number for the ISBN, <em>without</em> the check digit. For
     *            example, for <i>Scala for the Impatient</i> by Cay Horstmann,
     *            1<sup>st</sup> Edition, ISBN 978-0-32177-409-5, this would be
     *            978032177409. The constructor calculates the check digit, 5 in
     *            this example.
     */
    public ISBN(long num) {
        super(num);
    }

}
