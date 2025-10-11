package graphics;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static randomness.ExtendedRandom.nextInt;

class DownsampledColorTest {

    @Test
    void testToString() {
        System.out.println("toString");
        int n = nextInt(256);
        int red = (n & 48) >> 4;
        int green = (n & 12) >> 2;
        int blue = n & 3;
        int alpha = (n & 192) >> 6;
        byte b = (byte) n;
        DownsampledColor instance = new DownsampledColor(b);
        String expected = "[r = " + red + ", g = " + green + ", b = " + blue
                + ", a = " + alpha + "]";
        String actual = instance.toString();
        String message = "Color from byte " + b;
        assertEquals(expected, actual, message);
    }

}
