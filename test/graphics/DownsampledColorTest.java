package graphics;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static randomness.ExtendedRandom.nextColor;
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

    private static Object passThrough(Object obj) {
        return obj;
    }

    @Test
    void testReferentialEquality() {
        byte b = (byte) nextInt(256);
        DownsampledColor instance = new DownsampledColor(b);
        String msg = "Instance " + instance + " should be equal to itself";
        Object obj = passThrough(instance);
        assertEquals(instance, obj, msg);
    }

    private static Object provideNull() {
        return null;
    }

    @Test
    void testNotEqualsNull() {
        byte b = (byte) nextInt(256);
        DownsampledColor instance = new DownsampledColor(b);
        String msg = "Instance " + instance + " should not equal null";
        assert !instance.equals(provideNull()) : msg;
    }

    private static byte downsample(Color color) {
        int value = color.getRGB();
        int r = (color.getRed() / 64) << 4;
        int g = (color.getGreen() / 64) << 2;
        int b = (color.getBlue() / 64);
        int a = (color.getAlpha() / 64) << 6;
        return  (byte) (a + r + g + b);
    }

    @Test
    void testNotEqualsDiffClass() {
        Color color = nextColor();
        byte b = downsample(color);
        DownsampledColor instance = new DownsampledColor(b);
        String msg = instance + " should not equal " + color;
        Object obj = passThrough(color);
        assert !instance.equals(obj) : msg;
    }

    @Test
    void testNotEqualsDiffColor() {
        Color color = nextColor();
        byte b = downsample(color);
        DownsampledColor colorA = new DownsampledColor(b);
        DownsampledColor colorB = new DownsampledColor((byte) (~b));
        String message = colorA + " should not equal " + colorB;
        assertNotEquals(colorA, colorB, message);
    }

}
