package measures.imperial;

import fractions.Fraction;

public class Hand extends LengthMeasure {

    private static final Fraction ONE_THIRD = new Fraction(1, 3);

    // TODO: Write tests for this
    @Override
    public Hand plus(LengthMeasure addend) {
        return this;
    }

    // TODO: Write tests for this
    @Override
    public Hand minus(LengthMeasure subtrahend) {
        return this;
    }

    // TODO: Write tests for this
    @Override
    public Hand times(int multiplicand) {
        return this;
    }

    // TODO: Write tests for this
    @Override
    public Hand divides(int divisor) {
        return this;
    }

    @Override
    public String getSingularWord() {
        return "hand";
    }

    @Override
    public String getPluralWord() {
        return "hands";
    }

    // TODO: Write tests for this
    @Override
    public String getAbbreviation() {
        return "NOT IMPLEMENTED YET";
    }

    @Override
    public String toString() {
        String intermediate = this.quantity.toString();
        switch (intermediate) {
            case "-1":
            case "1":
                return intermediate + " hand";
            default:
                return intermediate + " hands";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Hand)) {
            return false;
        }
        return this.quantity.equals(((Hand) obj).quantity);
    }

    @Override
    public int hashCode() {
        return this.quantity.hashCode();
    }

    public Hand(Fraction number) {
        super(number, ONE_THIRD);
        if (number == null) {
            throw new NullPointerException("number should not be null");
        }
    }

}
