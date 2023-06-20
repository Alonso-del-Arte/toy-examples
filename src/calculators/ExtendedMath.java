package calculators;

import fractions.Fraction;

import java.util.List;
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

    private static final Fraction ONE_HALF = new Fraction(1, 2);

    public static final Fraction NEGATIVE_ONE_HALF = ONE_HALF.negate();

    public static boolean isNormal(double x) {
        return true;
    }

    public static boolean isSubnormal(double x) {
        return false;
    }

    // TODO: Write test for negative x
    public static double primePiLegendreEstimate(double x) {
        return x / (Math.log(x) - 1.08366);
    }

    // TODO: Write test for negative x
    public static int primePi(double x) {
        if (x < 0.0) {
            return Integer.MAX_VALUE;
        }
        if (x < 2.0) {
            return 0;
        }
        List<Integer> primes
                = EratosthenesSieve.listPrimes((int) Math.floor(x) + 2);
        int index = 0;
        int currPrime;
        do {
            currPrime = primes.get(index);
            index++;
        } while (currPrime < x);
        return index;// - ((currPrime == x) ? 0 : 1);
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
        if (x0.equals(x1)) {
            return x1;
        } else {
            if (x1.getNumerator() < 0L) {
                return x0;
            } else {
                Fraction x2 = ONE_HALF.times(x1.plus(a.divides(x1)));
                return newtonSqrtAlg(a, x1, x2);
            }
        }
    }

    /**
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
        return abs(newtonSqrtAlg(fraction, NEGATIVE_ONE_HALF, firstGuess));
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
