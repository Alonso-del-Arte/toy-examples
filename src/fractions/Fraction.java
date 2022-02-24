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

    // TODO: Write tests for this
    public Fraction(long numer, long denom) {
        this.numerator = numer;
        this.denominator = denom;
    }

}
