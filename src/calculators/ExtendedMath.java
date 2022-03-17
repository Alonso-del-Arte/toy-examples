package calculators;

import fractions.Fraction;

import java.util.Random;

public class ExtendedMath {

    private static final Random RANDOM = new Random();

    /**
     * The smallest denominator that {@link #random()} can choose for a
     * pseudorandom fraction.
     */
    public static final int MINIMUM_RANDOM_DENOMINATOR = 3;

    /**
     * The largest denominator that {@link #random()} can choose for a
     * pseudorandom fraction. The reason for this bound is to ensure that any
     * two fractions given by that function can be added or multiplied without
     * causing any overflow or underflow problems.
     */
    public static final int BOUND_FOR_RANDOM_DENOMINATORS
            = Integer.MAX_VALUE >> 14;

    private static final int UPPER_BOUND = BOUND_FOR_RANDOM_DENOMINATORS
            - MINIMUM_RANDOM_DENOMINATOR + 1;

    private static final Fraction ONE = new Fraction(1, 1);

    private static final Fraction ONE_HALF = new Fraction(1, 2);

    // TODO: Write tests for this
    public static double primePiLegendreEstimate(double x) {
        return -1.0;
    }

    // TODO: Write tests for this
    public static int primePi(double x) {
        return -1;
    }

    // TODO: Write tests for this
    public static Fraction abs(Fraction fraction) {
        return ONE_HALF;
    }

    private static Fraction newtonSqrtAlg(Fraction a, Fraction x0, Fraction x1) {
        double diff = Math.abs(x0.getNumericApproximation()
                - x1.getNumericApproximation());
        if (diff <= Double.MIN_NORMAL) {
            return x0;
        } else {
            Fraction x2 = ONE_HALF.times(x1.plus(a.divides(x1)));
            return newtonSqrtAlg(a, x1, x2);
        }
    }

    /* TODO: Restore Javadoc after test passes
     * Calculates the square root of a number using Newton's approximation
     * algorithm. Since the algorithm is recursive, some inputs may cause stack
     * overflow errors.
     * @param fraction The number to calculate the square root of. For example,
     *                 <sup>4</sup>&frasl;<sub>25</sub>.
     * @return The square root of the number. For example,
     * <sup>2</sup>&frasl;<sub>5</sub>.
     * @throws ArithmeticException If <code>Fraction</code> is negative.
     */
    public static Fraction sqrt(Fraction fraction) {
        if (fraction.getNumerator() < 0L) {
            String excMsg = "Fraction data type can't represent sqrt("
                    + fraction + "), which is an imaginary number";
            throw new ArithmeticException(excMsg);
        }return fraction;
//        return newtonSqrtAlg(fraction, ONE, ONE_HALF);
    }

    /**
     * Provides a pseudorandomly chosen fraction.
     * @return A fraction at least 0 but not more than 1. For example,
     * <sup>2470</sup>&frasl;<sub>40449</sub>, which is roughly
     * 0.061064550421518454. The denominator will be at least {@link
     * #MINIMUM_RANDOM_DENOMINATOR} but not more than {@link
     * #BOUND_FOR_RANDOM_DENOMINATORS}. The numerator will be at least 0 but not
     * equal to nor greater than the denominator.
     */
    public static Fraction random() {
        int denominator = RANDOM.nextInt(UPPER_BOUND)
                + MINIMUM_RANDOM_DENOMINATOR;
        int numerator = RANDOM.nextInt(denominator);
        return new Fraction(numerator, denominator);
    }

}
