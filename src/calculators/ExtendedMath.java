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

    private static final long COMPARISON_EDGE_DENOMINATOR = Integer.MAX_VALUE;

    private static final Fraction DELTA = new Fraction(1,
            COMPARISON_EDGE_DENOMINATOR);

    public static double primePiLegendreEstimate(double x) {
        return x / (Math.log(x) - 1.08366);
    }

    public static int primePi(double x) {
        return 0;
    }

    public static Fraction abs(Fraction fraction) {
        if (fraction.getNumerator() < 0L) {
            return fraction.negate();
        } else {
            return fraction;
        }
    }

    private static Fraction newtonSqrtAlg(Fraction a, Fraction x0,
                                          Fraction x1) {
        System.out.println("x_0 = " + x0 + ", x_1 = " + x1);
        Fraction diff = abs(x0.minus(x1));
        if (diff.compareTo(DELTA) < 0) {
            System.out.println("jump on compare to delta");
            return x1;
        } else {
            try {
                Fraction x2 = ONE_HALF.times(x1.plus(a.divides(x1)));
                return newtonSqrtAlg(a, x1, x2);
            } catch (ArithmeticException ae) {
                System.err.println(ae.getMessage());
                return x1;
            }
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
        }
        long numerator = (long) Math.floor(Math.sqrt(fraction.getNumerator()));
        long denominator = (long) Math.floor(Math.sqrt(fraction
                .getDenominator()));
        Fraction firstGuess = new Fraction(numerator, denominator);
        return abs(newtonSqrtAlg(fraction, ONE, firstGuess));
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
