package calculators;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NumberWordParser {

    private final static Map<String, Integer> NUMBER_WORDS = new HashMap<>();

    static {
        NUMBER_WORDS.put("zero", 0);
        NUMBER_WORDS.put("one", 1);
        NUMBER_WORDS.put("two", 2);
        NUMBER_WORDS.put("three", 3);
        NUMBER_WORDS.put("four", 4);
        NUMBER_WORDS.put("five", 5);
        NUMBER_WORDS.put("six", 6);
        NUMBER_WORDS.put("seven", 7);
        NUMBER_WORDS.put("eight", 8);
        NUMBER_WORDS.put("nine", 9);
    }

    private static int interpretEnglishNumberWord(String s) {
        return NUMBER_WORDS.getOrDefault(s, Integer.MIN_VALUE);
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
