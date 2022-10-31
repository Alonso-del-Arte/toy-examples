package org.example;

import java.util.Locale;
import java.util.NoSuchElementException;

import textops.LocaleParser;

public class HelloWorld {

    static String greeting(Locale locale) {
//        if (locale.toString().equals("es_US")) {
//            return "\u00A1Hola, mundo!";
//        }
        switch (locale.getISO3Language()) {
            case "deu":
                return "Hallo Welt!";
            case "eng":
                return "Hello, world!";
            case "fra":
                return "Bonjour le monde!";
            case "ita":
                return "Ciao mondo!";
            case "jpn":
                return "\u3053\u3093\u306B\u3061\u306F\u4E16\u754C\uFF01";
            case "kor":
                return "\uC548\uB155\uD558\uC138\uC694, "
                        + "\uC138\uACC4\uC785\uB2C8\uB2E4!";
            case "spa":
                return "\u00A1Hola, mundo!";
            case "zho":
                return "\u4F60\u597D\u4E16\u754C\uFF01";
            default:
                return "Greeting for " + locale.getDisplayLanguage()
                        + " language not on file yet";
        }
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
