package retail.books;

public class CheckDigitException extends RuntimeException {

    private String msg = "SORRY, NOT IMPLEMENTED YET";

    // TODO: Write tests for this
    @Override
    public String getMessage() {
        return this.msg;
    }

    // TODO: Write tests for this
    public CheckDigitException(long num) {
        this.msg = "%s does not have a valid check digit"
                .formatted(Long.toString(num));
    }

    // TODO: Write tests for this
    public CheckDigitException(String numStr) {}

    // TODO: Write tests for this
    public CheckDigitException(byte badCheckDigit, long num) {
        this.msg = badCheckDigit + " is not valid for " + num;
    }

    // TODO: Write tests for this
    public CheckDigitException(byte badCheckDigit, String numStr) {
        this.msg = badCheckDigit + " is not valid for \"" + numStr + "\"";
    }

    // TODO: Write tests for this
    public CheckDigitException(byte badCheckDigit, long num, String message) {
        this.msg = message;
    }

    // TODO: Write tests for this
    public CheckDigitException(byte badCheckDigit, String numStr, String message) {
        this.msg = message;
    }

}
