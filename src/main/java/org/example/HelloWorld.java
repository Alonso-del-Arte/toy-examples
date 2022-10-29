package org.example;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.util.Locale;
import java.util.NoSuchElementException;

import textops.LocaleParser;

public class HelloWorld {

    static String greeting(Locale locale) {

        String originalGreeting = "Hello, world!";

        Translate translate = TranslateOptions.getDefaultInstance().getService();

        Translation translation =
            translate.translate(originalGreeting, TranslateOption.targetLanguage(locale.toString()));

        return translation.getTranslatedText();
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
