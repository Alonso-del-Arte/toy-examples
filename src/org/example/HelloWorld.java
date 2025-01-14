package org.example;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import textops.LocaleParser;

public class HelloWorld {

    /**
     * Gives the "Hello, world" greeting in a language appropriate for the given
     * locale. All the greetings are "hard-coded."
     * @param locale The locale. For example, <code>Locale.CANADA_FRENCH</code>.
     * @return The greeting. For example, "Bonjour le monde!" If a greeting is
     * not available here, this will instead return a message saying so.
     * @author Tom&aacute;&scaron; Ko&#345;&oacute;nek
     * @author Irem Ugurlu had an earlier version which relied on classes from
     * <code>com.google.cloud.translate</code>. It worked fine, from what I
     * could tell, but these are supposed to be toy examples without
     * dependencies.
     */
    static String greeting(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n/HelloWorld",
                locale);
        return bundle.getString("greeting");
    }

    public static void main(String[] args) {
        boolean responded = false;
        for (String arg : args) {
            try {
                Locale locale = LocaleParser.parse(arg);
                System.out.println(greeting(locale));
                responded = true;
            } catch (NoSuchElementException nsee) {
                System.err.println(nsee.getMessage());
            }
        }
        if (!responded) {
            System.out.println("Hello, world!");
        }
    }

}
