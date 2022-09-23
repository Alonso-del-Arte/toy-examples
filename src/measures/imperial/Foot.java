package measures.imperial;

import fractions.Fraction;

public class Foot extends LengthMeasure {

    private static final Fraction ONE = new Fraction(1);

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

    public Foot(Fraction number) {
        super(number, ONE);
    }

}
