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

    public Yard(Fraction number) {
        super(number, THREE);
    }

}
