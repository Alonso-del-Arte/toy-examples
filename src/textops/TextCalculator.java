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

    /**
     * Determines whether a {@code String} instance consists entirely of ASCII
     * characters or not.
     * @param s The {@code String} for which to make the determination.
     *          Examples: "Anton&amp;iacute;n Dvo&amp;#x159;&amp;aacute;k",
     *          "Anton&iacute;n Dvo&#x159;&aacute;k".
     * @return True if {@code s} consists of ASCII characters only, false
     * otherwise. In the examples: true for the first one, since the non-ASCII
     * characters have been replaced with HTML entities; false for the second
     * one because '&iacute;', '&#x159' and '&aacute;' are not ASCII characters.
     * @throws NullPointerException If {@code s} is null. An argument could be
     * made that this function should return true when {@code s} is null.
     */
    public static boolean isAllASCII(String s) {
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
