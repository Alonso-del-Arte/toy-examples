package retail.books;

public class CheckDigitException extends RuntimeException {

    // TODO: Write tests for this
    public byte getBadCheckDigit() {
        return -1;
    }

    // TODO: Write tests for this
    public String getNumericString() {
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    public CheckDigitException(long num) {
        super("%s does not have a valid check digit"
                .formatted(Long.toString(num)));
    }

    // TODO: Write tests for this
    public CheckDigitException(String numStr) {
        super(numStr + " does not have a valid check digit");
    }

    // TODO: Write tests for this
    public CheckDigitException(byte badCheckDigit, long num) {
        super(badCheckDigit + " is not valid for " + num);
    }

    // TODO: Write tests for this
    public CheckDigitException(byte badCheckDigit, String numStr) {
        super(badCheckDigit + " is not valid for \"" + numStr + "\"");
    }

    // TODO: Write tests for this
    public CheckDigitException(byte badCheckDigit, long num, String message) {
        super(message);
    }

    // TODO: Write tests for this
    public CheckDigitException(byte badCheckDigit, String numStr,
                               String message) {
        super(message);
    }

}
