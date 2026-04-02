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
public class BigFraction implements Comparable<BigFraction> {

    private final BigInteger numerator, denominator;

    // TODO: Write tests for this
    public BigInteger getNumerator() {
        BigInteger gcd = this.numerator.gcd(this.denominator);
        if (!gcd.equals(BigInteger.ONE)) {
            return this.numerator.divide(gcd);
        }
        return this.numerator;
    }

    // TODO: Write tests for this
    public BigInteger getDenominator() {
        BigInteger gcd = this.numerator.gcd(this.denominator);
        if (!gcd.equals(BigInteger.ONE)) {
            return this.denominator.divide(gcd);
        }
        return this.denominator;
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
    @Override
    public int compareTo(BigFraction other) {
        return 0;
    }

    // TODO: Write more tests for this
    @Override
    public String toString() {
        if (this.denominator.equals(BigInteger.ONE)) {
            return this.numerator.toString();
        }
        return this.numerator + "/" + this.denominator;
    }

    // TODO: Write more tests for this
    public String toHTMLString() {
        return "<sup>" + this.numerator + "</sup>&frasl;<sub>"
                + this.denominator + "</sub>";
    }

    // TODO: Write tests for this
    public String toTeXString() {
        return "SORRY, NOT IMPLEMENTED YET";
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

    public BigFraction(BigInteger numer, BigInteger denom) {
        if (numer == null || denom == null) {
            String excMsg = "Neither numerator nor denominator may be null";
            throw new NullPointerException(excMsg);
        }
        if (denom.equals(BigInteger.ZERO)) {
            String excMsg = "Denominator 0 is not valid";
            throw new ArithmeticException(excMsg);
        }
        this.numerator = numer;
        this.denominator = denom;
    }

}
