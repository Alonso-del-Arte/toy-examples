package reinventedwheels;

/**
 * The idea here is to reproduce <code>java.lang.Math</code> entirely "in
 * software," that is to say, without "native methods." This is of course
 * completely unnecessary for real world projects. The value here is purely
 * educational.
 * @author Alonso del Arte
 */
class ToyMath {

    private static final long FLOATING_POINT_ALMOST_ONE_BIT_PATTERN
            = 0x3FEFFFFFFFFFFFFFL;

    private static long previousBitPattern = Double.doubleToLongBits(0.5);

    // TODO: Write tests for this
    static double floor(double x) {
        return Double.MAX_VALUE;
    }

    // TODO: Write tests for this
    static double ceiling(double x) {
        return Double.MIN_VALUE;
    }

    // TODO: Write tests for this
    static double abs(double x) {
        return Double.MIN_VALUE;
    }

    static double random() {
        long millis = System.currentTimeMillis();
        long bitPattern = (millis ^ (previousBitPattern * 37))
                & FLOATING_POINT_ALMOST_ONE_BIT_PATTERN;
        previousBitPattern = bitPattern;
        return Double.longBitsToDouble(bitPattern);
    }

}
