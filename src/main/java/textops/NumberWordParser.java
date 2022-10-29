package textops;

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
        NUMBER_WORDS.put("ten", 10);
        NUMBER_WORDS.put("eleven", 11);
        NUMBER_WORDS.put("twelve", 12);
        NUMBER_WORDS.put("thirteen", 13);
        NUMBER_WORDS.put("fourteen", 14);
        NUMBER_WORDS.put("fifteen", 15);
        NUMBER_WORDS.put("sixteen", 16);
        NUMBER_WORDS.put("seventeen", 17);
        NUMBER_WORDS.put("eighteen", 18);
        NUMBER_WORDS.put("nineteen", 19);
        NUMBER_WORDS.put("twenty", 20);
        NUMBER_WORDS.put("thirty", 30);
        NUMBER_WORDS.put("forty", 40);
        NUMBER_WORDS.put("fifty", 50);
        NUMBER_WORDS.put("sixty", 60);
        NUMBER_WORDS.put("seventy", 70);
        NUMBER_WORDS.put("eighty", 80);
        NUMBER_WORDS.put("ninety", 90);
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
