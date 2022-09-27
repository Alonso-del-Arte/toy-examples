package measures.imperial;

import fractions.Fraction;

public final class Yard extends LengthMeasure {

    private static final Fraction THREE = new Fraction(3);

    // TODO: Write tests for this
    @Override
    public String getSingularWord() {
        return "NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    @Override
    public String getPluralWord() {
        return "NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    @Override
    public String getAbbreviation() {
        return "NOT IMPLEMENTED YET";
    }

    @Override
    public String toString() {
        if (this.quantity.getNumericApproximation() == 1.0) {
            return "1 yard";
        }
        return this.quantity + " yards";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof  Yard)) {
            return false;
        }
        return this.quantity.equals(((Yard) obj).quantity);
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

    public Yard(Fraction number) {
        super(number, THREE);
        if (number == null) {
            throw new NullPointerException("Number should not be null");
        }
    }

}
