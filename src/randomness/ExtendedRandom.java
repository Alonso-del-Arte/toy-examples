package randomness;

import currency.CurrencyAmount;
import fractions.Fraction;

import java.util.Random;

import numerics.ComplexNumber;
import numerics.RomanNumeralsNumber;

public class ExtendedRandom {

    private static final Random RANDOM = new Random();

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

}
