package retail.books;

/**
 * Represents an International Standard Book Number (ISBN).
 * @author Alonso del Arte
 */
public abstract class ISBN {

    public static final long serialVersionUID = 4553372319738311680L;

    // TODO: Write tests for this
    public static ISBN parseISBN(String s) {
        return new ISBN10(0, 0, 0, (byte) 0);
    }

    public abstract boolean isConvertibleToISBN10();

    public abstract ISBN10 convertToISBN10();

    public abstract ISBN13 convertToISBN13();

    @Override
    public String toString() {
        return "intermediate.toString()";
    }

    ISBN(long num, byte check) {
        // TODO: Write tests for this, such as that this constructor sets human-
        //  readable form as simple Long.toString()
    }

    // TODO: Write tests for this
    ISBN(long num, byte check, String humanReadableForm) {
    }

}
