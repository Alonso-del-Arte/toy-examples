package measures.imperial;

import fractions.Fraction;

public final class Inch extends LengthMeasure {

    private static final Fraction ONE_TWELFTH = new Fraction(1, 12);

    // TODO: Write tests for this
    @Override
    public Inch plus(LengthMeasure addend) {
        return this;
    }

    // TODO: Write tests for this
    @Override
    public Inch minus(LengthMeasure subtrahend) {
        return this;
    }

    // TODO: Write tests for this
    @Override
    public Inch times(int multiplicand) {
        return this;
    }

    // TODO: Write tests for this
    @Override
    public Inch divides(int divisor) {
        return this;
    }

    @Override
    public String getSingularWord() {
        return "inch";
    }

    @Override
    public String getPluralWord() {
        return "inches";
    }

    @Override
    public String getAbbreviation() {
        return "in";
    }

    @Override
    public String toString() {
        String intermediate = this.quantity.toString();
        switch (intermediate) {
            case "-1":
            case "1":
                return intermediate + " inch";
            default:
                return intermediate + " inches";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Inch)) {
            return false;
        }
        return this.quantity.equals(((Inch) obj).quantity);
    }

    @Override
    public int hashCode() {
        return this.quantity.hashCode();
    }

    // TODO: Write tests for this
    @Override
    public int compareTo(LengthMeasure other) {
        return 0;
    }

    public Inch(Fraction number) {
        super(number, ONE_TWELFTH);
        if (number == null) {
            String excMsg = "Number must not be null";
            throw new NullPointerException(excMsg);
        }
    }

}
