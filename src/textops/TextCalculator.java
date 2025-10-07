package textops;

public class TextCalculator {

    /**
     * Checks whether a character is a Unicode high surrogate.
     * @param ch The character to check. For example, '&Lambda;'.
     * @return True if a character is a high surrogate (at least U+D800, at most
     * U+DBFF), false otherwise.
     */
    public static boolean isHighSurrogate(char ch) {
        return ch > '\uD7FF' && ch < '\uDC00';
    }

    /**
     * Checks whether a character is a Unicode low surrogate.
     * @param ch The character to check. For example, '&lambda;'.
     * @return True if a character is a low surrogate (at least U+DC00, at most
     * U+DFFF), false otherwise.
     */
    public static boolean isLowSurrogate(char ch) {
        return ch > '\uDBFF' && ch < '\uE000';
    }

    public static boolean isAllASCII(String s) {
        if (s == null) {
            String excMsg = "String should not be null";
            throw new NullPointerException(excMsg);
        }
        boolean allASCIISoFar = true;
        int index = 0;
        char[] characters = s.toCharArray();
        while (allASCIISoFar && index < s.length()) {
            allASCIISoFar = characters[index] < 128;
            index++;
        }
        return allASCIISoFar;
    }

    // TODO: Write tests for this
    public static boolean isOutsideBMP(String s) {
        return false;
    }

    // TODO: Write tests for this
    public static String convertASCIIToEBCDIC(String s) {
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    public static String convertEBCDICToASCII(String s) {
        return "SORRY, NOT IMPLEMENTED YET";
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
