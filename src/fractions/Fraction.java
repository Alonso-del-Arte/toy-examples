package fractions;

import static calculators.IntegerMath.euclideanGCD;

/**
 * Represents a fraction, with numerator and denominator.
 * @author Alonso del Arte
 */
public class Fraction implements Comparable<Fraction> {

    private final long numerator, denominator;

    public long getNumerator() {
        return this.numerator;
    }

    public long getDenominator() {
        return this.denominator;
    }

    public double getNumericApproximation() {
        return ((double) this.numerator) / this.denominator;
    }

    public Fraction plus(Fraction addend) {
        long numeratorA = this.numerator * addend.denominator;
        long numeratorB = addend.numerator * this.denominator;
        long crossDenom = this.denominator * addend.denominator;
        return new Fraction(numeratorA + numeratorB, crossDenom);
    }

    // TODO: Write tests for this
    public Fraction plus(int addend) {
        return this;
    }

    public Fraction negate() {
        return new Fraction(-this.numerator, this.denominator);
    }

    public Fraction minus(Fraction subtrahend) {
        return this.plus(subtrahend.negate());
    }

    // TODO: Write tests for this
    public Fraction minus(int subtrahend) {
        return this;
    }

    public Fraction times(Fraction multiplicand) {
        return new Fraction(this.numerator * multiplicand.numerator,
                this.denominator * multiplicand.denominator);
    }

    // TODO: Write tests for this
    public Fraction times(int multiplicand) {
        return this;
    }

    public Fraction reciprocal() {
        return new Fraction(this.denominator, this.numerator);
    }

    public Fraction divides(Fraction divisor) {
        return this.times(divisor.reciprocal());
    }

    // TODO: Write tests for this
    public Fraction divides(int divisor) {
        return this;
    }

    @Override
    public String toString() {
        if (this.denominator == 1L) {
            return Long.toString(this.numerator);
        } else {
            return this.numerator + "/" + this.denominator;
        }
    }

    // TODO: Write tests for this
    public String toHTMLString() {
        return this.toString();
    }

    // TODO: Write tests for this
    public String toTeXString() {
        return this.toString();
    }

    /**
     * Determines whether an object is equal to this <code>Fraction</code>
     * instance. Optimized for referential equality.
     * @param obj The object to compare. Examples: the fractions
     *            <sup>22</sup>&frasl;<sub>25</sub>,
     *            <sup>22</sup>&frasl;<sub>7</sub>,
     *            &minus;<sup>1</sup>&frasl;<sub>7</sub>,
     *            <sup>22</sup>&frasl;<sub>7</sub> but in an anonymous subclass
     *            of <code>Fraction</code>, the floating point value
     *            3.142857142857143 wrapped in a <code>Double</code>, and
     *            a null.
     * @return True only if <code>obj</code> is a <code>Fraction</code> with the
     * same runtime class, the same numerator and the same denominator, false in
     * all other cases. Suppose this is a <code>Fraction</code> instance for
     * <sup>22</sup>&frasl;<sub>7</sub>. Then, given the examples above for
     * <code>obj</code>, the results would be false (denominator is different),
     * true, false (numerator is different), false (even though it is an
     * instance of <code>Fraction</code>, its runtime class would be something
     * like <code>org.example.SomeClass$1</code> rather than
     * <code>fractions.Fraction</code>), false (even though the numerical values
     * differ by less than 0.000000000000001) and false (because this is a
     * non-null object).
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        Fraction other = (Fraction) obj;
        return this.numerator == other.numerator
                && this.denominator == other.denominator;
    }

    /**
     * Generates a hash code. Guaranteed to be unique in a given session only if
     * every fraction used in the session has both numerator and denominator
     * within the range of <code>short</code>. This is a pure function depending
     * only on the numerator and the denominator, and so it is guaranteed not to
     * change during a session.
     * @return The hash code. For example, for <sup>22</sup>&frasl;<sub>7</sub>,
     * this might be 1441799.
     */
    @Override
    public int hashCode() {
        int hash = ((int) this.numerator) << 16;
        hash += ((int) this.denominator) % 65536;
        return hash;
    }

    @Override
    public int compareTo(Fraction other) {
        Fraction diff = this.minus(other);
        return Long.signum(diff.numerator);
    }

    public static Fraction parseFract(String s) {
        throw new NumberFormatException("Sorry");
    }

    // TODO: Write tests for this
    public Fraction(long numer) {
        this(1, numer);
    }

    /**
     * Primary constructor. This constructor does not require the caller to
     * express the fraction in lowest terms, but it does ensure the fraction is
     * internally represented in lowest terms so that no "reduction" is needed
     * when {@link #toString()} is called.
     * @param numer The numerator of the fraction. For example, 7. Preferably in
     *              the range of <code>int</code>, to avoid overflows in
     *              operations with other fractions.
     * @param denom The denominator of the fraction. It must not be 0 nor
     *              <code>Long.MIN_VALUE</code>. Other than that, there are no
     *              requirements for the denominator, though I do recommend that
     *              it be in the range of <code>int</code>, to avoid overflows
     *              in operations with other fractions. It need not be coprime
     *              to the numerator, and it may be negative. The constructor
     *              will make sure the fraction is in the lowest terms, and that
     *              it has a positive denominator. For example, &minus;4 (which
     *              will be changed to 4, and <code>numer</code> will be changed
     *              accordingly).
     * @throws ArithmeticException If <code>denom</code> is 0 or
     * <code>Long.MIN_VALUE</code>. The problem with the latter is that its
     * absolute value is just outside the range of <code>long</code> to
     * represent. The problem with the former is obvious.
     * @throws IllegalArgumentException If <code>denom</code> is 0. I consider
     * this one a better exception for division by 0, but I also consider it
     * more elegant to have only one validation check per parameter. I've been
     * going back and forth on this one. Clearly this exception would be
     * inappropriate for <code>denom == Long.MIN_VALUE</code>, so this exception
     * should never occur for that particular parameter.
     */
    public Fraction(long numer, long denom) {
        if ((denom & Long.MAX_VALUE) == 0) {
            String excMsg = "Denominator " + denom
                    + " is not valid or available";
            throw new ArithmeticException(excMsg);
        }
        long adjust = euclideanGCD(numer, denom) * (denom < 0 ? -1 : 1);
        this.numerator = numer / adjust;
        this.denominator = denom / adjust;
    }

}
