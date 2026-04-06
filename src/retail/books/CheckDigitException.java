package retail.books;

public class CheckDigitException extends RuntimeException {

    // TODO: Write tests for this
    @Override
    public String getMessage() {
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    public CheckDigitException(long num) {}

    // TODO: Write tests for this
    public CheckDigitException(String numStr) {}

    // TODO: Write tests for this
    public CheckDigitException(byte badCheckDigit, long num) {}

    // TODO: Write tests for this
    public CheckDigitException(byte badCheckDigit, String numStr) {}

    // TODO: Write tests for this
    public CheckDigitException(byte badCheckDigit, long num, String message) {}

    // TODO: Write tests for this
    public CheckDigitException(byte badCheckDigit, String numStr, String message) {}

}
