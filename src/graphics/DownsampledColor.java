package graphics;

import java.awt.Color;

public final class DownsampledColor {

    private final byte colorByte;

    // TODO: Write tests for this
    public Color upsample() {
        return Color.WHITE;
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
        return ((int) this.colorByte) << 8;
    }

    DownsampledColor(byte b) {
        this.colorByte = b;
    }

    public DownsampledColor(Color color) {
        // TODO: Write tests for this
        this.colorByte = 0;
    }

}
