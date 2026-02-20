package retail.books;

public class ISBN10 extends ISBN {

    // TODO: Write tests for this
    static byte checkDigit(int num) {
        return 12;
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
