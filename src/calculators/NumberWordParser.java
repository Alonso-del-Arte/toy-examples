package calculators;

import java.util.Locale;

public class NumberWordParser {

    private static int interpretEnglishNumberWord(String s) {
        return Integer.MIN_VALUE;
    }

    // TODO: Write tests for this
    public static int interpret(String s) {
        return interpretEnglishNumberWord(s);
    }

    public static int interpret(String s, Locale locale) {
        if (locale.getISO3Language().equals("eng")) {
            return interpretEnglishNumberWord(s);
        } else {
            String excMsg = "Language " + locale.getDisplayLanguage()
                    + " is not supported yet";
            throw new UnsupportedOperationException(excMsg);
        }
    }

}
