package retail.books;

public class ISBN10 extends ISBN {

    /**
     * Reckons the check digit according to the ISBN-10 formula. Note that this
     * formula is different from the ISBN-13 formula.
     * @param num The number without the check digit. Ought to be at least
     *            0-000-00000 and at most 99999-99-99, but this is not checked
     *            and behavior for inputs outside that range is not documented.
     *            For the example, we'll use 93-081-4885.
     * @return The check digit. Provided {@code num} is in the appropriate
     * range, this function will return at least 0 and at most 10, which is
     * represented X. In the example, this would be 10. Thus the correctly
     * formatted ISBN-10 would be, assuming I have placed the dashes correctly,
     * 93-081-4885-X, which appears to be an unassigned ISBN as of February 19,
     * 2026. If that number is indeed unassigned, it may have already been
     * purchased long ago, but most likely it would be assigned ISBN-13
     * 978-93-081-4885-0 upon registration.
     */
    static byte checkDigit(int num) {
        int sum = 0;
        for (int i = 9; i > 0; i--) {
            int weighted = (num % 10) * i;
            sum += weighted;
            num /= 10;
        }
        return (byte) (sum % 11);
    }

    // TODO: Write tests for this
    static boolean isValid(String s) {
        return false;
    }

    // TODO: Write tests for this
    ISBN10(int registrationGroup, int registrant, int publication,
           byte checkDigit) {
        super(-1, (byte) 12, "?");
    }

}
