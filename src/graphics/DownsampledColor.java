package graphics;

import java.awt.Color;

public class DownsampledColor {

    private final byte colorByte;

    @Override
    public String toString() {
        int red = (this.colorByte & 48) >> 4;
        int green = (this.colorByte & 12) >> 2;
        int blue = this.colorByte & 3;
        int alpha = (this.colorByte & 192) >> 6;
        return "[r = " + red + ", g = " + green + ", b = " + blue + ", a = "
                + alpha + "]";
    }

    DownsampledColor(byte b) {
        this.colorByte = b;
    }

    public DownsampledColor(Color color) {
        // TODO: Write tests for this
        this.colorByte = 0;
    }

}
