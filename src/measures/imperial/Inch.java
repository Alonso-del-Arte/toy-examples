package measures.imperial;

import fractions.Fraction;

public class Inch extends LengthMeasure {

    private static final Fraction ONE_TWELFTH = new Fraction(1, 12);

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

    public Inch(Fraction number) {
        super(number, ONE_TWELFTH);
    }

}
