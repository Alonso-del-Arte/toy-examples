package measures.imperial;

import fractions.Fraction;

public final class Foot extends LengthMeasure {

    private static final Fraction ONE = new Fraction(1);

    @Override
    public String getSingularWord() {
        return "foot";
    }

    @Override
    public String getPluralWord() {
        return "feet";
    }

    @Override
    public String getAbbreviation() {
        return "ft";
    }

    @Override
    public String toString() {
        if (this.quantity.getNumericApproximation() == 1.0) {
            return "1 foot";
        }
        return this.quantity + " feet";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Foot)) {
            return false;
        }
        return this.quantity.equals(((Foot) obj).quantity);
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

    public Foot(Fraction number) {
        super(number, ONE);
        if (number == null) {
            throw new NullPointerException("Number should not be null");
        }
    }

}
