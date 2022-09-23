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

    // TODO: Write tests for this
    @Override
    public boolean equals(Object o) {
        return false;
    }

    // TODO: Write tests for this
    @Override
    public int hashCode() {
        return 0;
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
