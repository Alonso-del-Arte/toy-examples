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

    public Foot(Fraction number) {
        super(number, ONE);
    }

}
