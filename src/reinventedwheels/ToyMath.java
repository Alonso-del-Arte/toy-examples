package reinventedwheels;

/**
 * The idea here is to reproduce <code>java.lang.Math</code> entirely "in
 * software," that is to say, without "native methods." This is of course
 * completely unnecessary for real world projects. The value here is purely
 * educational.
 * @author Alonso del Arte
 */
public class ToyMath {

    private static final long FLOATING_POINT_EXPONENT_MASK
            = 0x000FFFFFFFFFFFFFL;

    public static final long FLOATING_POINT_ONE_HALF_BIT_PATTERN
            = 0x3FE0000000000000L;

    private static final long FLOATING_POINT_ALMOST_ONE_BIT_PATTERN
            = 0x3FEFFFFFFFFFFFFFL;

    private static int previousCallCount = 0;

    private static long earlierBitPattern = System.currentTimeMillis();

    private static long previousBitPattern = (earlierBitPattern << 32)
            + earlierBitPattern;

    // TODO: Write tests for this
    public static double floor(double x) {
        return Double.MAX_VALUE;
    }

    // TODO: Write tests for this
    public static double ceiling(double x) {
        return Double.MIN_VALUE;
    }

    // TODO: Write tests for this
    public static double abs(double x) {
        return Double.MIN_VALUE;
    }

    public static double random() {
        previousCallCount++;
        long bitPattern = ((107374182 * previousBitPattern
                + 104480 * earlierBitPattern + previousCallCount)
                | FLOATING_POINT_ONE_HALF_BIT_PATTERN)
                & FLOATING_POINT_ALMOST_ONE_BIT_PATTERN;
        earlierBitPattern = previousBitPattern;
        previousBitPattern = bitPattern;
        double number = Double.longBitsToDouble(bitPattern);
        return (previousCallCount % 2 == 0 && number > 0.5)
                ? number : number - 0.5;
    }

}
