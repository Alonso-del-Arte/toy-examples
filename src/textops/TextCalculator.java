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
     *          "Anton&iacute;n Dvo&#x159;&aacute;k" and "" (the empty {@code
     *          String}).
     * @return True if {@code s} consists of ASCII characters only or is empty,
     * false otherwise. In the examples: true for the first one, since the
     * non-ASCII characters have been replaced with HTML entities; false for the
     * second one because '&iacute;', '&#x159' and '&aacute;' are not ASCII
     * characters (though evaluation stops on finding the first non-ASCII
     * character); and true for the empty {@code String} because it doesn't
     * contain any non-ASCII characters even though it doesn't contain any ASCII
     * characters either.
     * @throws NullPointerException If {@code s} is null. An argument could be
     * made that this function should return true when {@code s} is null, by
     * analogy to the empty {@code String}. But if a {@code String} needs to be
     * queried for containing non-ASCII characters, there might be a need for
     * further processing, so if this function gave a result instead of throwing
     * an exception, that would postpone the exception to a point where it is
     * harder to diagnose the problem.
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

    /**
     * Determines whether a {@code String} instance contains any characters
     * outside Unicode's Basic Multilingual Plane (BMP).
     * @param s The {@code String} to check. Examples: "e&#x1078E;",
     *          "e&#x0259;", the empty {@code String}.
     * @return True if {@code s} contains at least one character outside the
     * BMP. In the examples, true for "e&#x1078E;" since the second character is
     * from the Supplementary Multilingual Plane (SMP) rather than the BMP,
     * false for "e&#x0259;" since the schwa is from the BMP, and false in the
     * case of the empty  {@code String}, even though it doesn't contain any BMP
     * characters.
     * @throws NullPointerException If {@code s} is null.
     */
    public static boolean isOutsideBMP(String s) {
        char[] array = s.toCharArray();
        boolean found = false;
        int index = 0;
        int max = array.length;
        while (index < max && !found) {
            found = isHighSurrogate(array[index])
                    && isLowSurrogate(array[index + 1]);
            index++;
        }
        return found;
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
        if (length < 0) {
            String excMsg = "Length " + length + " is not valid";
            throw new IllegalArgumentException(excMsg);
        }
        if (s.length() >= length) {
            return s;
        }
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    public static String padWithSpacesRight(String s, int length) {
        if (length < 0) {
            String excMsg = "Length " + length + " is not valid";
            throw new IllegalArgumentException(excMsg);
        }
        if (s.length() >= length) {
            return s;
        }
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    public static String padLeft(String s, int length, char c) {
        if (length < 0) {
            String excMsg = "Length " + length + " is not valid";
            throw new IllegalArgumentException(excMsg);
        }
        if (s.length() >= length) {
            return s;
        }
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    public static String padRight(String s, int length, char c) {
        if (length < 0) {
            String excMsg = "Length " + length + " is not valid";
            throw new IllegalArgumentException(excMsg);
        }
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
