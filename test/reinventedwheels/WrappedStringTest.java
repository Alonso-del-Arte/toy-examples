package reinventedwheels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests of the WrappedString class.
 */
public class WrappedStringTest {

    /**
     * Test of the toAllCaps function, of the WrappedString class.
     */
    @Test
    public void toAllCaps() {
        System.out.println("toAllCaps");
        WrappedString s = new WrappedString("helloworld");
        WrappedString expected = new WrappedString("HELLOWORLD");
        WrappedString actual = s.toAllCaps();
        assertEquals(expected, actual);
    }

    /**
     * Another test of the toAllCaps function, of the WrappedString class.
     */
    @Test
    public void toAllCapsWithSomeCaps() {
        WrappedString s = new WrappedString("Hello, World!");
        WrappedString expected = new WrappedString("HELLO, WORLD!");
        WrappedString actual = s.toAllCaps();
        assertEquals(expected, actual);
    }

}
