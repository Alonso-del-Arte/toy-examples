package measures.imperial;

import fractions.Fraction;

public final class Inch extends LengthMeasure {

    private static final Fraction ONE_TWELFTH = new Fraction(1, 12);

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
        if (this.quantity.getNumericApproximation() == 1.0) {
            return "1 inch";
        }
        return this.quantity + " inches";
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
