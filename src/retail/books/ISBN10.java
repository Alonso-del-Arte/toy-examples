package retail.books;

public class ISBN10 extends ISBN {

    static byte checkDigit(int num) {
        int sum = 0;
        int curr = num;
        for (int i = 9; i > 0; i--) {
            int digit = curr % 10;
            int weighted = digit * i;
            sum += weighted;
            curr /= 10;
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
        super(-1, "?");
    }

}
