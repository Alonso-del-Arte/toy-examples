package measures.imperial;

import fractions.Fraction;

public class Hand extends LengthMeasure {

    private static final Fraction ONE_THIRD = new Fraction(1, 3);

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

    public Hand(Fraction number) {
        super(number, ONE_THIRD);
    }

}
