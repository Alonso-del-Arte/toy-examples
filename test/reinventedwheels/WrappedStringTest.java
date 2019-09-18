package reinventedwheels;

import org.junit.Test;

import static org.junit.Assert.*;

public class WrappedStringTest {

    @Test
    public void toAllCaps() {
        System.out.println("toAllCaps");
        WrappedString s = new WrappedString("helloworld");
        WrappedString expected = new WrappedString("HELLOWORLD");
        WrappedString actual = s.toAllCaps();
        assertEquals(expected, actual);
    }

    @Test
    public void toAllCapsWithSomeCaps() {
        WrappedString s = new WrappedString("Hello, World!");
        WrappedString expected = new WrappedString("HELLO, WORLD!");
        WrappedString actual = s.toAllCaps();
        assertEquals(expected, actual);
    }

}