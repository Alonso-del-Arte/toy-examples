package reinventedwheels;

/**
 * The idea here is to reproduce <code>java.lang.Math</code> entirely "in
 * software," that is to say, without "native methods." This is of course
 * completely unnecessary for real world projects. The value here is purely
 * educational.
 * <p>For the most part, the functions of <code>java.lang.Math</code> should
 * pass the tests of <code>ToyMathTest</code>. However, we won't get these tests
 * to pass by delegating to <code>Math</code>.</p>
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

    public static double abs(double x) {
        long bitPattern = Double.doubleToLongBits(x);
        return Double.longBitsToDouble(bitPattern & Long.MAX_VALUE);
    }

    /**
     * Gives a pseudorandom number that is at least 0.0 but less than 1.0. No
     * promises about the distribution being uniform enough.
     * @return A pseudorandom number most likely to not be subnormal. Some
     * examples of actual consecutive return values: 0.8220326832526383,
     * 0.9538167054666133, 0.5021587719883371, 0.1457593655088658,
     * 0.18885082182314927, 0.4517967681540198, 0.5446456099783232,
     * 0.5776502266232226,  0.13911368720712836, 0.2645468660833896.
     */
    public static double random() {
        previousCallCount++;
        long bitPattern = ((107374182 * previousBitPattern
                + 104480 * earlierBitPattern + previousCallCount)
                | FLOATING_POINT_ONE_HALF_BIT_PATTERN)
                & FLOATING_POINT_ALMOST_ONE_BIT_PATTERN;
        earlierBitPattern = previousBitPattern;
        previousBitPattern = bitPattern;
        return (Double.longBitsToDouble(bitPattern) - 0.5) * 2;
    }

}
