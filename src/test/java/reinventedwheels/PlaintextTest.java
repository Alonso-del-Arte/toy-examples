package reinventedwheels;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaintextTest {

    private static final Random RANDOM = new Random();

    @Test
    void testToString() {
        System.out.println("toString");
        String expected = "Hello, world!";
        Plaintext plaintext = new Plaintext(expected.toCharArray());
        String actual = plaintext.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testToStringPseudorandom() {
        String expected = "Some number is " + RANDOM.nextInt();
        Plaintext plaintext = new Plaintext(expected.toCharArray());
        String actual = plaintext.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testToLowerCase() {
        System.out.println("toLowerCase");
        String example = "This Is Example Text";
        Plaintext plaintext = new Plaintext(example.toCharArray());
        String expected = example.toLowerCase();
        String actual = plaintext.toLowerCase().toString();
        assertEquals(expected, actual);
    }

}