package graphics;

import java.awt.Color;

public final class DownsampledColor {

    private final byte colorByte;

    // TODO: Write tests for this
    public Color upsample() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public String toString() {
        int red = (this.colorByte & 48) >> 4;
        int green = (this.colorByte & 12) >> 2;
        int blue = this.colorByte & 3;
        int alpha = (this.colorByte & 192) >> 6;
        return "[r = " + red + ", g = " + green + ", b = " + blue + ", a = "
                + alpha + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof DownsampledColor) {
            return this.colorByte == ((DownsampledColor) obj).colorByte;
        } else return false;
    }

    public int hashCode() {
        return this.colorByte << 8;
    }

    DownsampledColor(byte b) {
        this.colorByte = b;
    }

    public DownsampledColor(Color color) {
        int red = (color.getRed() / 64) << 4;
        int green = (color.getGreen() / 64) << 2;
        int blue = (color.getBlue() / 64);
        int alpha = (color.getAlpha() / 64) << 6;
        this.colorByte = (byte) (alpha + red + green + blue);
    }

}
