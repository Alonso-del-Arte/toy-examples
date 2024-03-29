package measures.imperial;

import fractions.Fraction;

public final class Foot extends LengthMeasure {

    private static final Fraction ONE = new Fraction(1);

    // TODO: Write tests for this
    @Override
    public Foot plus(LengthMeasure addend) {
        return this;
    }

    // TODO: Write tests for this
    @Override
    public Foot minus(LengthMeasure subtrahend) {
        return this;
    }

    // TODO: Write tests for this
    @Override
    public Foot times(int multiplicand) {
        return this;
    }

    // TODO: Write tests for this
    @Override
    public Foot divides(int divisor) {
        return this;
    }

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
        String intermediate = this.quantity.toString();
        switch (intermediate) {
            case "-1":
            case "1":
                return intermediate + " foot";
            default:
                return intermediate + " feet";
        }
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
