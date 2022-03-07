package fractions;

public class Fraction {

    private final long numerator, denominator;

    public long getNumerator() {
        return this.numerator;
    }

    public long getDenominator() {
        return this.denominator;
    }

    // TODO: Write tests for this
    public double getNumericApproximation() {
        return -1.0;
    }

    // TODO: Write tests for this
    public Fraction plus(Fraction addend) {
        return this;
    }

    // TODO: Write tests for this
    public Fraction negate() {
        return this;
    }

    // TODO: Write tests for this
    public Fraction minus(Fraction subtrahend) {
        return this;
    }

    // TODO: Write tests for this
    public Fraction times(Fraction multiplicand) {
        return this;
    }

    // TODO: Write tests for this
    public Fraction reciprocal() {
        return this;
    }

    // TODO: Write tests for this
    public Fraction divides(Fraction divisor) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return obj.getClass().equals(this.getClass());
    }

    // TODO: Write tests for this
    @Override
    public int hashCode() {
        return 0;
    }

    // TODO: Write tests for this
    public Fraction(long numer) {
        this(1, numer);
    }

    /**
     * Constructor.
     * @param numer The numerator of the fraction. For example, 2. Preferably in
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
        if (denom < 0) {
            numer *= -1;
            denom *= -1;
        }
        this.numerator = numer;
        this.denominator = denom;
    }

}
