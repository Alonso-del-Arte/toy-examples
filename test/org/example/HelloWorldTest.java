package org.example;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of the HelloWorld class.
 * @author Alonso del Arte except where otherwise noted
 */
class HelloWorldTest {

    @Test
    void testGreetingEnglish() {
        String expected = "Hello, world!";
        String actual = HelloWorld.greeting(Locale.US);
        assertEquals(expected, actual);
    }

    @Test
    void testGreetingSpanish() {
        String expected = "\u00A1Hola, mundo!";
        String actual = HelloWorld.greeting(new Locale("es", "US"));
        assertEquals(expected, actual);
    }

    /**
     * Test for the greeting function of the HelloWorld class as specified in
     * <a href="https://github.com/Alonso-del-Arte/toy-examples/issues/5">Issue 5</a>:
     * it should return an appropriate "Hello World" greeting for each of
     * the named <code>Locale</code> constants in that class's Javadoc. And it
     * should be done without using external APIs like the one for Google
     * Translate.
     * @author Tom&aacute;&scaron; Ko&#345;&oacute;nek
     */
    @Test
    public void testGreetingForSpecifiedLocales() {
        String greetingDE = "Hallo Welt!";
        String greetingEN = "Hello, world!";
        String greetingFR = "Bonjour le monde!";
        String greetingIT = "Ciao mondo!";
        String greetingJP = "\u3053\u3093\u306B\u3061\u306F\u4E16\u754C\uFF01";
        String greetingKO = "\uC548\uB155\uD558\uC138\uC694, "
                + "\uC138\uACC4\uC785\uB2C8\uB2E4!";
        String greetingZH = "\u4F60\u597D\u4E16\u754C\uFF01";
        Map<Locale, String> greetings = new HashMap<>();
        greetings.put(Locale.CANADA, greetingEN);
        greetings.put(Locale.CANADA_FRENCH, greetingFR);
        greetings.put(Locale.CHINA, greetingZH);
        greetings.put(Locale.CHINESE, greetingZH);
        greetings.put(Locale.ENGLISH, greetingEN);
        greetings.put(Locale.FRANCE, greetingFR);
        greetings.put(Locale.FRENCH, greetingFR);
        greetings.put(Locale.GERMAN, greetingDE);
        greetings.put(Locale.GERMANY, greetingDE);
        greetings.put(Locale.ITALIAN, greetingIT);
        greetings.put(Locale.ITALY, greetingIT);
        greetings.put(Locale.JAPAN, greetingJP);
        greetings.put(Locale.JAPANESE, greetingJP);
        greetings.put(Locale.KOREA, greetingKO);
        greetings.put(Locale.KOREAN, greetingKO);
        greetings.put(Locale.PRC, greetingZH);
        greetings.put(Locale.SIMPLIFIED_CHINESE, greetingZH);
        greetings.put(Locale.TAIWAN, greetingZH);
        greetings.put(Locale.TRADITIONAL_CHINESE, greetingZH);
        greetings.put(Locale.UK, greetingEN);
        greetings.put(Locale.US, greetingEN);
        greetings.forEach((key, expected) -> {
            String actual = HelloWorld.greeting(key);
            assertEquals(expected, actual);
        });
    }

    @Test
    void testMain() {
        System.out.println("main");
        System.out.println("Rerouting System.out");
        PrintStream usualPrintStream = System.out;
        ByteArrayOutputStream interceptor = new ByteArrayOutputStream();
        PrintStream interceptedStream = new PrintStream(interceptor);
        System.setOut(interceptedStream);
        String expected = HelloWorld.greeting(Locale.getDefault());
        String[] args = {};
        HelloWorld.main(args);
        String actual = interceptor.toString().replace("\n", "")
                .replace("\r", "");
        System.setOut(usualPrintStream);
        System.out.println("Restored usual System.out");
        assertEquals(expected, actual);
    }

}
