package calculators;

import java.util.Locale;

public class NumberWordParser {

    private static int interpretEnglishNumberWord(String s) {
        switch (s) {
            case "zero":
                return 0;
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
            default:
                return Integer.MIN_VALUE;
//                String excMsg = "Word \"" + s
//                        + "\" not recognized as a number word in English";
//                throw new NumberFormatException(excMsg);
        }
    }

    private static int interpretEnglishNumberWords(String s) {
        return interpretEnglishNumberWord(s);
    }

    // TODO: Write tests for this
    public static int interpret(String s) {
        return interpretEnglishNumberWords(s);
    }

    public static int interpret(String s, Locale locale) {
        if (locale.getISO3Language().equals("eng")) {
            return interpretEnglishNumberWords(s);
        } else {
            String excMsg = "Language " + locale.getDisplayLanguage()
                    + " is not supported yet";
            throw new UnsupportedOperationException(excMsg);
        }
    }

}
