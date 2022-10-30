package org.example;

import java.util.Locale;
import java.util.NoSuchElementException;

import textops.LocaleParser;

public class HelloWorld {

    static String greeting(Locale locale) {
        if (locale.toString().equals("es_US")) {
            return "\u00A1Hola, mundo!";
        }
        return "Hello, world!";
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
