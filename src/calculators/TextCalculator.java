package calculators;

public class TextCalculator {

    /**
     * Checks whether a character is a Unicode high surrogate.
     * @param ch The character to check. For example, '&Lambda;'.
     * @return True if a character is a high surrogate (at least U+D800, at most U+DBFF), false otherwise.
     */
    public static boolean isHighSurrogate(char ch) {
        return ch > '\uD7FF' && ch < '\uDC00';
    }

    // TODO: Write tests for this
    public static boolean isLowSurrogate(char ch) {
        return false;
    }

    // TODO: Write tests for this
    public static boolean isOutsideBMP(String s) {
        return false;
    }

    // TODO: Write tests for this
    public static String padWithSpacesLeft(String s, int length) {
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    public static String padWithSpacesRight(String s, int length) {
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    public static String padLeft(String s, int length, char c) {
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    public static String padRight(String s, int length, char c) {
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    public static String makeAcronym(String s) {
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    public static String makeAcronym(String s, char c) {
        return "SORRY, NOT IMPLEMENTED YET";
    }

}
