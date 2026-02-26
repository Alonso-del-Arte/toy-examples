package retail.books;

/**
 * Represents an International Standard Book Number (ISBN).
 * @author Alonso del Arte
 */
public abstract class ISBN {

    public static final long serialVersionUID = 4553372319738311680L;

    // TODO: Write tests for this
    public static ISBN parseISBN(String s) {
        return new ISBN(978_0_00000_000L, (byte) 11, "0") {};
    }

    @Override
    public String toString() {
        return "intermediate.toString()";
    }

    // TODO: Write tests for this
    ISBN(long num, byte check, String humanReadableForm) {
    }

}
