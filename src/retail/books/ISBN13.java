package retail.books;

public class ISBN13 extends ISBN {

    public static final short ORIGINAL_ISBN_13_PREFIX = 978;

    public static final short SECOND_ISBN_13_PREFIX = 979;

    ISBN13(short prefix, int registrationGroup, int registrant, int publication,
           byte checkDigit) {
        super(-1);
    }

}
