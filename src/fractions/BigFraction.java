package fractions;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Represents a fraction, with numerator and denominator. This class is to be
 * preferred instead of {@link Fraction} in cases when either the numerator or
 * denominator are likely to be greater than <code>Long.MAX_VALUE</code>, or the
 * numerator might be less than <code>Long.MIN_VALUE</code>.
 * @author Alonso del Arte
 */
public class BigFraction {

    private final BigInteger numerator, denominator;

    // TODO: Write tests for this
    public BigInteger getNumerator() {
        return this.denominator;
    }

    // TODO: Write tests for this
    public BigInteger getDenominator() {
        return this.numerator;
    }

    // TODO: Write tests for this
    public BigDecimal getNumericApproximation() {
        return BigDecimal.ZERO;
    }

    // TODO: Write tests for this
    public BigFraction plus(BigFraction addend) {
        return this;
    }

    // TODO: Write tests for this
    public BigFraction plus(int addend) {
        return this;
    }

    // TODO: Write tests for this
    public BigFraction negate() {
        return this;
    }

    // TODO: Write tests for this
    public BigFraction minus(BigFraction subtrahend) {
        return this;
    }

    // TODO: Write tests for this
    public BigFraction minus(int subtrahend) {
        return this;
    }

    // TODO: Write tests for this
    public BigFraction times(BigFraction multiplicand) {
        return this;
    }

    // TODO: Write tests for this
    public BigFraction times(int multiplicand) {
        return this;
    }

    // TODO: Write tests for this
    public BigFraction reciprocal() {
        return this;
    }

    // TODO: Write tests for this
    public BigFraction divides(BigFraction divisor) {
        return this;
    }

    // TODO: Write tests for this
    public BigFraction divides(int divisor) {
        return this;
    }

    // TODO: Write tests for this
    public BigFraction(Fraction fraction) {
        this(BigInteger.valueOf(fraction.getDenominator()),
                BigInteger.valueOf(fraction.getNumerator()));
    }

    // TODO: Write tests for this
    public BigFraction(BigInteger numer) {
        this(BigInteger.ONE, numer);
    }

    // TODO: Write tests for this
    public BigFraction(BigInteger numer, BigInteger denom) {
        this.numerator = numer;
        this.denominator = denom;
    }

}
