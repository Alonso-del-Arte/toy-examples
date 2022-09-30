package measures.imperial;

import fractions.Fraction;

public final class Yard extends LengthMeasure {

    private static final Fraction THREE = new Fraction(3);

    // TODO: Write tests for this
    @Override
    public Yard plus(LengthMeasure addend) {
        return this;
    }

    // TODO: Write tests for this
    @Override
    public Yard minus(LengthMeasure subtrahend) {
        return this;
    }

    // TODO: Write tests for this
    @Override
    public Yard times(int multiplicand) {
        return this;
    }

    // TODO: Write tests for this
    @Override
    public Yard divides(int divisor) {
        return this;
    }

    @Override
    public String getSingularWord() {
        return "yard";
    }

    @Override
    public String getPluralWord() {
        return "yards";
    }

    @Override
    public String getAbbreviation() {
        return "yd";
    }

    @Override
    public String toString() {
        String intermediate = this.quantity.toString();
        switch (intermediate) {
            case "-1":
            case "1":
                return intermediate + " yard";
            default:
                return intermediate + " yards";
        }
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
