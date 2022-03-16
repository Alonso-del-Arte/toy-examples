package calculators;

import fractions.Fraction;

import java.util.Random;

public class ExtendedMath {

    private static final Random RANDOM = new Random();

    public static final int BOUND_FOR_DENOMINATORS = Integer.MAX_VALUE >> 14;

    public static final int MINIMUM_RANDOM_DENOMINATOR = 3;

    private static final int UPPER_BOUND = BOUND_FOR_DENOMINATORS
            - MINIMUM_RANDOM_DENOMINATOR;

    // TODO: Write tests for this
    public static double primePiLegendreEstimate(double x) {
        return -1.0;
    }

    // TODO: Write tests for this
    public static int primePi(double x) {
        return -1;
    }

    // TODO: Write tests for this
    public static Fraction sqrt(Fraction fraction) {
        return fraction;
    }

    /**
     * Provides a pseudorandomly chosen fraction.
     * @return A fraction at least 0 but not more than 1. For example,
     * <sup>2470</sup>&frasl;<sub>40449</sub>, which is roughly
     * 0.061064550421518454. The denominator will be at least {@link
     * #MINIMUM_RANDOM_DENOMINATOR} but not more than {@link
     * #BOUND_FOR_DENOMINATORS}. The numerator will be at least 0 but not equal
     * to nor greater than the denominator.
     */
    public static Fraction random() {
        int denominator = RANDOM.nextInt(UPPER_BOUND)
                + MINIMUM_RANDOM_DENOMINATOR;
        int numerator = RANDOM.nextInt(denominator);
        return new Fraction(numerator, denominator);
    }

}
