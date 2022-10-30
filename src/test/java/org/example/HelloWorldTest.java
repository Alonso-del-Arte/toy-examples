package org.example;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

}