package measures.imperial;

import fractions.Fraction;

public final class Mile extends LengthMeasure {

    private static final Fraction FOOT_MULTIPLIER = new Fraction(5280);

    @Override
    public String getSingularWord() {
        return "mile";
    }

    @Override
    public String getPluralWord() {
        return "miles";
    }

    @Override
    public String getAbbreviation() {
        return "m";
    }

    @Override
    public String toString() {
        String intermediate = this.quantity.toString();
        switch (intermediate) {
            case "-1":
            case "1":
                return intermediate + " mile";
            default:
                return intermediate + " miles";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Mile)) {
            return false;
        }
        return this.quantity.equals(((Mile) obj).quantity);
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

    public Mile(Fraction number) {
        super(number, FOOT_MULTIPLIER);
        if (number == null) {
            throw new NullPointerException("Number should not be null");
        }
    }

}
