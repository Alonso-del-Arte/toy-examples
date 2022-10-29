package textops;

import java.util.Locale;
import java.util.NoSuchElementException;

public class LocaleParser {

    public static final Locale[] LOCALES = Locale.getAvailableLocales();

    public static Locale parse(String s) {
        boolean found = false;
        int index = 0;
        Locale locale = Locale.getDefault();
        while (!found && index < LOCALES.length) {
            locale = LOCALES[index];
            found = locale.toString().equals(s);
            index++;
        }
        return locale;
    }

}
