package randomness;

import collections.immutable.Range;
import currency.CurrencyAmount;
import fractions.Fraction;

import java.math.BigInteger;
import java.util.Random;

import numerics.ComplexNumber;
import numerics.RomanNumeralsNumber;

public class ExtendedRandom {

    private static final Random RANDOM = new Random();

    /**
     * Gives a pseudorandomly chosen integer. May be positive or negative, or
     * it could even be 0. This function is essentially a static wrapper for
     * <code>java.util.Random</code>'s <code>nextInt()</code>.
     * @return A pseudorandomly chosen integer, at least
     * <code>Integer.MIN_VALUE</code>, at most <code>Integer.MAX_VALUE</code>.
     * For example, &minus;2038868420.
     */
    public static int nextInt() {
        return RANDOM.nextInt();
    }

    // TODO: Write test for negative bound. Should still give a result, e.g., if
    //  bound = -128, then an integer in the range -127 to 0 would be a valid
    //  result
    /**
     * Gives a pseudorandomly chosen integer as specified by the bound. This
     * function is essentially a static wrapper for
     * <code>java.util.Random</code>'s one-parameter <code>nextInt()</code>.
     * @param bound A lower or upper bound for the pseudorandomly chosen
     *              integer. The other bound is 0. The farther away from 0 that
     *              this parameter is, the likelier it is for repeated calls to
     *              give a decent distribution of values.
     * @return A pseudorandomly chosen integer, may be 0 or any integer between
     * 0 and <code>bound</code>. For example, given a bound of 1024, this
     * function might return 743.
     */
    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    // TODO: Write tests for this
    public static int nextInt(Range range) {
        return 0;
    }

    /**
     * Gives a pseudorandomly chosen <code>BigInteger</code> instance
     * corresponding to 0 or a positive integer. This is a static wrapper for
     * one of the <code>BigInteger</code> constructors.
     * @param bitLength How many significant bits the integer should have at
     *                  most. For example, 72.
     * @return A pseudorandomly chosen <code>BigInteger</code> instance, at
     * least <code>BigInteger.ZERO</code>, at most
     * 2<sup><code>bitLength</code></sup> &minus; 1. But it's likelier to be
     * closer to the latter than to the former. For example, given a
     * <code>bitLength</code> of 72, the result might be 1617975039218359439262.
     */
    public static BigInteger nextBigInt(int bitLength) {
        return new BigInteger(bitLength, RANDOM);
    }

    // TODO: Write Javadoc
    public static double nextDouble() {
        return RANDOM.nextDouble();
    }

    /**
     * Gives a pseudorandomly chosen fraction. May be positive or negative, or
     * it could even be 0.
     * @return A fraction with a pseudorandom signed 32-bit integer for the
     * numerator and a denominator of at least 1 and at most
     * <code>Integer.MAX_VALUE</code>.
     */
    public static Fraction nextFraction() {
        int numer = RANDOM.nextInt();
        int denom = RANDOM.nextInt(Integer.MAX_VALUE) + 1;
        return new Fraction(numer, denom);
    }

    // TODO: Write tests for this
    public static ComplexNumber nextComplex() {
        return new ComplexNumber(0.0, 0.0);
    }

    public static String alphanumeric(int length) {
        if (length < 1) {
            String excMsg = "Length " + length + " is not valid";
            throw new IllegalArgumentException(excMsg);
        }
        char[] asciiChars = new char[length];
        for (int i = 0; i < length; i++) {
            asciiChars[i] = 'A';
        }
        return new String(asciiChars);
    }

}
