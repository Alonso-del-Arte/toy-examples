package retail.books;

public class ISBN13 extends ISBN {

    public static final short ORIGINAL_ISBN_13_PREFIX = 978;

    public static final short SECOND_ISBN_13_PREFIX = 979;

    static byte checkDigit(long num) {
        int sum = 0;
        int weight = 3;
        while (num > 0) {
            long digit = num % 10;
            long weighted = digit * weight;
            sum += weighted;
            if (weight == 3) {
                weight = 1;
            } else {
                weight = 3;
            }
            num /= 10;
        }
        int remainder = sum % 10;
        if (remainder == 0) {
            return 0;
        }
        return (byte) (10 - remainder);
    }

    // TODO: Write tests for this
    static boolean isValid(String s) {
        return false;
    }

    // TODO: Write tests for this
    ISBN13(short prefix, int registrationGroup, int registrant, int publication,
           byte checkDigit) {
        super(-1, "?");
    }

}
